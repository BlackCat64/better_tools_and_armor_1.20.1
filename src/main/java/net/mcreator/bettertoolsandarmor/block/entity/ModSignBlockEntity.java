package net.mcreator.bettertoolsandarmor.block.entity;

import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModSignBlockEntity extends SignBlockEntity {
	public ModSignBlockEntity(BlockPos pPos, BlockState pState) {
		super(, pPos, pState);
	}

	@Override
	public BlockEntityType<?> getType() {
		return 
	}
}
