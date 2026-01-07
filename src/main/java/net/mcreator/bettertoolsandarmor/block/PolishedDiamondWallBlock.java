
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class PolishedDiamondWallBlock extends WallBlock {
	public PolishedDiamondWallBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).sound(SoundType.METAL).strength(2.5f, 6f).requiresCorrectToolForDrops().forceSolidOn());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A7bLight Blue Metallic Building Block"));
	}
}