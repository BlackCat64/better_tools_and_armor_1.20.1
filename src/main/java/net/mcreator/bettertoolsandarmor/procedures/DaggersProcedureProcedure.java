package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class DaggersProcedureProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel && _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().get(ResourceLocation.parse("better_tools:daggers_adv"))).isDone())) {
			if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.STONE_DAGGER.get()))) {
				if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.FLINT_DAGGER.get()))) {
					if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.IRON_DAGGER.get()))) {
						if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.GOLD_DAGGER.get()))) {
							if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.RUBY_DAGGER.get()))) {
								if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.DIAMOND_DAGGER.get()))) {
									if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.NETHERITE_DAGGER.get()))) {
										if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.NETHER_DIAMOND_DAGGER.get()))) {
											if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.END_TITANIUM_DAGGER.get()))) {
												if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.SAPPHIRE_DAGGER.get()))) {
													if (hasEntityInInventory(entity, new ItemStack(BetterToolsModItems.TOPAZ_DAGGER.get()))) {
														if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandlerIter) {
															for (int _idx = 0; _idx < _modHandlerIter.getSlots(); _idx++) {
																ItemStack itemstackiterator = _modHandlerIter.getStackInSlot(_idx).copy();
																if (itemstackiterator.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_daggers")))) {
																	if (entity instanceof ServerPlayer _player) {
																		AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:daggers_adv"));
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

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}