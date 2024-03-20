package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class CrystalliteSapphireSilkTouchProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (!(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, itemstack) != 0)) {
			itemstack.enchant(Enchantments.SILK_TOUCH, 1);
			if (entity instanceof Player _player)
				_player.giveExperiencePoints(-(10));
		}
	}
}
