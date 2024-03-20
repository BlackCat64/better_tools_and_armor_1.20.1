package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class CrystalliteBootsAmethystShockwaveDamageProcedure {
	@SubscribeEvent
	public static void onEntityFall(LivingFallEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getDistance());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double distance) {
		execute(null, world, x, y, z, entity, distance);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, double distance) {
		if (entity == null)
			return;
		double damage = 0;
		double radius = 0;
		if (!BetterToolsModVariables.being_damaged_flag) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_BOOTS.get()) {
				if (Math.ceil(distance) < 3) {
					damage = 0;
					radius = 0;
				} else if (Math.ceil(distance) < 42) {
					damage = Math.floor(distance) - 2;
					radius = Math.floor(0.25 * distance) + 1;
				} else {
					damage = 40;
					radius = 11;
				}
				if (damage > 0) {
					for (int index0 = 0; index0 < 5; index0++) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.CLOUD, x, (y + 0.5), z, (int) (Math.ceil(radius) * 3), (radius * 0.5), 0, (radius * 0.5), 0.0025);
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.amethyst_cluster.fall")), SoundSource.PLAYERS, 5, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.amethyst_cluster.fall")), SoundSource.PLAYERS, 5, 1, false);
						}
					}
					BetterToolsModVariables.being_damaged_flag = true;
					{
						final Vec3 _center = new Vec3(x, y, z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(Math.floor(radius * 2 + 1) / 2d), e -> true).stream()
								.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (!(entityiterator == entity)) {
								if (entityiterator instanceof LivingEntity) {
									if (Math.abs(entityiterator.getY() - entity.getY()) < 1) {
										entityiterator
												.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:shockwave_damage"))),
														entity), (float) damage);
									}
								}
							}
						}
					}
					BetterToolsModVariables.being_damaged_flag = false;
				}
			}
		}
	}
}
