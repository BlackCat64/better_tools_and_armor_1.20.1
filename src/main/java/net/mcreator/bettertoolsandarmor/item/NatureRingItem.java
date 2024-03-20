
package net.mcreator.bettertoolsandarmor.item;

import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.SlotContext;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.procedures.CrystalliteChestplateEmeraldProcedureProcedure;
import net.mcreator.bettertoolsandarmor.procedures.CharmCounterIncrementProcedure;
import net.mcreator.bettertoolsandarmor.procedures.CharmCounterDecrementProcedure;

import java.util.List;

public class NatureRingItem extends Item implements ICurioItem {
	public NatureRingItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77When worn:"));
		list.add(Component.literal("\u00A79Regenerate HP naturally"));
	}

	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		CrystalliteChestplateEmeraldProcedureProcedure.execute(slotContext.entity().level, slotContext.entity().getX(), slotContext.entity().getY(), slotContext.entity().getZ(), slotContext.entity());
	}

	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		CharmCounterIncrementProcedure.execute(slotContext.entity());
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		CharmCounterDecrementProcedure.execute(slotContext.entity());
	}
}
