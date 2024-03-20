package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModParticleTypes;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GuardianNecklaceProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		double damage = 0;
		if (!BetterToolsModVariables.being_damaged_flag) {
			if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.GUARDIAN_NECKLACE.get(), lv).isPresent() : false) {
				if (entity.isInWaterRainOrBubble()) {
					BetterToolsModVariables.being_damaged_flag = true;
					damage = 3;
					if (sourceentity instanceof LivingEntity _livEnt2 && _livEnt2.getMobType() == MobType.WATER) {
						damage = damage * 2;
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.GUARDIAN_STAFF_BEAM.get()), (sourceentity.getX()), (sourceentity.getY() + 1), (sourceentity.getZ()), 8, 0.3, 1, 0.3, 0.05);
					}
					sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:water_pulse_damage"))), entity),
							(float) damage);
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.NAUTILUS, (sourceentity.getX()), (sourceentity.getY() + 1), (sourceentity.getZ()), 8, 0.3, 1, 0.3, 0.05);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(sourceentity.getX(), sourceentity.getY(), sourceentity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("enchant.thorns.hit")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound((sourceentity.getX()), (sourceentity.getY()), (sourceentity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("enchant.thorns.hit")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					BetterToolsModVariables.being_damaged_flag = false;
				}
			}
		}
	}
}
