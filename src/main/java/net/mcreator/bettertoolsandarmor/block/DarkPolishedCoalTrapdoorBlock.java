
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DarkPolishedCoalTrapdoorBlock extends TrapDoorBlock {
	public DarkPolishedCoalTrapdoorBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).sound(SoundType.METAL).strength(2.5f, 6f).requiresCorrectToolForDrops(), BlockSetType.STONE);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A78Black Metallic Building Block"));
	}
}