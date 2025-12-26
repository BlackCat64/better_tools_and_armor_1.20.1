package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteHoeSapphirePlantClearanceProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double i_x = 0;
		double i_y = 0;
		double i_z = 0;
		if (IsPlantProcedure.execute(world, x, y, z) && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:plant_clearance_tools")))
				&& !entity.isShiftKeyDown()) {
			i_x = -2;
			for (int index0 = 0; index0 < 5; index0++) {
				i_y = -2;
				for (int index1 = 0; index1 < 5; index1++) {
					i_z = -2;
					for (int index2 = 0; index2 < 5; index2++) {
						if (IsPlantProcedure.execute(world, x + i_x, y + i_y, z + i_z)) {
							{
								BlockPos _pos = BlockPos.containing(x + i_x, y + i_y, z + i_z);
								Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + i_x + 0.5, y + i_y + 0.5, z + i_z + 0.5), null);
								world.destroyBlock(_pos, false);
							}
						}
						i_z = i_z + 1;
					}
					i_y = i_y + 1;
				}
				i_x = i_x + 1;
			}
		}
	}
}
