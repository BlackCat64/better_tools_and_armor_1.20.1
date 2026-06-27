package net.mcreator.bettertoolsandarmor.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.procedures.CrystalliteBowGetPullTimeProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.entity.CrystalliteIronArrowEntity;

import java.util.List;

public class CrystalliteBowIronItem extends BowItem {
	public CrystalliteBowIronItem() {
		super(new Item.Properties().durability(2400).fireResistant());
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.IRON_INGOT)).test(repairitem);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.better_tools.crystallite_bow_iron.description_0"));
	}

	@Override
	protected Projectile createProjectile(Level world, LivingEntity player, ItemStack bowItem, ItemStack arrowItemStack, boolean isFullyPulled) {
		ArrowItem arrowitem = arrowItemStack.getItem() instanceof ArrowItem arrowitem1 ? arrowitem1 : (ArrowItem) Items.ARROW;
		AbstractArrow abstractarrow;
		if (arrowItemStack.is(Items.SPECTRAL_ARROW) || arrowItemStack.is(Items.TIPPED_ARROW)) {
			abstractarrow = arrowitem.createArrow(world, arrowItemStack, player, bowItem);
		} else {
			CrystalliteIronArrowEntity ironArrow = new CrystalliteIronArrowEntity(world, player);
			ironArrow.setPickupItemStack(arrowItemStack.copyWithCount(1));
			ironArrow.setWeaponItem(bowItem);
			abstractarrow = ironArrow;
		}
		if (isFullyPulled) {
			abstractarrow.setCritArrow(true);
		}
		return customArrow(abstractarrow, arrowItemStack, bowItem);
	}

	@Override
	public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
		arrow.setBaseDamage(arrow.getBaseDamage() + 1.0);
		int power = getPowerLevel(arrow.level(), weaponStack);
		if (power > 0)
			arrow.setBaseDamage(arrow.getBaseDamage() + 0.5 + (power * 0.5));
		if (getFlameLevel(arrow.level(), weaponStack) > 0)
			arrow.igniteForSeconds(100);

		return arrow;
	}

	@Override
	public void onUseTick(Level world, LivingEntity entity, ItemStack itemstack, int time) {
		CrystalliteBowGetPullTimeProcedure.execute(entity, time);
	}

	private int getPowerLevel(Level level, ItemStack weapon) {
		return EnchantmentHelper.getItemEnchantmentLevel(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.POWER), weapon);
	}

	private int getFlameLevel(Level level, ItemStack weapon) {
		return EnchantmentHelper.getItemEnchantmentLevel(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FLAME), weapon);
	}
}