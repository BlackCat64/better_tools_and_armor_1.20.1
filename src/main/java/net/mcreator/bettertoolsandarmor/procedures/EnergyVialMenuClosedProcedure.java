package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class EnergyVialMenuClosedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!PlayerHasEnergyVialEquippedProcedure.execute(entity)) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).energy_vial_to_update.copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}
