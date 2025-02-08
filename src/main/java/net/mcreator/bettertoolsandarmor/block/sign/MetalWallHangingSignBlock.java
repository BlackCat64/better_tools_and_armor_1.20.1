package net.mcreator.bettertoolsandarmor.block.sign;

import net.minecraftforge.common.property.Properties;

import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.block.entity.MetalHangingSignBlockEntity;

public class MetalWallHangingSignBlock extends WallHangingSignBlock {
	public MetalWallHangingSignBlock(Properties pProperties, WoodType pType) {
		super(pProperties, pType);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new MetalHangingSignBlockEntity(pPos, pState);
	}
}
