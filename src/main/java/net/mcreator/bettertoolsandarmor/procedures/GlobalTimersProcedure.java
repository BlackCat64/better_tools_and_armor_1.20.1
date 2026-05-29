package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class GlobalTimersProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
			_vars.time_since_last_hurt = SafeIncrementProcedure.execute(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_hurt);
			_vars.time_since_last_attacked = SafeIncrementProcedure.execute(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_attacked);
			_vars.time_since_last_mined = SafeIncrementProcedure.execute(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_mined);
			_vars.time_since_last_jumped = SafeIncrementProcedure.execute(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_jumped);
			_vars.time_since_non_carbonated_food_eaten = SafeIncrementProcedure.execute(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_non_carbonated_food_eaten);
			_vars.time_since_shot_bow = SafeIncrementProcedure.execute(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_shot_bow);
			_vars.markSyncDirty();
		}
		if (entity.onGround()) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.time_since_on_ground = 0;
				_vars.markSyncDirty();
			}
		} else {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.time_since_on_ground = SafeIncrementProcedure.execute(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_on_ground);
				_vars.markSyncDirty();
			}
		}
		if (entity.isOnFire() && !(entity.isInLava() || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.FIRE || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SOUL_FIRE)) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.time_on_fire = SafeIncrementProcedure.execute(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_on_fire);
				_vars.markSyncDirty();
			}
		} else {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.time_on_fire = 0;
				_vars.markSyncDirty();
			}
		}
		{
			BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
			_vars.save_from_void_cooldown = Math.max(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).save_from_void_cooldown - 1, 0);
			_vars.ender_titanium_boots_cooldown = Math.max(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).ender_titanium_boots_cooldown - 1, 0);
			_vars.flaming_circlet_cooldown = Math.max(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).flaming_circlet_cooldown - 1, 0);
			_vars.crystallite_emerald_heal_timer = Math.max(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_emerald_heal_timer - 1, 0);
			_vars.crystallite_honey_absorption_timer = Math.max(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_honey_absorption_timer - 1, 0);
			_vars.nether_diamond_armor_fire_res_cooldown = Math.max(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).nether_diamond_armor_fire_res_cooldown - 1, 0);
			_vars.crystallite_redstone_sword_heal_cooldown = Math.max(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_redstone_sword_heal_cooldown - 1, 0);
			_vars.crystallite_amethyst_ore_highlight_cooldown = Math.max(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_amethyst_ore_highlight_cooldown - 1, 0);
			_vars.markSyncDirty();
		}
		BetterToolsModVariables.MapVariables.get(world).crystallite_shimmer_timer = Math.max(BetterToolsModVariables.MapVariables.get(world).crystallite_shimmer_timer - 1, 0);
		BetterToolsModVariables.MapVariables.get(world).stealth_armor_timer = Math.max(BetterToolsModVariables.MapVariables.get(world).stealth_armor_timer - 1, 0);
		BetterToolsModVariables.MapVariables.get(world).markSyncDirty();
	}
}