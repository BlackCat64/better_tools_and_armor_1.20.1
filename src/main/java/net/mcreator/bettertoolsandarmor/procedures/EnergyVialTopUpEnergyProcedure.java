package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber
public class EnergyVialTopUpEnergyProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getEntity(), event.getItemStack());
	}

	public static void execute(Entity entity, ItemStack itemstack) {
		execute(null, entity, itemstack);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		ItemStack item = ItemStack.EMPTY;
		if (itemstack.getItem() == BetterToolsModItems.ENERGY_VIAL.get() && (!entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.ENERGY_VIAL.get(), lv).isPresent() : false)) {
			item = (new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack(0, entity));
			if (item.is(ItemTags.create(new ResourceLocation("better_tools:energy_vial_fuels")))) {
				if (Screen.hasShiftDown()) {
					itemstack.getOrCreateTag().putDouble("energy", (itemstack.getOrCreateTag().getDouble("energy") + 1000));
					{
						final int _slotid = 0;
						final ItemStack _setstack = item.copy();
						_setstack.setCount((int) (item.getCount() - 1));
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
								_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
						});
					}
				}
			}
		}
	}
}
