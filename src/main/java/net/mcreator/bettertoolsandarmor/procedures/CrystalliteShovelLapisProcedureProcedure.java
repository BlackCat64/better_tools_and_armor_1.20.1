package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.Registries;

public class CrystalliteShovelLapisProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		double chance = 0;
		chance = 0.1;
		if (false) {
			chance = chance + 1 * 0.05;
		}
		if (Math.random() < chance) {
			if (world instanceof ServerLevel _level)
				_level.addFreshEntity(new ExperienceOrb(_level, (x + Math.random()), (y + Math.random()), (z + Math.random()),
						Mth.nextInt(RandomSource.create(), 1, itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)) + 1)));
		}
	}
}