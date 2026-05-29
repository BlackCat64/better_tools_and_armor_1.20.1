package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteSwordHoneyProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getSource().getEntity());
		}
	}

	public static void execute(Entity sourceentity) {
		execute(null, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity sourceentity) {
		if (sourceentity == null)
			return;
		{
			BetterToolsModVariables.PlayerVariables _vars = sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
			_vars.time_since_last_attacked = 0;
			_vars.syncPlayerVariables(sourceentity);
		}
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:combo_weapons")))) {
			if (sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_attacked <= 40) {
				if (false) {// Remove modifier 1, add modifier 2
				} else if (false) {// Remove modifier 1+2, add modifier 3
				}
			}
			if (true) {// Add modifier 1
			}
		}
	}
}