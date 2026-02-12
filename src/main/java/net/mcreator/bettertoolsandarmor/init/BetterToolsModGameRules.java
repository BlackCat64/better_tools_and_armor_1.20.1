
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterToolsModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> DISPLAY_DAMAGE_VALUES = GameRules.register("displayDamageValues", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
	public static final GameRules.Key<GameRules.BooleanValue> DISPLAY_XP_DROP_VALUES = GameRules.register("displayXpDropValues", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
}
