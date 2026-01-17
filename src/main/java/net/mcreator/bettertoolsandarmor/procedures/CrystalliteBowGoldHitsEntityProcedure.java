package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteBowGoldHitsEntityProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource().getDirectEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity immediatesourceentity) {
		execute(null, world, immediatesourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		if (immediatesourceentity instanceof Arrow && immediatesourceentity.getPersistentData().getBoolean("crystallite_gold_upgrade")) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3((immediatesourceentity.getX()), (immediatesourceentity.getY()), (immediatesourceentity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
								.withSuppressedOutput(),
						"summon minecraft:firework_rocket ~ ~ ~ {FireworksItem:{id:\"minecraft:firework_rocket\",Count:1b,tag:{Fireworks:{Flight:1b,Explosions:[{Type:0b,Colors:[I;16645946,13426154],Flicker:0b,Trail:0b}]}}}}");
			if (!immediatesourceentity.level().isClientSide())
				immediatesourceentity.discard();
		}
	}
}
