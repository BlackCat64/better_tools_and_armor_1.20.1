package net.mcreator.bettertoolsandarmor.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Unit;
import net.minecraft.stats.Stats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class CrystalliteBowEmeraldItem extends BowItem {
	public CrystalliteBowEmeraldItem() {
		super(new Item.Properties().durability(2400).fireResistant());
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.EMERALD)).test(repairitem);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.better_tools.crystallite_bow_emerald.description_0"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_emerald.description_1"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_emerald.description_2"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_emerald.description_3"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		ItemStack itemstack = entity.getItemInHand(hand);
		boolean flag = !entity.getProjectile(itemstack).isEmpty();
		flag = true; // Changing this flag to true gives INFINITE AMMO
		InteractionResultHolder<ItemStack> ret = net.neoforged.neoforge.event.EventHooks.onArrowNock(itemstack, world, entity, hand, flag);
		if (ret != null)
			return ret;
		if (!entity.hasInfiniteMaterials() && !flag) {
			return InteractionResultHolder.fail(itemstack);
		} else {
			entity.startUsingItem(hand);
			return InteractionResultHolder.consume(itemstack);
		}
	}

	@Override
	public void releaseUsing(ItemStack bowItem, Level world, LivingEntity entity, int time) {
		if (!world.isClientSide() && entity instanceof ServerPlayer player) {
			int useTime = this.getUseDuration(bowItem, player) - time;
			useTime = net.neoforged.neoforge.event.EventHooks.onArrowLoose(bowItem, world, player, useTime, !bowItem.isEmpty());
			float pullingPower = BowItem.getPowerForTime(useTime);
			if (pullingPower < 0.1)
				return;
			ItemStack arrowItem = player.getProjectile(bowItem);
			boolean infinite = false;
			if (arrowItem.isEmpty()) {
				arrowItem = new ItemStack(Items.ARROW);
				// Create a 'fake arrow' which doesn't get used up, if no arrows available
				infinite = true;
			} else if (arrowItem.is(Items.ARROW)) {
				// Or if an arrow was about to be used
				arrowItem = arrowItem.copyWithCount(1);
				infinite = true;
			}
			List<ItemStack> ammo;
			if (infinite) {
				// In case of infinite arrows, the list of projectiles contains the 'fake arrow'
				arrowItem.set(DataComponents.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
				ammo = List.of(arrowItem);
			} else
				ammo = draw(bowItem, arrowItem, player);
			if (world instanceof ServerLevel serverlevel && !ammo.isEmpty()) {
				this.shoot(serverlevel, player, player.getUsedItemHand(), bowItem, ammo, pullingPower * 3.0F, 1.0F, pullingPower == 1.0F, null);
			}
			world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + pullingPower * 0.5F);
			player.awardStat(Stats.ITEM_USED.get(this));
		}
	}

	@Override
	public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
		arrow.setBaseDamage(arrow.getBaseDamage() + 1.0);
		return arrow;
	}

	private ItemStack findAmmo(Player player) {
		return new ItemStack(Items.ARROW);
	}
}