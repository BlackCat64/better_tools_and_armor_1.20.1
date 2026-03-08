
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.SoundType;

public class PolishedAmethystTrapdoorBlock extends TrapDoorBlock {
	public PolishedAmethystTrapdoorBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_PURPLE).sound(SoundType.METAL).strength(2.5f, 6f).requiresCorrectToolForDrops(), BlockSetType.STONE);
	}
}
