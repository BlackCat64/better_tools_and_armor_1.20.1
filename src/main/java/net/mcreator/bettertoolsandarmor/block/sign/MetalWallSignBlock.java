package net.mcreator.bettertoolsandarmor.block.sign;

import net.minecraft.world.level.block.WallSignBlock;
import net.minecraftforge.common.property.Properties;
import net.minecraft.world.level.block.state.properties.WoodType;

public class MetalWallSignBlock extends WallSignBlock {
	public MetalWallSignBlock(Properties pProperties, WoodType pType) {
		super(pProperties, pType);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ModSignBlockEntity(pPos, pState);
	}
}
