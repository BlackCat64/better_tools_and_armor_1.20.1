package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class CrystaliteGemItem extends Item {
	public CrystaliteGemItem() {
		super(new Item.Properties().fireResistant());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}
}