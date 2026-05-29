package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FallingBlock;

import com.mojang.serialization.MapCodec;

public class SugarBlockBlock extends FallingBlock {
	public static final MapCodec<SugarBlockBlock> CODEC = simpleCodec(properties -> new SugarBlockBlock());

	public MapCodec<SugarBlockBlock> codec() {
		return CODEC;
	}

	public SugarBlockBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).sound(SoundType.SAND).strength(0.4f).instrument(NoteBlockInstrument.SNARE));
	}
}