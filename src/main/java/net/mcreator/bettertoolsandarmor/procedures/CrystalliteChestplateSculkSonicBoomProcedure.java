package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteChestplateSculkSonicBoomProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		double distance = 0;
		double count = 0;
		double change_x = 0;
		double change_y = 0;
		double change_z = 0;
		double chance = 0;
		if (!BetterToolsModVariables.being_damaged_flag) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_CHESTPLATE.get()) {
				if (!(sourceentity instanceof Player)) {
					chance = 0.2;
					if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK) != null) {
						chance = chance + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
					}
					if (Math.random() < chance) {
						change_x = sourceentity.getX() - entity.getX();
						change_y = sourceentity.getY() - entity.getY();
						change_z = sourceentity.getZ() - entity.getZ();
						distance = Math.ceil(Math.sqrt(Math.pow(change_x, 2) + Math.pow(change_y, 2) + Math.pow(change_z, 2)));
						if (distance >= 4 && distance <= 40) {
							change_x = change_x / distance;
							change_y = change_y / distance;
							change_z = change_z / distance;
							count = 1;
							for (int index0 = 0; index0 < (int) (distance - 1); index0++) {
								if (world instanceof ServerLevel _level)
									_level.sendParticles(ParticleTypes.SONIC_BOOM, (x + count * change_x), (y + 1 + count * change_y), (z + count * change_z), 1, 0.01, 0.01, 0.01, 0.01);
								count = count + 1;
							}
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.warden.sonic_boom")), SoundSource.PLAYERS, (float) 0.3, (float) 1.5);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.warden.sonic_boom")), SoundSource.PLAYERS, (float) 0.3, (float) 1.5, false);
								}
							}
							BetterToolsModVariables.being_damaged_flag = true;
							sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.SONIC_BOOM), entity), 10);
							BetterToolsModVariables.being_damaged_flag = false;
						}
					}
				}
			}
		}
	}
}
