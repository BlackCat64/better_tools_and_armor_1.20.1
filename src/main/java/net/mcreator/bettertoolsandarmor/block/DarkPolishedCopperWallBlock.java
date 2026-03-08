
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class DarkPolishedCopperWallBlock extends WallBlock {
	public DarkPolishedCopperWallBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.PODZOL).sound(SoundType.METAL).strength(2.5f, 6f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
