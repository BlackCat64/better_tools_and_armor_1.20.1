package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class BlueMushroomBlockBlock extends Block {
	public BlueMushroomBlockBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).sound(SoundType.WOOD).strength(0.2f).requiresCorrectToolForDrops().ignitedByLava().instrument(NoteBlockInstrument.BASS));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}