package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

public class EnergyVialApplyEffectsProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (itemstack.getOrCreateTag().getDouble("energy") > 0) {
			if (((LivingEntity) entity).getAttribute(BetterToolsModAttributes.EFFECTENERGYCOST.get()).getValue() > 0) {
				itemstack.getOrCreateTag().putDouble("energy", Math.max(itemstack.getOrCreateTag().getDouble("energy") - ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.EFFECTENERGYCOST.get()).getValue(), 0));
				if (itemstack.getOrCreateTag().getBoolean("helmet_active")) {
					ApplyHelmetEffectProcedure.execute(entity);
				}
				if (itemstack.getOrCreateTag().getBoolean("chestplate_active")) {
					ApplyChestplateEffectProcedure.execute(entity);
				}
				if (itemstack.getOrCreateTag().getBoolean("leggings_active")) {
					ApplyLeggingsEffectProcedure.execute(entity);
				}
				if (itemstack.getOrCreateTag().getBoolean("boots_active")) {
					ApplyBootsEffectProcedure.execute(entity);
				}
			}
		} else {
			itemstack.getOrCreateTag().putDouble("energy", 0);
		}
	}
}
