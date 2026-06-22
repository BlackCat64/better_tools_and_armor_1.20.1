package net.mcreator.bettertoolsandarmor.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.entity.projectile.AbstractArrow;
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

import net.mcreator.bettertoolsandarmor.procedures.CrystalliteBowGetPullTimeProcedure;

import java.util.List;

public class CrystalliteBowItem extends BowItem {
	public CrystalliteBowItem() {
		super(new Item.Properties().durability(2400).fireResistant());
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.better_tools.crystallite_bow.description_0"));
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
			return InteractionResultHolder.consume(itemstack);
		}
	}

	@Override
	public void releaseUsing(ItemStack bowItem, Level world, LivingEntity entity, int time) {
		if (!world.isClientSide() && entity instanceof ServerPlayer player) {
			int useTime = this.getUseDuration(bowItem, player) - time;
			useTime = net.neoforged.neoforge.event.EventHooks.onArrowLoose(bowItem, world, player, useTime, !bowItem.isEmpty());
			float pullingPower = BowItem.getPowerForTime(useTime * this.getDrawSpeedMultiplier());
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

	//	@Override
	//	protected void shoot(
	//        ServerLevel world,
	//        LivingEntity player,
	//        InteractionHand hand,
	//        ItemStack bowItem,
	//        List<ItemStack> items,
	//        float p_331007_,
	//        float p_331445_,
	//        boolean isFullyPulled,
	//        @Nullable LivingEntity unused
	//    ) {
	//        float f = EnchantmentHelper.processProjectileSpread(world, bowItem, player, 0.0F);
	//        float f1 = items.size() == 1 ? 0.0F : 2.0F * f / (float)(items.size() - 1);
	//        float f2 = (float)((items.size() - 1) % 2) * f1 / 2.0F;
	//        float f3 = 1.0F;
	//
	//        for (int i = 0; i < items.size(); i++) {
	//            ItemStack itemstack = items.get(i);
	//            if (!itemstack.isEmpty()) {
	//                float f4 = f2 + f3 * (float)((i + 1) / 2) * f1;
	//                f3 = -f3;
	//                Projectile projectile = this.createProjectile(world, player, bowItem, itemstack, isFullyPulled);
	//                this.shootProjectile(player, projectile, i, p_331007_, p_331445_, f4, unused);
	//                world.addFreshEntity(projectile);
	//                bowItem.hurtAndBreak(this.getDurabilityUse(itemstack), player, LivingEntity.getSlotForHand(hand));
	//                if (bowItem.isEmpty()) {
	//                    break;
	//                }
	//            }
	//        }
	//    }
	//
	//    @Override
	//    protected Projectile createProjectile(Level world, LivingEntity player, ItemStack bowItem, ItemStack arrowItem, boolean isFullyPulled) {
	//        ArrowItem arrowitem = arrowItem.getItem() instanceof ArrowItem arrowitem1 ? arrowitem1 : (ArrowItem) Items.ARROW;
	//        AbstractArrow abstractarrow = arrowitem.createArrow(world, arrowItem, player, bowItem);
	//        if (isFullyPulled) {
	//            abstractarrow.setCritArrow(true);
	//        }
	//
	//        return customArrow(abstractarrow, arrowItem, bowItem);
	//    }
	@Override
	public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
		arrow.setBaseDamage(arrow.getBaseDamage() + this.getDamageBoost());
		System.out.println(arrow.getBaseDamage());
		return arrow;
	}

	// By default, all Crystallite Bows have +1 Base Arrow Damage
	public double getDamageBoost() {
		return 1.0;
	}

	// Can be overridden to multiply the draw speed by this int value. (Charge time is multiplied by the RECIPROCAL of this value)
	public int getDrawSpeedMultiplier() {
		return 1;
	}

	@Override
	public void onUseTick(Level world, LivingEntity entity, ItemStack itemstack, int time) {
		CrystalliteBowGetPullTimeProcedure.execute(entity, time);
	}
}