package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class ArrowExplosionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity arrow = null;
		String potion = "";
		String uuid = "";
		if ((entity.level().dimension()) == Level.NETHER) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, y, z, 4, Level.ExplosionInteraction.TNT);
		} else {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, y, z, (float) 2.5, Level.ExplosionInteraction.TNT);
		}
		potion = GetEntityTextDataProcedure.execute(entity, "Potion");
		if (!(potion).isEmpty()) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						("summon minecraft:area_effect_cloud ~ ~ ~ {Duration:600,DurationOnUse:0,Potion:\"" + "" + potion
								+ "\",Particle:\"minecraft:entity_effect\",Radius:3.0f,RadiusOnUse:-0.5f,RadiusPerTick:-0.005f,ReapplicationDelay:20,WaitTime:0}"));
		}
	}
}
