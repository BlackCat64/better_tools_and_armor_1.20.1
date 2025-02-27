package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.common.ToolActions;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteHoePrismarineProcedureProcedure {
	@SubscribeEvent
	public static void onUseHoe(BlockEvent.BlockToolModificationEvent event) {
		if (!event.isSimulated() && event.getToolAction() == ToolActions.HOE_TILL && event.getPlayer() != null) {
			execute(event, event.getContext().getLevel(), event.getContext().getClickedPos().getX(), event.getContext().getClickedPos().getY(), event.getContext().getClickedPos().getZ(), event.getPlayer());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		BlockState current = Blocks.AIR.defaultBlockState();
		boolean aquaculture = false;
		double sx = 0;
		double sz = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_HOE_PRISMARINE.get()) {
			aquaculture = !(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("aquaculture:farmland")) == Blocks.AIR);
			sx = -1;
			sz = -1;
			for (int index0 = 0; index0 < 3; index0++) {
				for (int index1 = 0; index1 < 3; index1++) {
					if (!(sx == 0 && sz == 0)) {
						current = (world.getBlockState(BlockPos.containing(x + sx, y, z + sz)));
						if (!world.getBlockState(BlockPos.containing(x + sx, y + 1, z + sz)).canOcclude()) {
							if (current.getBlock() == Blocks.FARMLAND || (ForgeRegistries.BLOCKS.getKey(current.getBlock()).toString()).equals("aquaculture:farmland") || current.getBlock() == Blocks.DIRT || current.getBlock() == Blocks.GRASS_BLOCK) {
								if (aquaculture) {
									world.setBlock(BlockPos.containing(x + sx, y, z + sz), ForgeRegistries.BLOCKS.getValue(new ResourceLocation("aquaculture:farmland")).defaultBlockState(), 3);
								} else {
									world.setBlock(BlockPos.containing(x + sx, y, z + sz), Blocks.FARMLAND.defaultBlockState(), 3);
									{
										int _value = 7;
										BlockPos _pos = BlockPos.containing(x + sx, y, z + sz);
										BlockState _bs = world.getBlockState(_pos);
										if (_bs.getBlock().getStateDefinition().getProperty("moisture") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
											world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
									}
								}
								if (entity instanceof LivingEntity _entity)
									_entity.swing(InteractionHand.MAIN_HAND, true);
							}
						}
					}
					sx = sx + 1;
				}
				sz = sz + 1;
				sx = -1;
			}
			BetterToolsMod.queueServerWork(2, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.FARMLAND || (ForgeRegistries.BLOCKS.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("aquaculture:farmland")
						|| (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DIRT || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.GRASS_BLOCK) {
					if (!(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("aquaculture:farmland")) == Blocks.AIR)) {
						world.setBlock(BlockPos.containing(x, y, z), ForgeRegistries.BLOCKS.getValue(new ResourceLocation("aquaculture:farmland")).defaultBlockState(), 3);
					} else {
						{
							int _value = 7;
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("moisture") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
								world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
						}
					}
				}
			});
		}
	}
}
