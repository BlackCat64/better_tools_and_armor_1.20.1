package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.eventbus.api.Event;

public class TopazToolsFortuneProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (!(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, itemstack) != 0)) {
			itemstack.enchant(Enchantments.BLOCK_FORTUNE, 1);
			if (entity instanceof Player _player)
				_player.giveExperiencePoints(-(10));
		}
	}
}
