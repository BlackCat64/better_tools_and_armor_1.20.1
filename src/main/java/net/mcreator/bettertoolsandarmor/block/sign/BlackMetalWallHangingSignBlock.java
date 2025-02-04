package net.mcreator.bettertoolsandarmor.block.sign;

import net.minecraftforge.common.property.Properties;

import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.WallHangingSignBlock;

public class BlackMetalWallHangingSignBlock extends WallHangingSignBlock {

	public BlackMetalWallHangingSignBlock(Properties pProperties, WoodType pType) {
		super(pProperties, pType)
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ModHangingSignBlockEntity(pPos, pState);
	}
}
