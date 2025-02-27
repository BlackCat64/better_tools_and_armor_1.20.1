package net.mcreator.bettertoolsandarmor.block.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;

public class MetalHangingSignBlockEntity extends HangingSignBlockEntity {
	public MetalHangingSignBlockEntity(BlockPos pPos, BlockState pState) {
		super(pPos, pState);
	}

	@Override
	public BlockEntityType<?> getType() {
		return BetterToolsModBlockEntities.METAL_HANGING_SIGN.get();
	}
}
