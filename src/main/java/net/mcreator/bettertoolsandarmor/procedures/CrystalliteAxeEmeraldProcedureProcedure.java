package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteAxeEmeraldProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		double y_distance = 0;
		if (((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_AXE_EMERALD.get()
				|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_AXE_SCULK.get()) && !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)
				&& blockstate.is(BlockTags.create(ResourceLocation.parse("minecraft:logs"))) && !entity.isShiftKeyDown()) {
			y_distance = 0;
			for (int index0 = 0; index0 < 16; index0++) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_AXE_SCULK.get()) {
					if (world instanceof Level _level)
						_level.updateNeighborsAt(BlockPos.containing(x, y + y_distance, z), _level.getBlockState(BlockPos.containing(x, y + y_distance, z)).getBlock());
					{
						BlockPos _pos = BlockPos.containing(x, y + y_distance, z);
						Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 0.5, (y + y_distance) - 0.5, z + 0.5), null);
						world.destroyBlock(_pos, false);
					}
					if (y_distance > 0) {
						if (world instanceof ServerLevel _level) {
							(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {
							});
						}
					}
				}
				y_distance = y_distance + 1;
				if (!((world.getBlockState(BlockPos.containing(x, y + y_distance, z))).getBlock() == blockstate.getBlock())) {
					break;
				}
			}
			if (y_distance > 1) {
				if (event instanceof ICancellableEvent _cancellable) {
					_cancellable.setCanceled(true);
				}
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_AXE_EMERALD.get()) {
					if (world instanceof Level _level)
						_level.updateNeighborsAt(BlockPos.containing(x, (y + y_distance) - 1, z), _level.getBlockState(BlockPos.containing(x, (y + y_distance) - 1, z)).getBlock());
					{
						BlockPos _pos = BlockPos.containing(x, (y + y_distance) - 1, z);
						Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 0.5, (y + y_distance) - 0.5, z + 0.5), null);
						world.destroyBlock(_pos, false);
					}
					if (world instanceof ServerLevel _level) {
						(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {
						});
					}
				}
			}
		}
	}
}
