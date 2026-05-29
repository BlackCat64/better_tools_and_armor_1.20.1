package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModGameRules;

import javax.annotation.Nullable;

@EventBusSubscriber
public class XpPickupTrackerProcedure {
	@SubscribeEvent
	public static void onLivingDropXp(LivingExperienceDropEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getAttackingPlayer(), event.getDroppedExperience());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, double droppedexperience) {
		execute(null, world, entity, sourceentity, droppedexperience);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity, double droppedexperience) {
		if (entity == null || sourceentity == null)
			return;
		if (world.getLevelData().getGameRules().getBoolean(BetterToolsModGameRules.DISPLAY_XP_DROP_VALUES) && sourceentity.hasPermissions(2) && sourceentity instanceof Player) {
			if (sourceentity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((new java.text.DecimalFormat("##.##").format(droppedexperience) + " XP dropped by " + entity.getDisplayName().getString())), false);
		}
	}
}