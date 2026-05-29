package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class FlamingCircletProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingDamageEvent.Post event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (true) {
			if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).flaming_circlet_cooldown == 0 && !sourceentity.fireImmune()) {
				{
					BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
					_vars.flaming_circlet_cooldown = 200;
					_vars.syncPlayerVariables(entity);
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(BetterToolsModItems.NETHER_DIAMOND.get(), 200);
				if ((entity.level().dimension()) == Level.NETHER) {
					sourceentity.igniteForSeconds(6);
				} else {
					sourceentity.igniteForSeconds(3);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(sourceentity.getX(), sourceentity.getY(), sourceentity.getZ()), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.blaze.shoot")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound((sourceentity.getX()), (sourceentity.getY()), (sourceentity.getZ()), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.blaze.shoot")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			}
		}
	}
}