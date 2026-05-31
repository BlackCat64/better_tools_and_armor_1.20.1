package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.IItemHandler;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class HasCuriosItemEquippedProcedure {
	public static boolean execute(LevelAccessor world, Entity entity, ItemStack curiosItem) {
		if (entity == null)
			return false;
		boolean found = false;
		if (entity instanceof Player player2) {
			IItemHandler inventory2 = BetterToolsMod.CuriosApiHelper.getCuriosInventory(player2);
			if (inventory2 != null) {
				for (int i = 0; i < inventory2.getSlots(); i++) {
					ItemStack itemstackiterator = inventory2.getStackInSlot(i);
					if (itemstackiterator.getItem() == curiosItem.getItem()) {
						found = true;
						break;
					}
				}
			}
		}
		return found;
	}
}