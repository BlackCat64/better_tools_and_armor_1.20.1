package net.mcreator.bettertoolsandarmor.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class CrystalliteBowSculkItem extends BowItem {
	public CrystalliteBowSculkItem() {
		super(new Item.Properties().durability(3000).fireResistant());
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.ECHO_SHARD)).test(repairitem);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.better_tools.crystallite_bow_sculk.description_0"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_sculk.description_1"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_sculk.description_2"));
	}

	@Override
	public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
		arrow.setBaseDamage(arrow.getBaseDamage() + 1.5);
		CompoundTag data = new CompoundTag();
		arrow.saveWithoutId(data);
		// Save maximum piercing level to the arrow
		data.putByte("PierceLevel", (byte) Byte.MAX_VALUE);
		arrow.load(data);
		return arrow;
	}
}