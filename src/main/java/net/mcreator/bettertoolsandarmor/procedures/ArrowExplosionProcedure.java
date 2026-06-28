package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.component.DataComponents;

public class ArrowExplosionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity arrow, Entity entity, Entity player) {
		if (arrow == null || entity == null || player == null)
			return;
		if ((entity.level().dimension()) == Level.NETHER) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(player, x, y, z, 4, Level.ExplosionInteraction.TNT);
		} else {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(player, x, y, z, (float) 2.5, Level.ExplosionInteraction.TNT);
		}
		ItemStack arrowItem = ((Arrow) arrow).getPickupItemStackOrigin();
		PotionContents potion = arrowItem.get(DataComponents.POTION_CONTENTS);
		System.out.println("Trying to spawn potion cloud - " + potion);
		if (potion != null && potion.potion().isPresent()) {
			if (world instanceof ServerLevel _level) {
				AreaEffectCloud cloud = EntityType.AREA_EFFECT_CLOUD.create(_level);
				cloud.moveTo(x, y, z);
				cloud.setDuration(600);
				cloud.setDurationOnUse(0);
				cloud.setPotionContents(new PotionContents(potion.potion().get()));
				cloud.setRadius(3.0F);
				cloud.setRadiusOnUse(-0.5F);
				cloud.setRadiusPerTick(-0.005F);
				cloud.setWaitTime(0);
				_level.addFreshEntity(cloud);
				System.out.println("Summoned potion cloud");
			}
		}
		if (false) {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal((player.getDisplayName().getString())), false);
			}
		}
	}
}