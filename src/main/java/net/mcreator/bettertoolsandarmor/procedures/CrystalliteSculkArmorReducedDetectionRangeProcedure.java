package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.Comparator;

@EventBusSubscriber
public class CrystalliteSculkArmorReducedDetectionRangeProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean armor_active = false;
		double armor_pieces = 0;
		double range_multiplier = 0;
		double range_reduction = 0;
		double default_range = 0;
		if (BetterToolsModVariables.MapVariables.get(world).stealth_armor_timer == 0) {
			BetterToolsModVariables.MapVariables.get(world).stealth_armor_timer = 20;
			BetterToolsModVariables.MapVariables.get(world).markSyncDirty();
			armor_pieces = 0;
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
			}
			if (armor_pieces == 4) {
				armor_pieces = armor_pieces + 1;
			}
			if (IsWearingGlassArmorFullSetProcedure.execute(entity) && PlayerHasEnergyVialEquippedProcedure.execute(entity) && EnergyVialActiveArmorPiecesProcedure.execute(entity, GetEquippedVialProcedure.execute()) == 4
					&& EnergyVialActiveProcedure.execute(entity, GetEquippedVialProcedure.execute()) == 1) {
				range_multiplier = 0;
				armor_pieces = 4;
			} else {
				range_multiplier = 1 - 0.1 * armor_pieces;
			}
			{
				final Vec3 _center = new Vec3(x, y, z);
				for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(127 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
					if (entityiterator instanceof Mob && entityiterator instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(Attributes.FOLLOW_RANGE)) {
						if (entityiterator instanceof LivingEntity _entity) {
							_entity.getAttribute(Attributes.FOLLOW_RANGE).removeModifier(ResourceLocation.parse("better_tools:crystallite_sculk_armor_detection_range_reduction"));
						}
						if (armor_pieces > 0 && world.players().size() <= 1) {
							if (range_multiplier > 0) {
								range_reduction = range_multiplier
										* (entityiterator instanceof LivingEntity _livingEntity12 && _livingEntity12.getAttributes().hasAttribute(Attributes.FOLLOW_RANGE) ? _livingEntity12.getAttribute(Attributes.FOLLOW_RANGE).getValue() : 0)
										- (entityiterator instanceof LivingEntity _livingEntity13 && _livingEntity13.getAttributes().hasAttribute(Attributes.FOLLOW_RANGE) ? _livingEntity13.getAttribute(Attributes.FOLLOW_RANGE).getValue() : 0);
							} else {
								range_reduction = 4
										- (entityiterator instanceof LivingEntity _livingEntity14 && _livingEntity14.getAttributes().hasAttribute(Attributes.FOLLOW_RANGE) ? _livingEntity14.getAttribute(Attributes.FOLLOW_RANGE).getValue() : 0);
							}
							if (entityiterator instanceof LivingEntity _entity) {
								AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:crystallite_sculk_armor_detection_range_reduction"), range_reduction, AttributeModifier.Operation.ADD_VALUE);
								if (!_entity.getAttribute(Attributes.FOLLOW_RANGE).hasModifier(modifier.id())) {
									_entity.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(modifier);
								}
							}
						}
					}
				}
			}
		}
	}
}