package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class HeartyShirtActiveProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		double absorption_limit = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.DIAMOND_HEARTY_CHESTPLATE.get()) {
			absorption_limit = 8;
		} else {
			absorption_limit = 4;
		}
		return (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_hurt > 200
				&& (entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) < absorption_limit;
	}
}
