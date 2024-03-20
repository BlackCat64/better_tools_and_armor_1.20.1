package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class RubyToolsProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel && _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:ruby_tools_adv"))).isDone())) {
			if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.RUBY_PICKAXE.get())) : false) {
				if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.RUBY_AXE.get())) : false) {
					if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.RUBY_SHOVEL.get())) : false) {
						if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.RUBY_HOE.get())) : false) {
							if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.RUBY_DAGGER.get())) : false) {
								if (entity instanceof ServerPlayer _player) {
									Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:ruby_tools_adv"));
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
}
