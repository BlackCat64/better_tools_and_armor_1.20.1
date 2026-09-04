package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class CheckForNetheriteTierToolProcedure {
	public static boolean execute(Entity entity) {
		if (entity instanceof LivingEntity livEnt) {
			ItemStack tool = livEnt.getMainHandItem();
			if (tool.getItem() instanceof TieredItem tiered) {
				Tier toolTier = tiered.getTier();
				if (toolTier != null) {
					return toolTier.getLevel() >= Tiers.NETHERITE.getLevel();
				}
			}
		}
		return false;
	}
}
