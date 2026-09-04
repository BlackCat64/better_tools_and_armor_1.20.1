package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;

public class GetVanillaBlockXPDropProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null || !(entity instanceof LivingEntity livingEntity))
			return 0;
		BlockPos pos = BlockPos.containing(x, y, z);
		ItemStack tool = livingEntity.getMainHandItem();
		int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, tool);
		int silkTouchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool);
		RandomSource random = RandomSource.create();
		return blockstate.getExpDrop(world, random, pos, fortuneLevel, silkTouchLevel);
	}
}
