package net.mcreator.bettertoolsandarmor.block.sign;

import net.minecraftforge.common.property.Properties;

import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.block.entity.MetalHangingSignBlockEntity;

public class MetalHangingSignBlock extends CeilingHangingSignBlock {
	public MetalHangingSignBlock(Properties pProperties, WoodType Ptype) {
		super(pProperties, Ptype);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new MetalHangingSignBlockEntity(pPos, pState);
	}
}
