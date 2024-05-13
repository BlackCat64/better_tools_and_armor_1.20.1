
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterToolsModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> DISPLAY_DAMAGE_VALUES = GameRules.register("displayDamageValues", GameRules.Category.MISC, GameRules.BooleanValue.create(false));
}
