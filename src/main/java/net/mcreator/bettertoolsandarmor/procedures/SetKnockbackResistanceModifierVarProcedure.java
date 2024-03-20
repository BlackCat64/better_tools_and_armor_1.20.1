package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SetKnockbackResistanceModifierVarProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double modifier = 0;
		if (world.dayTime() % 10 == 0) {
			modifier = ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE).getValue()
					- ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE).getBaseValue();
			if (modifier > 0) {
				{
					String _setval = "+" + new java.text.DecimalFormat("#.###").format(modifier);
					entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.knockback_resistance_modifier = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else {
				{
					String _setval = new java.text.DecimalFormat("#.###").format(modifier);
					entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.knockback_resistance_modifier = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
