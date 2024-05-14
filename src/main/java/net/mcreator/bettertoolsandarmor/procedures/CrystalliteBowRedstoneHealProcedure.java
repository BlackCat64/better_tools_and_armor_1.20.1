package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteBowRedstoneHealProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getSource().getDirectEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity immediatesourceentity, Entity sourceentity) {
		execute(null, immediatesourceentity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity immediatesourceentity, Entity sourceentity) {
		if (immediatesourceentity == null || sourceentity == null)
			return;
		if (immediatesourceentity.getPersistentData().getBoolean("crystallite_redstone_upgrade")) {
			if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) <= 0.25 * (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
				if (sourceentity instanceof LivingEntity _entity)
					_entity.setHealth((float) ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + 2));
			} else {
				if (sourceentity instanceof LivingEntity _entity)
					_entity.setHealth((float) ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + 1));
			}
		}
	}
}
