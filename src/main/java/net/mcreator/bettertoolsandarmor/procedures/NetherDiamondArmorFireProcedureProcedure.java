package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class NetherDiamondArmorFireProcedureProcedure {
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
		double time = 0;
		double chance = 0;
		time = 5;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.NETHER_DIAMOND_BOOTS.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.05;
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_BOOTS.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.1;
			time = 10;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.NETHER_DIAMOND_LEGGINGS.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.05;
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_LEGGINGS.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.1;
			time = 10;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.NETHER_DIAMOND_CHESTPLATE.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.05;
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_CHESTPLATE.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.1;
			time = 10;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.NETHER_DIAMOND_HELMET.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.05;
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_HELMET.get()) {
			armor_pieces = armor_pieces + 1;
			chance = chance + 0.1;
			time = 10;
		}
		if (armor_pieces == 4) {
			if (time == 10) {
				chance = chance + 0.1;
			} else {
				chance = chance + 0.05;
			}
		}
		if ((entity.level().dimension()) == Level.NETHER) {
			if (time == 10) {
				chance = chance * 1.5;
				time = time * 1.5;
			} else {
				chance = chance * 2;
				time = time * 2;
			}
		}
		if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK) != null) {
			if (chance > 0) {
				chance = chance + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
			}
		}
		if (Math.random() < chance) {
			sourceentity.setSecondsOnFire((int) time);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot")), SoundSource.NEUTRAL, (float) 0.75, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot")), SoundSource.NEUTRAL, (float) 0.75, 1, false);
				}
			}
		}
	}
}
