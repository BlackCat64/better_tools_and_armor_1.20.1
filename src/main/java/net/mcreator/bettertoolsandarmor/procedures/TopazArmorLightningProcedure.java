package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TopazArmorLightningProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
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
		double armor_pieces = 0;
		double chance = 0;
		boolean crystallite_worn = false;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_BOOTS.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.05;
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_BOOTS.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.1;
			crystallite_worn = true;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_LEGGINGS.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.05;
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_LEGGINGS.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.1;
			crystallite_worn = true;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_CHESTPLATE.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.05;
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_CHESTPLATE.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.1;
			crystallite_worn = true;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_HELMET.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.05;
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_HELMET.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.1;
			crystallite_worn = true;
		}
		if (armor_pieces == 4) {
			if (crystallite_worn) {
				chance = chance + 0.1;
			} else {
				chance = chance + 0.05;
			}
		}
		if (world.getLevelData().isThundering()) {
			if (crystallite_worn) {
				chance = chance * 1.5;
			} else {
				chance = chance * 2;
			}
		}
		if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK) != null) {
			if (chance > 0) {
				chance = chance + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
			}
		}
		if (Math.random() < chance) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.containing(sourceentity.getX(), sourceentity.getY(), sourceentity.getZ()), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.elder_guardian.curse")), SoundSource.NEUTRAL, (float) 0.75, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.elder_guardian.curse")), SoundSource.NEUTRAL, (float) 0.75, 1, false);
				}
			}
			if (armor_pieces >= 4) {
				if (entity instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:topaz_adv"));
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
	}
}
