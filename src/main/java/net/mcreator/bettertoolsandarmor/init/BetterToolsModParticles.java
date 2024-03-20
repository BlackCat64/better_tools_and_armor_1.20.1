
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.bettertoolsandarmor.client.particle.OreLocationParticleParticle;
import net.mcreator.bettertoolsandarmor.client.particle.IceParticleParticle;
import net.mcreator.bettertoolsandarmor.client.particle.GuardianStaffBeamParticle;
import net.mcreator.bettertoolsandarmor.client.particle.CrystalliteSparkleParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BetterToolsModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(BetterToolsModParticleTypes.CRYSTALLITE_SPARKLE.get(), CrystalliteSparkleParticle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.ICE_PARTICLE.get(), IceParticleParticle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.ORE_LOCATION_PARTICLE.get(), OreLocationParticleParticle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.GUARDIAN_STAFF_BEAM.get(), GuardianStaffBeamParticle::provider);
	}
}
