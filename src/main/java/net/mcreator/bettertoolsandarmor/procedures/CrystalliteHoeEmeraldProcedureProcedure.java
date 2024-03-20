package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

public class CrystalliteHoeEmeraldProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double x_iterator = 0;
		double z_iterator = 0;
		if (blockstate.is(BlockTags.create(new ResourceLocation("minecraft:crops")))) {
			x_iterator = x - 2;
			z_iterator = z - 2;
			for (int index0 = 0; index0 < 5; index0++) {
				for (int index1 = 0; index1 < 5; index1++) {
					if ((world.getBlockState(BlockPos.containing(x_iterator, y, z_iterator))).is(BlockTags.create(new ResourceLocation("minecraft:crops")))) {
						{
							int _value = 7;
							BlockPos _pos = BlockPos.containing(x_iterator, y, z_iterator);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("age") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
								world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
						}
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.HAPPY_VILLAGER, x_iterator, (y + 0.5), z_iterator, 2, 0.5, 0.1, 0.5, 0.1);
					}
					x_iterator = x_iterator + 1;
				}
				z_iterator = z_iterator + 1;
				x_iterator = x - 2;
			}
			if (!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayer _serverPlayer) {
						return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
					} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
						return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
								&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity))) {
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 1200);
				{
					ItemStack _ist = itemstack;
					if (_ist.hurt(5, RandomSource.create(), null)) {
						_ist.shrink(1);
						_ist.setDamageValue(0);
					}
				}
			}
		}
	}
}
