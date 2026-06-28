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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.entity.CrystallitePrismarineArrowEntity;

import java.util.List;

public class CrystalliteBowPrismarineItem extends BowItem {
	public CrystalliteBowPrismarineItem() {
		super(new Item.Properties().durability(2400).fireResistant());
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.PRISMARINE_SHARD), new ItemStack(Items.PRISMARINE_CRYSTALS)).test(repairitem);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.better_tools.crystallite_bow_prismarine.description_0"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_prismarine.description_1"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_prismarine.description_2"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_prismarine.description_3"));
	}

	@Override
	public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
		if (projectileStack.is(Items.ARROW) && arrow.getOwner() instanceof LivingEntity owner) {
			CompoundTag tag = arrow.saveWithoutId(new CompoundTag());
			CrystallitePrismarineArrowEntity newArrow = new CrystallitePrismarineArrowEntity(arrow.level(), owner);
			newArrow.load(tag);
			//		 Copy data from old arrow to new arrow
			arrow = newArrow;
		}
		arrow.setBaseDamage(arrow.getBaseDamage() + 1.0);
		return arrow;
	}
}