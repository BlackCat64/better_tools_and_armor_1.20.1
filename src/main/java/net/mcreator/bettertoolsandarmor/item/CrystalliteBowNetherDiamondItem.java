package net.mcreator.bettertoolsandarmor.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.procedures.CrystalliteBowNetherDiamondFiredProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class CrystalliteBowNetherDiamondItem extends BowItem {
	private AbstractArrow firedArrow = null;

	public CrystalliteBowNetherDiamondItem() {
		super(new Item.Properties().durability(2400).fireResistant());
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(BetterToolsModItems.NETHER_DIAMOND.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get())).test(repairitem);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.better_tools.crystallite_bow_nether_diamond.description_0"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_nether_diamond.description_1"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_nether_diamond.description_2"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_nether_diamond.description_3"));
	}

	@Override
	public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
		arrow.setBaseDamage(arrow.getBaseDamage() + 1.0);
		arrow.getPersistentData().putBoolean("crystallite_bow_nether_diamond", true);
		this.firedArrow = arrow;
		return arrow;
	}

	@Override
	public void releaseUsing(ItemStack bowItem, Level world, LivingEntity entity, int time) {
		super.releaseUsing(bowItem, world, entity, time);
		if (entity instanceof Player player)
			CrystalliteBowNetherDiamondFiredProcedure.execute(world, this.firedArrow, entity);
	}
}