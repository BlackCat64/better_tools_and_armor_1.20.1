package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class JewelleryTableBlock extends Block {
	public JewelleryTableBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).sound(SoundType.NETHERITE_BLOCK).strength(3f).ignitedByLava().instrument(NoteBlockInstrument.BASS));
	}
}