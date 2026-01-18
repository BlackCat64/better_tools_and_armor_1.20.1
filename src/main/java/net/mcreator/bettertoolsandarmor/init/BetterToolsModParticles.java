
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
import net.mcreator.bettertoolsandarmor.client.particle.FreezeBoom6Particle;
import net.mcreator.bettertoolsandarmor.client.particle.FreezeBoom5Particle;
import net.mcreator.bettertoolsandarmor.client.particle.FreezeBoom4Particle;
import net.mcreator.bettertoolsandarmor.client.particle.FreezeBoom3Particle;
import net.mcreator.bettertoolsandarmor.client.particle.FreezeBoom2Particle;
import net.mcreator.bettertoolsandarmor.client.particle.CrystalliteThornsParticleParticle;
import net.mcreator.bettertoolsandarmor.client.particle.CrystalliteSplashDamageParticle;
import net.mcreator.bettertoolsandarmor.client.particle.CrystalliteSparkleParticle;
import net.mcreator.bettertoolsandarmor.client.particle.ArrowHomingParticleParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BetterToolsModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(BetterToolsModParticleTypes.CRYSTALLITE_SPARKLE.get(), CrystalliteSparkleParticle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.ICE_PARTICLE.get(), IceParticleParticle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.ORE_LOCATION_PARTICLE.get(), OreLocationParticleParticle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.GUARDIAN_STAFF_BEAM.get(), GuardianStaffBeamParticle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.CRYSTALLITE_SPLASH_DAMAGE.get(), CrystalliteSplashDamageParticle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.CRYSTALLITE_THORNS_PARTICLE.get(), CrystalliteThornsParticleParticle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.ARROW_HOMING_PARTICLE.get(), ArrowHomingParticleParticle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.FREEZE_BOOM_2.get(), FreezeBoom2Particle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.FREEZE_BOOM_3.get(), FreezeBoom3Particle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.FREEZE_BOOM_4.get(), FreezeBoom4Particle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.FREEZE_BOOM_5.get(), FreezeBoom5Particle::provider);
		event.registerSpriteSet(BetterToolsModParticleTypes.FREEZE_BOOM_6.get(), FreezeBoom6Particle::provider);
	}
}
