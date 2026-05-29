package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteHelmetHoneyStickToCeilingProcedure {
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
		if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).stick_to_ceiling
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:sticky_helmets"))) && !entity.onGround()
				&& world.getBlockState(BlockPos.containing(x, y + 2, z)).isFaceSturdy(world, BlockPos.containing(x, y + 2, z), Direction.DOWN)) {
			if (false) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 2, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.honey_block.place")), SoundSource.PLAYERS, (float) 0.75, 1);
					} else {
						_level.playLocalSound(x, (y + 2), z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.honey_block.place")), SoundSource.PLAYERS, (float) 0.75, 1, false);
					}
				}
			}
			if (Math.random() < 0.2) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.FALLING_HONEY, x, (y + 1.9), z, 1, 0.5, 0.5, 0.5, 0.025);
			}
		}
	}
}