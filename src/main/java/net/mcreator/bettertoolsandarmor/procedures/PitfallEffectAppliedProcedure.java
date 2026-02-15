package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

import java.util.UUID;

public class PitfallEffectAppliedProcedure {
	public static void execute(double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double speed = 0;
		if ((entity instanceof Mob || entity instanceof Player) && !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false) && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.LEVITATION);
			if (entity.onGround()) {
				if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).hasModifier((new AttributeModifier(UUID.fromString("585176c4-a1ed-4d0e-995c-f5ca0cb3843c"), "trapped_in_ground",
						(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue() * (-1)), AttributeModifier.Operation.ADDITION)))))
					((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).addTransientModifier((new AttributeModifier(UUID.fromString("585176c4-a1ed-4d0e-995c-f5ca0cb3843c"), "trapped_in_ground",
							(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue() * (-1)), AttributeModifier.Operation.ADDITION)));
				entity.clearFire();
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(),
										_ent.level().getServer(), _ent),
								("summon block_display ~ ~ ~ {transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],scale:[" + ("" + entity.getBbWidth() * 1.25) + "f,0.5f," + ("" + entity.getBbWidth() * 1.25) + "f],translation:[-"
										+ ("" + entity.getBbWidth() * 0.625) + "f,0f,-" + ("" + entity.getBbWidth() * 0.625) + "f]},block_state:{Name:mud},ForgeData:{trapped_in_ground:1b,trapped_entity:\"" + entity.getStringUUID() + "\"}}"));
					}
				}
				entity.getPersistentData().putDouble("frozen_at_x", x);
				entity.getPersistentData().putDouble("frozen_at_y", y);
				entity.getPersistentData().putDouble("frozen_at_z", z);
			} else {
				entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), (-2), (entity.getDeltaMovement().z())));
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(BetterToolsModMobEffects.PITFALL.get());
			}
		}
	}
}
