package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class EnergyVialTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		double energy = 0;
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:energy_vials")))) {
			energy = itemstack.getOrCreateTag().getDouble("energy");
			tooltip.add(Component.literal(""));
			tooltip.add(Component.literal(("\u00A77Energy: \u00A76" + new java.text.DecimalFormat("#").format(energy) + " / " + new java.text.DecimalFormat("#").format(GetEnergyVialCapacityProcedure.execute(itemstack)))));
			tooltip.add(Component.literal(("\u00A77Cost: \u00A7c" + new java.text.DecimalFormat("#").format(CalculateEffectEnergyCostProcedure.execute(entity, itemstack)) + "\u00A76 / 5s")));
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_cost > 0) {
				tooltip.add(Component.literal(("\u00A77Estimated Time Remaining: \u00A76" + EnergyTimeDisplayProcedure.execute(entity, itemstack))));
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.WINGED_BOOTS_BOOTS.get()) {
				tooltip.add(Component.literal(("\u00A77Estimated Jumps Remaining: \u00A76" + new java.text.DecimalFormat("#").format(energy / 50))));
			}
			if (!IsWearingGlassArmorFullSetProcedure.execute(entity)) {
				if (EnergyVialActiveArmorPiecesProcedure.execute(entity, itemstack) == 2) {
					tooltip.add(Component.literal("\u00A772-piece bonus: \u00A7a2/3 Energy Cost"));
				} else if (EnergyVialActiveArmorPiecesProcedure.execute(entity, itemstack) >= 3) {
					tooltip.add(Component.literal("\u00A773-piece bonus: \u00A7a1/2 Energy Cost"));
				}
			}
		}
	}
}
