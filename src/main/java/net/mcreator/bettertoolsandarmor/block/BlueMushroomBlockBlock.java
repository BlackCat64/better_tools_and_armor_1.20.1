
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BlueMushroomBlockBlock extends Block {
	public BlueMushroomBlockBlock() {
		super(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.DIAMOND).sound(SoundType.WOOD).strength(0.2f).requiresCorrectToolForDrops());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}