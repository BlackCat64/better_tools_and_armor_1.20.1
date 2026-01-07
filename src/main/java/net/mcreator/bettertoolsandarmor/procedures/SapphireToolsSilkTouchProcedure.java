package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.eventbus.api.Event;

public class SapphireToolsSilkTouchProcedure {
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
