
package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class KarmaPotionMobEffect extends MobEffect {
	public KarmaPotionMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -8776990);
	}

	@Override
	public String getDescriptionId() {
		return "effect.better_tools.karma_potion";
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
