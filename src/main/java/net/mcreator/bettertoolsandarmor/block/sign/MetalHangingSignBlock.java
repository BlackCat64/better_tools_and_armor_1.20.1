package net.mcreator.bettertoolsandarmor.block.sign;

import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraftforge.common.property.Properties;
import org.jline.terminal.spi.Pty;
import org.jline.terminal.spi.Pty;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MetalHangingSignBlock extends CeilingHangingSignBlock {
	public MetalHangingSignBlock(Properties pProperties, WoodType Ptype) {
		super(pProperties, Ptype);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ModHangingSignBlockEntity(pPos, pState);
	}
}
