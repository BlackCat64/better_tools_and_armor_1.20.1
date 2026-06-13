package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.IItemHandler;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class PlayerEffectEnergyValueProcedure {
	public static double execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return 0;
		double energy = 0;
		if (entity instanceof Player player4) {
			IItemHandler inventory4 = BetterToolsMod.CuriosApiHelper.getCuriosInventory(player4);
			if (inventory4 != null) {
				for (int i = 0; i < inventory4.getSlots(); i++) {
					ItemStack itemstackiterator = inventory4.getStackInSlot(i);
					if (itemstackiterator.is(ItemTags.create(ResourceLocation.parse("better_tools:energy_vials")))) {
						energy = itemstackiterator.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy");
					}
				}
			}
		}
		return energy;
	}
}