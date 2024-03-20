
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.procedures.CrystallitePickaxePrismarineProcedureProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class CrystallitePickaxePrismarineItem extends PickaxeItem {
	public CrystallitePickaxePrismarineItem() {
		super(new Tier() {
			public int getUses() {
				return 1800;
			}

			public float getSpeed() {
				return 10f;
			}

			public float getAttackDamageBonus() {
				return 6f;
			}

			public int getLevel() {
				return 5;
			}

			public int getEnchantmentValue() {
				return 20;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.PRISMARINE_SHARD), new ItemStack(Items.PRISMARINE_CRYSTALS));
			}
		}, 1, -2.8f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77Upgrade: \u00A73Prismarine"));
		list.add(Component.literal("\u00A77Abilities:"));
		list.add(Component.literal("\u00A73Aqua Affinity - Mines faster when wet"));
		list.add(Component.literal("\u00A73Removes underwater mining penalty"));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			CrystallitePickaxePrismarineProcedureProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
	}
}
