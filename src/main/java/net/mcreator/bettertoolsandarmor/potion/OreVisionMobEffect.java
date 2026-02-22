
package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class OreVisionMobEffect extends MobEffect {
	public OreVisionMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3170061);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
