package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModBlocks;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteLeggingsAmethystClimbWallsProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_LEGGINGS.get()) {
			if (world.getBlockState(BlockPos.containing(x + (entity.getDirection()).getStepX(), y, z + (entity.getDirection()).getStepZ())).isFaceSturdy(world,
					BlockPos.containing(x + (entity.getDirection()).getStepX(), y, z + (entity.getDirection()).getStepZ()), ((entity.getDirection()).getOpposite()))
					&& (world.isEmptyBlock(BlockPos.containing(x, y, z)) || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BetterToolsModBlocks.CLIMBABLE_WALL.get())) {
				world.setBlock(BlockPos.containing(x, y, z), BetterToolsModBlocks.CLIMBABLE_WALL.get().defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y + 1, z), BetterToolsModBlocks.CLIMBABLE_WALL.get().defaultBlockState(), 3);
			}
		}
	}
}
