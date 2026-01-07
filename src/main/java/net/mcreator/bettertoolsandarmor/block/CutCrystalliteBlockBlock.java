
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class CutCrystalliteBlockBlock extends Block {
	public CutCrystalliteBlockBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.ICE)
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
		return super.canHarvestBlock(state, world, pos, player) && CheckForNetheriteTierToolProcedure.execute(player);
	}
}