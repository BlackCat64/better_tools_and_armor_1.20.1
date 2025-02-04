package net.mcreator.bettertoolsandarmor.block.sign;

import net.minecraft.world.level.block.WallSignBlock;
import net.minecraftforge.common.property.Properties;
import net.minecraft.world.level.block.state.properties.WoodType;

public class BlackMetalWallSignBlock extends WallSignBlock {
	public BlackMetalWallSignBlock(Properties pProperties, WoodType pType) {
		super(pProperties, pType);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ModSignBlockEntity(pPos, pState);
	}
}
