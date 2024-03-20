
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.HoeItem;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class EndTitaniumHoeItem extends HoeItem {
	public EndTitaniumHoeItem() {
		super(new Tier() {
			public int getUses() {
				return 2700;
			}

			public float getSpeed() {
				return 12f;
			}

			public float getAttackDamageBonus() {
				return 1f;
			}

			public int getLevel() {
				return 4;
			}

			public int getEnchantmentValue() {
				return 18;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.END_TITANIUM_INGOT.get()));
			}
		}, 0, 0f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A75Mines Purpur instantly"));
	}
}
