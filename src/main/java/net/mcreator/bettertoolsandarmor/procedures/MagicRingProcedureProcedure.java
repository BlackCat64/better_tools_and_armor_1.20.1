package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MagicRingProcedureProcedure {
	@SubscribeEvent
	public static void onLivingDropXp(LivingExperienceDropEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getAttackingPlayer(), event.getDroppedExperience());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity, double droppedexperience) {
		execute(null, world, x, y, z, sourceentity, droppedexperience);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity sourceentity, double droppedexperience) {
		if (sourceentity == null)
			return;
		if (sourceentity instanceof Player && sourceentity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.MAGIC_RING.get(), lv).isPresent() : false) {
			if (world instanceof ServerLevel _level)
				_level.addFreshEntity(new ExperienceOrb(_level, x, y, z, (int) (0.1 * droppedexperience)));
		}
	}
}
