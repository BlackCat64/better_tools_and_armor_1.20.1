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
		double x_offset = 0;
		double z_offset = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_LEGGINGS.get()) {
			if (entity.getYRot() <= 45 && entity.getYRot() > -45) {
				z_offset = 1;
			} else if (entity.getYRot() < 135 && entity.getYRot() > 45) {
				x_offset = -1;
			} else if (Math.abs(entity.getYRot()) >= 135) {
				z_offset = -1;
			} else if (entity.getYRot() <= -45 && entity.getYRot() > -135) {
				x_offset = 1;
			}
			if (world.getBlockState(BlockPos.containing(x + x_offset, y, z + z_offset)).canOcclude()) {
				if (entity.getDeltaMovement().x() != 0 || entity.getDeltaMovement().z() != 0) {
					PlaceClimbableWallProcedure.execute(world, x, y, z);
					PlaceClimbableWallProcedure.execute(world, x, (y + 1), z);
					PlaceClimbableWallProcedure.execute(world, x, (y - 1), z);
					if (entity.getDeltaMovement().y() < -0.5) {
						PlaceClimbableWallProcedure.execute(world, x, (y - 2), z);
					}
					if (x_offset == 0) {
						PlaceClimbableWallProcedure.execute(world, (x - 1), (y - 1), z);
						PlaceClimbableWallProcedure.execute(world, (x - 1), y, z);
						PlaceClimbableWallProcedure.execute(world, (x - 1), (y + 1), z);
						PlaceClimbableWallProcedure.execute(world, (x + 1), (y - 1), z);
						PlaceClimbableWallProcedure.execute(world, (x + 1), y, z);
						PlaceClimbableWallProcedure.execute(world, (x + 1), (y + 1), z);
					} else if (z_offset == 0) {
						PlaceClimbableWallProcedure.execute(world, x, (y - 1), (z - 1));
						PlaceClimbableWallProcedure.execute(world, x, y, (z - 1));
						PlaceClimbableWallProcedure.execute(world, x, (y + 1), (z - 1));
						PlaceClimbableWallProcedure.execute(world, x, (y - 1), (z + 1));
						PlaceClimbableWallProcedure.execute(world, x, y, (z + 1));
						PlaceClimbableWallProcedure.execute(world, x, (y + 1), (z + 1));
					}
				}
			}
		}
	}
}
