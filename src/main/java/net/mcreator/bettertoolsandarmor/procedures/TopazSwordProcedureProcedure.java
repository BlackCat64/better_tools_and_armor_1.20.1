package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class TopazSwordProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource(), event.getEntity(), event.getSource().getDirectEntity(), event.getSource().getEntity(),
					event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		execute(null, world, x, y, z, damagesource, entity, immediatesourceentity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		if (damagesource == null || entity == null || immediatesourceentity == null || sourceentity == null)
			return;
		Entity closest = null;
		boolean continue_chain = false;
		boolean success = false;
		double chance = 0;
		double range = 0;
		double particle_x = 0;
		double particle_y = 0;
		double particle_z = 0;
		double increment_x = 0;
		double increment_y = 0;
		double increment_z = 0;
		double chain_count = 0;
		if (!damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:electric_chain_damage")))) {
			if ((immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:lightning_tools")))) {
				if ((immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:upgraded_crystallite_tools")))) {
					chance = 0.5;
				} else {
					chance = 0.25;
				}
				range = 3;
				if (IsInThunderstormProcedure.execute(world, x, y, z)) {
					chance = chance * 1.5;
					range = range * 2;
				}
				if (entity instanceof LivingEntity && ((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK) != null) {
					chance = chance + ((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
				}
				closest = entity;
				if (Math.random() < chance) {
					continue_chain = true;
					chain_count = 1;
				}
				while (closest instanceof LivingEntity && continue_chain) {
					closest.getPersistentData().putBoolean("can_electric_chain", false);
					success = false;
					{
						final Vec3 _center = new Vec3((closest.getX()), (closest.getY()), (closest.getZ()));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((range * 2) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator.getPersistentData().getBoolean("can_electric_chain") && entityiterator instanceof LivingEntity && !(entityiterator == sourceentity || entityiterator == closest)) {
								success = true;
								increment_x = (entityiterator.getX() - closest.getX()) / 16;
								increment_y = (entityiterator.getY() - closest.getY()) / 16;
								increment_z = (entityiterator.getZ() - closest.getZ()) / 16;
								particle_x = closest.getX();
								particle_y = closest.getY() + closest.getBbHeight() / 2;
								particle_z = closest.getZ();
								for (int index1 = 0; index1 < 16; index1++) {
									if (world instanceof ServerLevel _level)
										_level.sendParticles(ParticleTypes.WAX_ON, particle_x, particle_y, particle_z, 1, 0.1, 0.1, 0.1, 0.005);
									particle_x = particle_x + increment_x;
									particle_y = particle_y + increment_y;
									particle_z = particle_z + increment_z;
								}
								entityiterator
										.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:electric_chain_damage"))),
												sourceentity), (float) (amount / 2));
								if (world instanceof ServerLevel _level)
									_level.sendParticles(ParticleTypes.ELECTRIC_SPARK, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 16, 0.25, 1, 0.25, 0.01);
								if (chain_count >= 2) {
									if (sourceentity instanceof ServerPlayer _player) {
										Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:topaz_sword_adv"));
										AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
										if (!_ap.isDone()) {
											for (String criteria : _ap.getRemainingCriteria())
												_player.getAdvancements().award(_adv, criteria);
										}
									}
								}
								closest = entityiterator;
								break;
							}
						}
					}
					if (!success) {
						break;
					}
					if (Math.random() < chance) {
						continue_chain = true;
						chain_count = chain_count + 1;
					} else {
						continue_chain = false;
					}
				}
			}
		}
	}
}
