/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.GameRules;

@EventBusSubscriber
public class BetterToolsModGameRules {
	public static GameRules.Key<GameRules.BooleanValue> DISPLAY_DAMAGE_VALUES;
	public static GameRules.Key<GameRules.BooleanValue> DISPLAY_XP_DROP_VALUES;

	@SubscribeEvent
	public static void registerGameRules(FMLCommonSetupEvent event) {
		DISPLAY_DAMAGE_VALUES = GameRules.register("displayDamageValues", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
		DISPLAY_XP_DROP_VALUES = GameRules.register("displayXpDropValues", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
	}
}