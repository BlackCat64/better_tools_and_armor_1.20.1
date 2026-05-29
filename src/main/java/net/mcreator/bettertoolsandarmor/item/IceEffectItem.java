package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class IceEffectItem extends Item {
	public IceEffectItem() {
		super(new Item.Properties());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}
}