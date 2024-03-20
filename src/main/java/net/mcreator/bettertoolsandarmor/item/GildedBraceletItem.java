
package net.mcreator.bettertoolsandarmor.item;

import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.SlotContext;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.procedures.GildedBraceletUnequippedProcedure;
import net.mcreator.bettertoolsandarmor.procedures.GildedBraceletEquippedProcedure;

import java.util.List;

public class GildedBraceletItem extends Item implements ICurioItem {
	public GildedBraceletItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public boolean makesPiglinsNeutral(SlotContext slotContext, ItemStack stack) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77When worn:"));
		list.add(Component.literal("\u00A79+1 Block Reach"));
		list.add(Component.literal("\u00A79Makes Piglins neutral"));
	}

	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		GildedBraceletEquippedProcedure.execute(slotContext.entity());
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		GildedBraceletUnequippedProcedure.execute(slotContext.entity());
	}
}
