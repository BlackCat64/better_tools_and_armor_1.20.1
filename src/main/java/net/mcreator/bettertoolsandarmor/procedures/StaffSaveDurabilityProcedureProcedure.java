package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class StaffSaveDurabilityProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			(entity instanceof LivingEntity _entUseItem3 ? _entUseItem3.getUseItem() : ItemStack.EMPTY).setDamageValue((int) ((entity instanceof LivingEntity _entUseItem1 ? _entUseItem1.getUseItem() : ItemStack.EMPTY).getDamageValue() - 1));
		}
	}
}
