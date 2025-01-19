package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class GuardianNecklaceProcedureProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double damage = 0;
		double radius = 0;
		if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.GUARDIAN_NECKLACE.get(), lv).isPresent() : false) {
			damage = 1;
			radius = 6;
			if (entity.isInWaterRainOrBubble()) {
				damage = damage * 2;
				radius = radius * 2;
			}
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((radius * 2) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("better_tools:hostile_mobs"))) || (entityiterator instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entity
							|| entityiterator == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null)) {
						if (entity.getPersistentData().getDouble("guardian_necklace_damage_timer") == 0) {
							entityiterator.hurt(
									new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:water_pulse_damage"))), entity),
									(float) (entityiterator instanceof Drowned || entityiterator instanceof LivingEntity _livEnt9 && _livEnt9.getMobType() == MobType.WATER ? damage * 2 : damage));
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.NAUTILUS, (entityiterator.getX()), (entityiterator.getY() + 1), (entityiterator.getZ()), 8, 0.3, 1, 0.3, 0.05);
						}
					}
				}
			}
		}
		if (entity.getPersistentData().getDouble("guardian_necklace_damage_timer") == 0) {
			entity.getPersistentData().putDouble("guardian_necklace_damage_timer", 60);
		} else {
			entity.getPersistentData().putDouble("guardian_necklace_damage_timer", (entity.getPersistentData().getDouble("guardian_necklace_damage_timer") - 1));
		}
	}
}
