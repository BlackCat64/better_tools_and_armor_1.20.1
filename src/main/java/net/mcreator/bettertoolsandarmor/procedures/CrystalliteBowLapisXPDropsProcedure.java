package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModGameRules;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteBowLapisXPDropsProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getDirectEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, immediatesourceentity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity, Entity sourceentity) {
		if (entity == null || immediatesourceentity == null || sourceentity == null)
			return;
		double distance = 0;
		if (immediatesourceentity.getPersistentData().getBoolean("crystallite_lapis_upgrade") && !(entity instanceof Player)) {
			distance = GetDistanceBetweenPointsProcedure.execute(entity.getX(), entity.getY(), entity.getZ(), sourceentity.getX(), sourceentity.getY(), sourceentity.getZ());
			if (distance >= 5) {
				if (world instanceof ServerLevel _level)
					_level.addFreshEntity(new ExperienceOrb(_level, (x + Math.random()), (y + 1), (z + Math.random()), (int) Math.floor(distance)));
				if (world.getLevelData().getGameRules().getBoolean(BetterToolsModGameRules.DISPLAY_XP_DROP_VALUES)) {
					if (!world.isClientSide() && world.getServer() != null)
						world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((new java.text.DecimalFormat("#").format(Math.floor(distance)) + " XP dropped by " + entity.getDisplayName().getString())), false);
				}
			}
		}
	}
}
