
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.stats.Stats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;

import java.util.List;

public class CrystalliteBowEmeraldItem extends BowItem {
	public CrystalliteBowEmeraldItem() {
		super(new Item.Properties().durability(1800).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A77Upgrade: \u00A7aEmerald"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
		ItemStack itemstack = p_40673_.getItemInHand(p_40674_);
		boolean flag = !p_40673_.getProjectile(itemstack).isEmpty();
		flag = true; // Changing this flag to true gives INFINITE AMMO
		InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, p_40672_, p_40673_, p_40674_, flag);
		if (ret != null)
			return ret;
		if (!p_40673_.getAbilities().instabuild && !flag) {
			return InteractionResultHolder.fail(itemstack);
		} else {
			p_40673_.startUsingItem(p_40674_);
			return InteractionResultHolder.consume(itemstack);
		}
	}

	@Override
	public void releaseUsing(ItemStack item, Level world, LivingEntity _player, int duration) {
		if (_player instanceof Player player) {
			boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, item) > 0;
			flag = true; // Setting this flag to true gives INFINITE AMMO
			ItemStack itemstack = player.getProjectile(item);
			int i = this.getUseDuration(item) - duration;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(item, world, player, i, !itemstack.isEmpty() || flag);
			if (i < 0)
				return;
			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(Items.ARROW);
				}
				float f = getPowerForTime(i);
				if (!((double) f < 0.1D)) {
					boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, item, player));
					flag1 = true;

					if (!world.isClientSide) {
						ArrowItem arrowitem = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
						AbstractArrow abstractarrow = arrowitem.createArrow(world, itemstack, player);
						abstractarrow = customArrow(abstractarrow);
						abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
						if (f == 1.0F) {
							abstractarrow.setCritArrow(true);
						}
						int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, item);
						if (j > 0) {
							abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double) j * 0.5D + 0.5D);
						}
						int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, item);
						if (k > 0) {
							abstractarrow.setKnockback(k);
						}
						if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, item) > 0) {
							abstractarrow.setSecondsOnFire(100);
						}
						item.hurtAndBreak(1, player, (p_289501_) -> {
							p_289501_.broadcastBreakEvent(player.getUsedItemHand());
						});
						if (player.getAbilities().instabuild || !(itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
							abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
						}

						world.addFreshEntity(abstractarrow);
					}
					world.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					if (!player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							player.getInventory().removeItem(itemstack);
						}
					}
					player.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}
}
