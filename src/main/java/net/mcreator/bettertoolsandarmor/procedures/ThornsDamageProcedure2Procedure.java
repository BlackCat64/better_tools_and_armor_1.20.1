package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ThornsDamageProcedure2Procedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getDirectEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity immediatesourceentity) {
		execute(null, world, entity, immediatesourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity immediatesourceentity) {
		if (entity == null || immediatesourceentity == null)
			return;
		if (!BetterToolsModVariables.being_damaged_flag) {
			if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.THORNSDAMAGE.get()) != null) {
				if (((LivingEntity) entity).getAttribute(BetterToolsModAttributes.THORNSDAMAGE.get()).getValue() > 0) {
					BetterToolsModVariables.being_damaged_flag = true;
					immediatesourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.THORNS), entity),
							(float) ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.THORNSDAMAGE.get()).getValue());
					BetterToolsModVariables.being_damaged_flag = false;
				}
			}
		}
	}
}
