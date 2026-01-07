
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DarkPolishedEmeraldBlockBlock extends Block {
	public DarkPolishedEmeraldBlockBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.METAL).strength(2.5f, 6f).requiresCorrectToolForDrops());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A72Dark Green Metallic Building Block"));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}