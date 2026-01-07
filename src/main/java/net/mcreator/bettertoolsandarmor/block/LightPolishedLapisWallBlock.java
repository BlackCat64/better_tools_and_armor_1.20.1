
package net.mcreator.bettertoolsandarmor.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class LightPolishedLapisWallBlock extends WallBlock {
	public LightPolishedLapisWallBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_STEM).sound(SoundType.METAL).strength(2.5f, 6f).requiresCorrectToolForDrops().forceSolidOn());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A73Cyan Metallic Building Block"));
	}
}