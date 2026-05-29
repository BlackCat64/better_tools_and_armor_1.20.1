package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class FlintBlockwoodBlock extends Block {
	public FlintBlockwoodBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(1f).instrument(NoteBlockInstrument.BASEDRUM));
	}
}