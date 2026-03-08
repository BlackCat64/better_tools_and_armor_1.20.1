
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class LightPolishedCoalWallBlock extends WallBlock {
	public LightPolishedCoalWallBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(2.5f, 6f).requiresCorrectToolForDrops().forceSolidOn());
	}
}
