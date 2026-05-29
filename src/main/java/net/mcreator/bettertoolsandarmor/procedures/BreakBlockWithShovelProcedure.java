package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

public class BreakBlockWithShovelProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		BlockState block = Blocks.AIR.defaultBlockState();
		block = (world.getBlockState(BlockPos.containing(x, y, z)));
		if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH)) != 0) {
			world.destroyBlock(BlockPos.containing(x, y, z), false);
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), (new ItemStack(block.getBlock())));
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
		} else {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), null);
				world.destroyBlock(_pos, false);
			}
		}
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			if (world instanceof ServerLevel _level) {
				itemstack.hurtAndBreak(1, _level, null, _stkprov -> {
				});
			}
		}
	}
}