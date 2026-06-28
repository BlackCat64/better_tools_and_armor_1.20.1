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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.stats.Stats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class CrystalliteBowRubyItem extends BowItem {
	public CrystalliteBowRubyItem() {
		super(new Item.Properties().durability(2400).fireResistant());
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(BetterToolsModItems.RUBY.get())).test(repairitem);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.better_tools.crystallite_bow_ruby.description_0"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_ruby.description_1"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_ruby.description_2"));
	}

	@Override
	public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
		arrow.setBaseDamage(arrow.getBaseDamage() - 0.5);
		return arrow;
	}

	@Override
	public void releaseUsing(ItemStack bowItem, Level world, LivingEntity entity, int time) {
		if (!world.isClientSide() && entity instanceof ServerPlayer player) {
			int useTime = this.getUseDuration(bowItem, player) - time;
			useTime = net.neoforged.neoforge.event.EventHooks.onArrowLoose(bowItem, world, player, useTime, !bowItem.isEmpty());
			float pullingPower = BowItem.getPowerForTime(useTime * 2);
			// Multiply pull time by 2 in calculation, to halve the pull time required for full charge
			if (pullingPower < 0.1)
				return;
			ItemStack arrowItem = player.getProjectile(bowItem);
			if (player.getAbilities().instabuild || arrowItem != ItemStack.EMPTY) {
				List<ItemStack> list = draw(bowItem, arrowItem, player);
				if (world instanceof ServerLevel serverlevel && !list.isEmpty()) {
					this.shoot(serverlevel, player, player.getUsedItemHand(), bowItem, list, pullingPower * 3.0F, 1.0F, pullingPower == 1.0F, null);
				}
				world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + pullingPower * 0.5F);
				player.awardStat(Stats.ITEM_USED.get(this));
			}
		}
	}
}