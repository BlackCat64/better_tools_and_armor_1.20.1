/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterToolsModAttributes {
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, BetterToolsMod.MODID);
	public static final RegistryObject<Attribute> CRITICALHITMULTIPLIER = ATTRIBUTES.register("critical_hit_multiplier", () -> (new RangedAttribute("attribute." + BetterToolsMod.MODID + ".critical_hit_multiplier", 1.5, 1, 10)).setSyncable(true));
	public static final RegistryObject<Attribute> THORNSDAMAGE = ATTRIBUTES.register("thorns_damage", () -> (new RangedAttribute("attribute." + BetterToolsMod.MODID + ".thorns_damage", 0, 0, 20)).setSyncable(true));
	public static final RegistryObject<Attribute> FIRETHORNSCHANCE = ATTRIBUTES.register("fire_thorns_chance", () -> (new RangedAttribute("attribute." + BetterToolsMod.MODID + ".fire_thorns_chance", 0, 0, 1)).setSyncable(true));
	public static final RegistryObject<Attribute> FIRETHORNSTIME = ATTRIBUTES.register("fire_thorns_time", () -> (new RangedAttribute("attribute." + BetterToolsMod.MODID + ".fire_thorns_time", 0, 0, 20000)).setSyncable(true));
	public static final RegistryObject<Attribute> FREEZETHORNSCHANCE = ATTRIBUTES.register("freeze_thorns_chance", () -> (new RangedAttribute("attribute." + BetterToolsMod.MODID + ".freeze_thorns_chance", 0, 0, 1)).setSyncable(true));
	public static final RegistryObject<Attribute> FREEZETHORNSTIME = ATTRIBUTES.register("freeze_thorns_time", () -> (new RangedAttribute("attribute." + BetterToolsMod.MODID + ".freeze_thorns_time", 0, 0, 20000)).setSyncable(true));
	public static final RegistryObject<Attribute> LIGHTNINGTHORNSCHANCE = ATTRIBUTES.register("lightning_thorns_chance", () -> (new RangedAttribute("attribute." + BetterToolsMod.MODID + ".lightning_thorns_chance", 0, 0, 1)).setSyncable(true));
	public static final RegistryObject<Attribute> ATTACKFREEZECHANCE = ATTRIBUTES.register("attack_freeze_chance", () -> (new RangedAttribute("attribute." + BetterToolsMod.MODID + ".attack_freeze_chance", 0, 0, 1)).setSyncable(true));
	public static final RegistryObject<Attribute> ATTACKFREEZETIME = ATTRIBUTES.register("attack_freeze_time", () -> (new RangedAttribute("attribute." + BetterToolsMod.MODID + ".attack_freeze_time", 0, 0, 20000)).setSyncable(true));

	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ATTRIBUTES.register(FMLJavaModLoadingContext.get().getModEventBus());
		});
	}

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		List<EntityType<? extends LivingEntity>> entityTypes = event.getTypes();
		event.add(EntityType.PLAYER, CRITICALHITMULTIPLIER.get());
		event.add(EntityType.PLAYER, CRITICALHITMULTIPLIER.get());
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Mob.class) || baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, THORNSDAMAGE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Mob.class)) {
				event.add(e, FIRETHORNSCHANCE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Mob.class)) {
				event.add(e, FIRETHORNSTIME.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Mob.class)) {
				event.add(e, FREEZETHORNSCHANCE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Mob.class)) {
				event.add(e, FREEZETHORNSTIME.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Mob.class)) {
				event.add(e, LIGHTNINGTHORNSCHANCE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Mob.class)) {
				event.add(e, ATTACKFREEZECHANCE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Mob.class)) {
				event.add(e, ATTACKFREEZETIME.get());
			}
		});
	}
}
