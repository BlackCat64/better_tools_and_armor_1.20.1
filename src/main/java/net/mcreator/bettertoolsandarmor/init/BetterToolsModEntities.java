
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.entity.PurpleMooshroomEntity;
import net.mcreator.bettertoolsandarmor.entity.LightningStaffDispenserProjectileEntity;
import net.mcreator.bettertoolsandarmor.entity.IceStaffProjectileFromDispenserEntity;
import net.mcreator.bettertoolsandarmor.entity.IceStaffProjectileEntity;
import net.mcreator.bettertoolsandarmor.entity.FireStaffProjectileEntity;
import net.mcreator.bettertoolsandarmor.entity.FireStaffDispenserProjectileEntity;
import net.mcreator.bettertoolsandarmor.entity.ElectricStaffProjectileEntity;
import net.mcreator.bettertoolsandarmor.entity.CrystallitePrismarineArrowEntity;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BetterToolsModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, BetterToolsMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<PurpleMooshroomEntity>> PURPLE_MOOSHROOM = register("purple_mooshroom",
			EntityType.Builder.<PurpleMooshroomEntity>of(PurpleMooshroomEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.9f, 1.4f));
	public static final DeferredHolder<EntityType<?>, EntityType<ElectricStaffProjectileEntity>> ELECTRIC_STAFF_PROJECTILE = register("electric_staff_projectile",
			EntityType.Builder.<ElectricStaffProjectileEntity>of(ElectricStaffProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<IceStaffProjectileEntity>> ICE_STAFF_PROJECTILE = register("ice_staff_projectile",
			EntityType.Builder.<IceStaffProjectileEntity>of(IceStaffProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<FireStaffProjectileEntity>> FIRE_STAFF_PROJECTILE = register("fire_staff_projectile",
			EntityType.Builder.<FireStaffProjectileEntity>of(FireStaffProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<FireStaffDispenserProjectileEntity>> FIRE_STAFF_DISPENSER_PROJECTILE = register("fire_staff_dispenser_projectile",
			EntityType.Builder.<FireStaffDispenserProjectileEntity>of(FireStaffDispenserProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<IceStaffProjectileFromDispenserEntity>> ICE_STAFF_PROJECTILE_FROM_DISPENSER = register("ice_staff_projectile_from_dispenser",
			EntityType.Builder.<IceStaffProjectileFromDispenserEntity>of(IceStaffProjectileFromDispenserEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<LightningStaffDispenserProjectileEntity>> LIGHTNING_STAFF_DISPENSER_PROJECTILE = register("lightning_staff_dispenser_projectile",
			EntityType.Builder.<LightningStaffDispenserProjectileEntity>of(LightningStaffDispenserProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<CrystallitePrismarineArrowEntity>> CRYSTALLITE_PRISMARINE_ARROW = register("crystallite_prismarine_arrow",
			EntityType.Builder.<CrystallitePrismarineArrowEntity>of(CrystallitePrismarineArrowEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		PurpleMooshroomEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(PURPLE_MOOSHROOM.get(), PurpleMooshroomEntity.createAttributes().build());
	}
}
