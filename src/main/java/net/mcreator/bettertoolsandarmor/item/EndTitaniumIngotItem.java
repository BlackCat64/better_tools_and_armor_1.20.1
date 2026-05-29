package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;

public class EndTitaniumIngotItem extends Item {
	public EndTitaniumIngotItem() {
		super(new Item.Properties().fireResistant());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}

	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.EMPTY;
	}
}