package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.UUID;
import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class CrystalliteSculkArmorReducedDetectionRangeProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
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
		if (world.dayTime() % 20 == 0) {
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
			if (IsWearingGlassArmorFullSetProcedure.execute(entity) && PlayerHasEnergyVialEquippedProcedure.execute(entity) && EnergyVialActiveArmorPiecesProcedure.execute(entity, GetEquippedVialProcedure.execute(world, entity)) == 4
					&& EnergyVialActiveProcedure.execute(entity, GetEquippedVialProcedure.execute(world, entity)) == 1) {
				range_multiplier = 0;
				armor_pieces = 4;
			} else {
				range_multiplier = 1 - 0.1 * armor_pieces;
			}
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(127 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator instanceof Mob && entity instanceof LivingEntity && ((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE) != null) {
						((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE).removeModifier(UUID.fromString("3cf26020-f66d-4f4f-b808-3c90d2ee141b"));
						((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE).removeModifier(UUID.fromString("6d8ffbd5-c43d-49b0-9218-3a51497ed045"));
						if (armor_pieces > 0 && world.players().size() <= 1) {
							if (range_multiplier > 0) {
								range_reduction = range_multiplier * ((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE).getValue()
										- ((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE).getValue();
							} else {
								range_reduction = 4 - ((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE).getValue();
							}
							((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE)
									.addTransientModifier((new AttributeModifier(UUID.fromString("6d8ffbd5-c43d-49b0-9218-3a51497ed045"), "crystallite_sculk_armor_detection_range_reduction", range_reduction, AttributeModifier.Operation.ADDITION)));
						}
					}
				}
			}
		}
	}
}
