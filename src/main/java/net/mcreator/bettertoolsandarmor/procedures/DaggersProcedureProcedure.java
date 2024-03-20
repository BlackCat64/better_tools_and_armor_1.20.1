package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.concurrent.atomic.AtomicReference;

public class DaggersProcedureProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel && _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:daggers_adv"))).isDone())) {
			if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.STONE_DAGGER.get())) : false) {
				if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.FLINT_DAGGER.get())) : false) {
					if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.IRON_DAGGER.get())) : false) {
						if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.GOLD_DAGGER.get())) : false) {
							if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.RUBY_DAGGER.get())) : false) {
								if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_DAGGER.get())) : false) {
									if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.NETHERITE_DAGGER.get())) : false) {
										if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.NETHER_DIAMOND_DAGGER.get())) : false) {
											if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.END_TITANIUM_DAGGER.get())) : false) {
												if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.SAPPHIRE_DAGGER.get())) : false) {
													if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.TOPAZ_DAGGER.get())) : false) {
														{
															AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
															entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
															if (_iitemhandlerref.get() != null) {
																for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
																	ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
																	if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_daggers")))) {
																		if (entity instanceof ServerPlayer _player) {
																			Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:daggers_adv"));
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
