package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class FizzyDrinksAdvProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_food_was_carbonated && entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_food_eaten.is(ItemTags.create(ResourceLocation.parse("better_tools:drinks")))) {
			if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_non_carbonated_food_eaten >= 24000) {
				if (entity instanceof ServerPlayer _player) {
					AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:fizzy_drinks_adv"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
		} else {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.time_since_non_carbonated_food_eaten = 0;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}