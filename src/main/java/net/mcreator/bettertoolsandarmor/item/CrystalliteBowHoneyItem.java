package net.mcreator.bettertoolsandarmor.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.stats.Stats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class CrystalliteBowHoneyItem extends BowItem {
	public CrystalliteBowHoneyItem() {
		super(new Item.Properties().durability(2400).fireResistant());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.BOW;
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {
		return 72000;
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Blocks.HONEY_BLOCK)).test(repairitem);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.better_tools.crystallite_bow_honey.description_0"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_honey.description_1"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_honey.description_2"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		ItemStack itemstack = entity.getItemInHand(hand);
		boolean flag = !entity.getProjectile(itemstack).isEmpty();
		InteractionResultHolder<ItemStack> ret = net.neoforged.neoforge.event.EventHooks.onArrowNock(itemstack, world, entity, hand, flag);
		if (ret != null)
			return ret;
		if (!entity.hasInfiniteMaterials() && !flag) {
			return InteractionResultHolder.fail(itemstack);
		} else {
			entity.startUsingItem(hand);
			return InteractionResultHolder.pass(itemstack);
		}
	}

	@Override
	public void onUseTick(Level world, LivingEntity entity, ItemStack itemstack, int time) {
		if (!world.isClientSide() && entity instanceof ServerPlayer player) {
			int useTime = this.getUseDuration(itemstack, player) - time;
			useTime = net.neoforged.neoforge.event.EventHooks.onArrowLoose(itemstack, world, player, useTime, !itemstack.isEmpty());
			// Fire every 0.25s
			if (useTime % 5 == 0 && useTime > 0) {
				float pullingPower = 0.5f; // Fixed pull power
				ItemStack arrowItem = player.getProjectile(itemstack);
				if (player.getAbilities().instabuild || arrowItem != ItemStack.EMPTY) {
					List<ItemStack> list = draw(itemstack, arrowItem, player);
					if (world instanceof ServerLevel serverlevel && !list.isEmpty()) {
						this.shoot(serverlevel, player, player.getUsedItemHand(), itemstack, list, pullingPower * 3.0F, 1.0F, pullingPower == 1.0F, null);
					}
					world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + pullingPower * 0.5F);
					player.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	private ItemStack findAmmo(Player player) {
		return new ItemStack(Items.ARROW);
	}

	// Disable normal bow behaviour
	@Override
	public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
	}
}