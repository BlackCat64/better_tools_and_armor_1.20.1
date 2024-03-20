
package net.mcreator.bettertoolsandarmor.item;

import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.SlotContext;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.procedures.SpeedyNecklaceUnequippedProcedure;
import net.mcreator.bettertoolsandarmor.procedures.SpeedyNecklaceEquippedProcedure;

import java.util.List;

public class SpeedyNecklaceItem extends Item implements ICurioItem {
	public SpeedyNecklaceItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77When worn:"));
		list.add(Component.literal("\u00A79+10% Movement Speed"));
	}

	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		SpeedyNecklaceEquippedProcedure.execute(slotContext.entity());
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		SpeedyNecklaceUnequippedProcedure.execute(slotContext.entity());
	}
}
