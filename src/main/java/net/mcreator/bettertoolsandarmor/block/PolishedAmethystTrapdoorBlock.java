
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class PolishedAmethystTrapdoorBlock extends TrapDoorBlock {
	public PolishedAmethystTrapdoorBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_PURPLE).sound(SoundType.METAL).strength(2.5f, 6f).requiresCorrectToolForDrops(), BlockSetType.STONE);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A75Purple Metallic Building Block"));
	}
}