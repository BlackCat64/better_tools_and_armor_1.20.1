
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
import net.minecraft.stats.Stats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;

import java.util.List;

public class CrystalliteBowHoneyItem extends BowItem {
	public CrystalliteBowHoneyItem() {
		super(new Item.Properties().durability(1800).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A77Upgrade: \u00A76Honey"));
	}

	@Override
	public void releaseUsing(ItemStack item, Level world, LivingEntity _player, int duration) {
		if (_player instanceof Player player) {
			boolean infinite_arrows = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, item) > 0;
			ItemStack itemstack = player.getProjectile(item);
			int i = this.getUseDuration(item) - duration;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(item, world, player, i, !itemstack.isEmpty() || infinite_arrows);
			if (i < 0)
				return;
			if (!itemstack.isEmpty() || infinite_arrows) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(Items.ARROW);
				}
				float f = getPowerForTime(i * 20);
				// 							^ Multiplying by 20 here causes rapid-fire. The animation is the same, however.
				if (!((double) f < 0.1D)) {
					boolean creative = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, item, player));
					if (!world.isClientSide) {
						ArrowItem arrowitem = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
						AbstractArrow abstractarrow = arrowitem.createArrow(world, itemstack, player);
						abstractarrow = customArrow(abstractarrow);
						abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
						// Shoot speed is always 3
						// Rapid-fire arrows cannot be critical
						int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, item);
						if (j > 0) {
							abstractarrow.setBaseDamage((double) (1 + j * 0.4));
						} else
							abstractarrow.setBaseDamage(1);
						// Arrow damage = 1 + (0.4 * POWER LEVEL)
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
						if (creative || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
							abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
						}
						world.addFreshEntity(abstractarrow);
					}
					world.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					if (!creative && !player.getAbilities().instabuild) {
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
