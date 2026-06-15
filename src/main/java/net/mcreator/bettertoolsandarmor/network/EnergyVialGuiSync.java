package net.mcreator.bettertoolsandarmor.network;

import io.netty.buffer.ByteBuf;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;
import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.world.inventory.EnergyVialMenuMenu;
import net.mcreator.bettertoolsandarmor.procedures.GetEquippedVialProcedure;
import net.mcreator.bettertoolsandarmor.procedures.PlayerHasEnergyVialEquippedProcedure;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.core.component.DataComponents;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;


@EventBusSubscriber
public record EnergyVialGuiSync(
        boolean helmetActive,
        boolean chestplateActive,
        boolean leggingsActive,
        boolean bootsActive
) implements CustomPacketPayload {

    public static final Type<EnergyVialGuiSync> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(
                    BetterToolsMod.MODID,
                    "energy_vial_gui_sync"
            ));

    public static final StreamCodec<FriendlyByteBuf, EnergyVialGuiSync> STREAM_CODEC =
        StreamCodec.composite(
                ByteBufCodecs.BOOL,
                EnergyVialGuiSync::helmetActive,

                ByteBufCodecs.BOOL,
                EnergyVialGuiSync::chestplateActive,

                ByteBufCodecs.BOOL,
                EnergyVialGuiSync::leggingsActive,

                ByteBufCodecs.BOOL,
                EnergyVialGuiSync::bootsActive,

                EnergyVialGuiSync::new
        );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(EnergyVialGuiSync message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player))
                return;

            if (!(player.containerMenu instanceof EnergyVialMenuMenu menu))
                return;

            ItemStack vial = menu.getSlots().get(1).getItem();

            if (vial.isEmpty())
                return;

			// Update data of vial, once we have confirmed it exists and the player is in the Energy Vial GUI
            CustomData.update(DataComponents.CUSTOM_DATA, vial,
                    tag -> tag.putBoolean("helmet_active", message.helmetActive()));

            CustomData.update(DataComponents.CUSTOM_DATA, vial,
                    tag -> tag.putBoolean("chestplate_active", message.chestplateActive()));

            CustomData.update(DataComponents.CUSTOM_DATA, vial,
                    tag -> tag.putBoolean("leggings_active", message.leggingsActive()));

            CustomData.update(DataComponents.CUSTOM_DATA, vial,
                    tag -> tag.putBoolean("boots_active", message.bootsActive()));

			// Copy data to the player's actual Energy Vial, either equipped or in their inventory
            if (PlayerHasEnergyVialEquippedProcedure.execute(player.level(), player)) {
                GetEquippedVialProcedure.execute(player.level(), player)
                        .applyComponents(vial.getComponents()); // If equipped, use procedure to find the vial
            } 
            else {
            	// If in inventory, use global variable to find the vial
                int slot = (int) player.getData(BetterToolsModVariables.PLAYER_VARIABLES).energy_vial_slot;

                if (slot >= 0
                        && player.getCapability(Capabilities.ItemHandler.ENTITY, null)
                        instanceof IItemHandlerModifiable handler) {

                    handler.getStackInSlot(slot)
                            .applyComponents(vial.getComponents());
                }
            }
        });
    }

    @SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
	    BetterToolsMod.addNetworkMessage(
            EnergyVialGuiSync.TYPE,
            EnergyVialGuiSync.STREAM_CODEC,
            EnergyVialGuiSync::handle
    	);
	}
}