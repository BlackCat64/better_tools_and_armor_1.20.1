
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class PolishedRedstoneTrapdoorBlock extends TrapDoorBlock {
	public PolishedRedstoneTrapdoorBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.FIRE).sound(SoundType.METAL).strength(2.5f, 6f).requiresCorrectToolForDrops(), BlockSetType.STONE);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A7cRed Metallic Building Block"));
	}
}