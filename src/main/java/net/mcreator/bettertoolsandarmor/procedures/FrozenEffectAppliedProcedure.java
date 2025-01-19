package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class FrozenEffectAppliedProcedure {
	public static void execute(double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double speed = 0;
		{
			CompoundTag dataIndex = new CompoundTag();
			entity.saveWithoutId(dataIndex);
			dataIndex.putDouble("Fire", 0);
			entity.load(dataIndex);
		}
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false) && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
			speed = ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue();
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null) {
					_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
							_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("attribute @s minecraft:generic.movement_speed modifier add 06be9690-876a-468f-9d10-25eb7c432664 frozen_effect -" + speed + " add"));
				}
			}
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null) {
					_ent.getServer().getCommands().performPrefixedCommand(
							new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(),
									_ent.level().getServer(), _ent),
							("summon block_display ~ ~ ~ {transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],scale:[" + ("" + entity.getBbWidth() * 1.25) + "f," + ("" + entity.getBbHeight() * 1.1) + "f,"
									+ ("" + entity.getBbWidth() * 1.25) + "f],translation:[-" + ("" + entity.getBbWidth() * 0.625) + "f,0f,-" + ("" + entity.getBbWidth() * 0.625)
									+ "f]},block_state:{Name:ice},ForgeData:{freeze_effect:1b, frozen_entity:\"" + entity.getStringUUID() + "\"}}"));
				}
			}
			entity.getPersistentData().putDouble("frozen_at_x", x);
			entity.getPersistentData().putDouble("frozen_at_y", y);
			entity.getPersistentData().putDouble("frozen_at_z", z);
		}
	}
}
