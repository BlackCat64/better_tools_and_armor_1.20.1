
package net.mcreator.bettertoolsandarmor.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class EndTitaniumBlockBlock extends Block {
	public EndTitaniumBlockBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.NETHERITE_BLOCK).strength(60f, 1500f).lightLevel(s -> 4));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}
