
package net.mcreator.bettertoolsandarmor.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.util.ForgeSoundType;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class CutCrystalliteBlockBlock extends Block {
	public CutCrystalliteBlockBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.ICE)
				.sound(new ForgeSoundType(1.0f, 1.0f, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("better_tools:crystallite_break")),
						() -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("better_tools:crystallite_step")), () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("better_tools:crystallite_place")),
						() -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("better_tools:crystallite_shimmer")), () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("better_tools:crystallite_break"))))
				.strength(40f, 1200f).lightLevel(s -> 4).requiresCorrectToolForDrops().noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
		return true;
	}

	@Override
	public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.getBlock() == this ? true : super.skipRendering(state, adjacentBlockState, side);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 10;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
			return tieredItem.getTier().getLevel() >= 4;
		else
			return super.canHarvestBlock(state, world, pos, player);
	}
}
