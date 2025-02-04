package net.mcreator.bettertoolsandarmor.block.sign;

import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraftforge.common.property.Properties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;

public class BlackMetalSignBlock extends StandingSignBlock {
	public BlackMetalSignBlock(Properties pProperties, WoodType pType) {
		super(pProperties, pType);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ModSignBlockEntity(pPos, pState);
	}
}
