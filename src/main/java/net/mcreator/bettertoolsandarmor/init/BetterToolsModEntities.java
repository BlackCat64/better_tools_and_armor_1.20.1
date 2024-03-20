
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.entity.PurpleMooshroomEntity;
import net.mcreator.bettertoolsandarmor.entity.LightningStaffDispenserProjectileEntity;
import net.mcreator.bettertoolsandarmor.entity.IceStaffProjectileFromDispenserEntity;
import net.mcreator.bettertoolsandarmor.entity.IceStaffProjectileEntity;
import net.mcreator.bettertoolsandarmor.entity.FireStaffProjectileEntity;
import net.mcreator.bettertoolsandarmor.entity.FireStaffDispenserProjectileEntity;
import net.mcreator.bettertoolsandarmor.entity.ElectricStaffProjectileEntity;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterToolsModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BetterToolsMod.MODID);
	public static final RegistryObject<EntityType<PurpleMooshroomEntity>> PURPLE_MOOSHROOM = register("purple_mooshroom",
			EntityType.Builder.<PurpleMooshroomEntity>of(PurpleMooshroomEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PurpleMooshroomEntity::new)

					.sized(0.9f, 1.4f));
	public static final RegistryObject<EntityType<ElectricStaffProjectileEntity>> ELECTRIC_STAFF_PROJECTILE = register("projectile_electric_staff_projectile",
			EntityType.Builder.<ElectricStaffProjectileEntity>of(ElectricStaffProjectileEntity::new, MobCategory.MISC).setCustomClientFactory(ElectricStaffProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<IceStaffProjectileEntity>> ICE_STAFF_PROJECTILE = register("projectile_ice_staff_projectile", EntityType.Builder.<IceStaffProjectileEntity>of(IceStaffProjectileEntity::new, MobCategory.MISC)
			.setCustomClientFactory(IceStaffProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<FireStaffProjectileEntity>> FIRE_STAFF_PROJECTILE = register("projectile_fire_staff_projectile", EntityType.Builder.<FireStaffProjectileEntity>of(FireStaffProjectileEntity::new, MobCategory.MISC)
			.setCustomClientFactory(FireStaffProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<FireStaffDispenserProjectileEntity>> FIRE_STAFF_DISPENSER_PROJECTILE = register("projectile_fire_staff_dispenser_projectile",
			EntityType.Builder.<FireStaffDispenserProjectileEntity>of(FireStaffDispenserProjectileEntity::new, MobCategory.MISC).setCustomClientFactory(FireStaffDispenserProjectileEntity::new).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<IceStaffProjectileFromDispenserEntity>> ICE_STAFF_PROJECTILE_FROM_DISPENSER = register("projectile_ice_staff_projectile_from_dispenser",
			EntityType.Builder.<IceStaffProjectileFromDispenserEntity>of(IceStaffProjectileFromDispenserEntity::new, MobCategory.MISC).setCustomClientFactory(IceStaffProjectileFromDispenserEntity::new).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<LightningStaffDispenserProjectileEntity>> LIGHTNING_STAFF_DISPENSER_PROJECTILE = register("projectile_lightning_staff_dispenser_projectile",
			EntityType.Builder.<LightningStaffDispenserProjectileEntity>of(LightningStaffDispenserProjectileEntity::new, MobCategory.MISC).setCustomClientFactory(LightningStaffDispenserProjectileEntity::new).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			PurpleMooshroomEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(PURPLE_MOOSHROOM.get(), PurpleMooshroomEntity.createAttributes().build());
	}
}
