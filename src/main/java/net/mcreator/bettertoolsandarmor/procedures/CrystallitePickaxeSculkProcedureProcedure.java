package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.Comparator;

public class CrystallitePickaxeSculkProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double x_offset = 0;
		double y_offset = 0;
		double z_offset = 0;
		double count = 0;
		x_offset = 0;
		y_offset = 0;
		z_offset = 0;
		if (blockstate.is(BlockTags.create(new ResourceLocation("forge:ores")))) {
			{
				double _setval = 1;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.blocks_broken_with_sculk_crystallite_pickaxe = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (!entity.isShiftKeyDown()) {
				x_offset = -1;
				for (int index0 = 0; index0 < 3; index0++) {
					y_offset = -1;
					for (int index1 = 0; index1 < 3; index1++) {
						z_offset = -1;
						for (int index2 = 0; index2 < 3; index2++) {
							if (!(x_offset == 0 && y_offset == 0 && z_offset == 0)) {
								if ((world.getBlockState(BlockPos.containing(x + x_offset, y + y_offset, z + z_offset))).getBlock() == blockstate.getBlock()) {
									if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, itemstack) != 0) {
										world.destroyBlock(BlockPos.containing(x + x_offset, y + y_offset, z + z_offset), false);
										if (world instanceof ServerLevel _level) {
											ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, (new ItemStack(blockstate.getBlock())));
											entityToSpawn.setPickUpDelay(10);
											_level.addFreshEntity(entityToSpawn);
										}
									} else if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, itemstack) != 0) {
										world.destroyBlock(BlockPos.containing(x + x_offset, y + y_offset, z + z_offset), false);
									} else {
										{
											BlockPos _pos = BlockPos.containing(x + x_offset, y + y_offset, z + z_offset);
											Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), null);
											world.destroyBlock(_pos, false);
										}
									}
									{
										ItemStack _ist = itemstack;
										if (_ist.hurt(1, RandomSource.create(), null)) {
											_ist.shrink(1);
											_ist.setDamageValue(0);
										}
									}
									{
										double _setval = (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).blocks_broken_with_sculk_crystallite_pickaxe + 1;
										entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.blocks_broken_with_sculk_crystallite_pickaxe = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
								}
							}
							z_offset = z_offset + 1;
						}
						y_offset = y_offset + 1;
					}
					x_offset = x_offset + 1;
				}
				if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, itemstack) != 0) {
					if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).blocks_broken_with_sculk_crystallite_pickaxe > 1) {
						BetterToolsMod.queueServerWork(2, () -> {
							(((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.5), (z + 0.5)), 1, 1, 1), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf((x + 0.5), (y + 0.5), (z + 0.5))).findFirst().orElse(null)) instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY)
									.setCount((int) Math.floor((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).blocks_broken_with_sculk_crystallite_pickaxe
											* (itemstack.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE) * Math.random() + 1)));
						});
					}
				}
				if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).blocks_broken_with_sculk_crystallite_pickaxe >= 16) {
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:vein_mining_adv"));
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
