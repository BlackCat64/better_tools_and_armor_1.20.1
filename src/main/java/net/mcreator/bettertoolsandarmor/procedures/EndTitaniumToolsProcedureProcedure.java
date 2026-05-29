package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class EndTitaniumToolsProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel && _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().get(ResourceLocation.parse("better_tools:end_titanium_tools_adv"))).isDone())) {
			if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.END_TITANIUM_PICKAXE.get()))) {
				if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.END_TITANIUM_AXE.get()))) {
					if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.END_TITANIUM_SHOVEL.get()))) {
						if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.END_TITANIUM_HOE.get()))) {
							if (entity instanceof ServerPlayer _player) {
								AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:end_titanium_tools_adv"));
								if (_adv != null) {
									AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
									if (!_ap.isDone()) {
										for (String criteria : _ap.getRemainingCriteria())
											_player.getAdvancements().award(_adv, criteria);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}