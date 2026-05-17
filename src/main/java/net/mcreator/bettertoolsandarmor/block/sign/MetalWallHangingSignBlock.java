package net.mcreator.bettertoolsandarmor.block.sign;

import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.block.entity.MetalHangingSignBlockEntity;

import javax.annotation.Nullable;

public class MetalWallHangingSignBlock extends WallHangingSignBlock {
	public MetalWallHangingSignBlock(BlockBehaviour.Properties pProperties, WoodType pType) {
		super(pType, pProperties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new MetalHangingSignBlockEntity(pPos, pState);
	}

	@Override
	public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
		return SoundType.CHAIN;
	}
}
