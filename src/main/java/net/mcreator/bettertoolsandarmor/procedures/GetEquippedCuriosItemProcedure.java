package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.IItemHandler;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class GetEquippedCuriosItemProcedure {
	public static ItemStack execute(LevelAccessor world, Entity entity, ItemStack curiosItem) {
		if (entity == null)
			return ItemStack.EMPTY;
		ItemStack found_item = ItemStack.EMPTY;
		if (entity instanceof Player player3) {
			IItemHandler inventory3 = BetterToolsMod.CuriosApiHelper.getCuriosInventory(player3);
			if (inventory3 != null) {
				for (int i = 0; i < inventory3.getSlots(); i++) {
					ItemStack itemstackiterator = inventory3.getStackInSlot(i);
					if (itemstackiterator.getItem() == curiosItem.getItem()) {
						found_item = itemstackiterator.copy();
						break;
					}
				}
			}
		}
		return found_item;
	}
}