
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.EntityType;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterToolsModAttributes {
	public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, BetterToolsMod.MODID);
	public static final RegistryObject<Attribute> CRITICAL_HIT_MULTIPLIER = REGISTRY.register("critical_hit_multiplier", () -> new RangedAttribute("attribute.better_tools.critical_hit_multiplier", 1.5, 0, 10).setSyncable(true));
	public static final RegistryObject<Attribute> THORNS_DAMAGE = REGISTRY.register("thorns_damage", () -> new RangedAttribute("attribute.better_tools.thorns_damage", 0, 0, 20).setSyncable(true));
	public static final RegistryObject<Attribute> FREEZE_THORNS_CHANCE = REGISTRY.register("freeze_thorns_chance", () -> new RangedAttribute("attribute.better_tools.freeze_thorns_chance", 0, 0, 1).setSyncable(true));
	public static final RegistryObject<Attribute> FREEZE_THORNS_TIME = REGISTRY.register("freeze_thorns_time", () -> new RangedAttribute("attribute.better_tools.freeze_thorns_time", 0, 0, 20000).setSyncable(true));
	public static final RegistryObject<Attribute> ATTACK_FREEZE_CHANCE = REGISTRY.register("attack_freeze_chance", () -> new RangedAttribute("attribute.better_tools.attack_freeze_chance", 0, 0, 1).setSyncable(true));
	public static final RegistryObject<Attribute> ATTACK_FREEZE_TIME = REGISTRY.register("attack_freeze_time", () -> new RangedAttribute("attribute.better_tools.attack_freeze_time", 0, 0, 20000).setSyncable(true));

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, CRITICAL_HIT_MULTIPLIER.get());
		event.getTypes().forEach(entity -> event.add(entity, THORNS_DAMAGE.get()));
		event.getTypes().forEach(entity -> event.add(entity, FREEZE_THORNS_CHANCE.get()));
		event.getTypes().forEach(entity -> event.add(entity, FREEZE_THORNS_TIME.get()));
		event.getTypes().forEach(entity -> event.add(entity, ATTACK_FREEZE_CHANCE.get()));
		event.getTypes().forEach(entity -> event.add(entity, ATTACK_FREEZE_TIME.get()));
	}

	@Mod.EventBusSubscriber
	public static class PlayerAttributesSync {
		@SubscribeEvent
		public static void playerClone(PlayerEvent.Clone event) {
			Player oldPlayer = event.getOriginal();
			Player newPlayer = event.getEntity();
			newPlayer.getAttribute(CRITICAL_HIT_MULTIPLIER.get()).setBaseValue(oldPlayer.getAttribute(CRITICAL_HIT_MULTIPLIER.get()).getBaseValue());
			newPlayer.getAttribute(THORNS_DAMAGE.get()).setBaseValue(oldPlayer.getAttribute(THORNS_DAMAGE.get()).getBaseValue());
			newPlayer.getAttribute(FREEZE_THORNS_CHANCE.get()).setBaseValue(oldPlayer.getAttribute(FREEZE_THORNS_CHANCE.get()).getBaseValue());
			newPlayer.getAttribute(FREEZE_THORNS_TIME.get()).setBaseValue(oldPlayer.getAttribute(FREEZE_THORNS_TIME.get()).getBaseValue());
			newPlayer.getAttribute(ATTACK_FREEZE_CHANCE.get()).setBaseValue(oldPlayer.getAttribute(ATTACK_FREEZE_CHANCE.get()).getBaseValue());
			newPlayer.getAttribute(ATTACK_FREEZE_TIME.get()).setBaseValue(oldPlayer.getAttribute(ATTACK_FREEZE_TIME.get()).getBaseValue());
		}
	}
}
