package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModBlocks;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteLeggingsAmethystClimbWallsProcedure {
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
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_LEGGINGS.get()
				&& !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			if (world.getBlockState(BlockPos.containing(x + (entity.getDirection()).getStepX(), y, z + (entity.getDirection()).getStepZ())).isFaceSturdy(world,
					BlockPos.containing(x + (entity.getDirection()).getStepX(), y, z + (entity.getDirection()).getStepZ()), ((entity.getDirection()).getOpposite()))
					|| (world.getBlockState(BlockPos.containing(x + (entity.getDirection()).getStepX(), y, z + (entity.getDirection()).getStepZ()))).is(BlockTags.create(ResourceLocation.parse("better_tools:climbable_with_wall_climbing_leggings")))) {
				if (world.isEmptyBlock(BlockPos.containing(x, y, z)) || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BetterToolsModBlocks.CLIMBABLE_WALL.get()) {
					world.setBlock(BlockPos.containing(x, y, z), BetterToolsModBlocks.CLIMBABLE_WALL.get().defaultBlockState(), 3);
				}
				if ((world.isEmptyBlock(BlockPos.containing(x, y + 1, z)) || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BetterToolsModBlocks.CLIMBABLE_WALL.get())
						&& (world.getBlockState(BlockPos.containing(x + (entity.getDirection()).getStepX(), y + 1, z + (entity.getDirection()).getStepZ())).isFaceSturdy(world,
								BlockPos.containing(x + (entity.getDirection()).getStepX(), y + 1, z + (entity.getDirection()).getStepZ()), ((entity.getDirection()).getOpposite()))
								|| (world.getBlockState(BlockPos.containing(x + (entity.getDirection()).getStepX(), y + 1, z + (entity.getDirection()).getStepZ())))
										.is(BlockTags.create(ResourceLocation.parse("better_tools:climbable_with_wall_climbing_leggings"))))) {
					world.setBlock(BlockPos.containing(x, y + 1, z), BetterToolsModBlocks.CLIMBABLE_WALL.get().defaultBlockState(), 3);
				}
			}
		}
	}
}
