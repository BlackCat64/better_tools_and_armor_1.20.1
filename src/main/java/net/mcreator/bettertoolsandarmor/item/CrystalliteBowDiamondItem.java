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
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class CrystalliteBowDiamondItem extends BowItem {
	private AbstractArrow firedArrow = null;

	public CrystalliteBowDiamondItem() {
		super(new Item.Properties().durability(4800).fireResistant());
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.DIAMOND)).test(repairitem);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.better_tools.crystallite_bow_diamond.description_0"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_diamond.description_1"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_diamond.description_2"));
		list.add(Component.translatable("item.better_tools.crystallite_bow_diamond.description_3"));
	}

	@Override
	public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
		arrow.setBaseDamage(arrow.getBaseDamage() + 1.0);
		this.firedArrow = arrow;
		return arrow;
	}

	@Override
	public void releaseUsing(ItemStack bowItem, Level world, LivingEntity entity, int time) {
		super.releaseUsing(bowItem, world, entity, time);
		double random = Math.random();
		double chance = 0.25;
		chance = chance + (entity instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(Attributes.LUCK) ? _livingEntity9.getAttribute(Attributes.LUCK).getValue() : 0) * 0.05;
		if (random < chance && this.firedArrow != null) {
			this.firedArrow.setBaseDamage(this.firedArrow.getBaseDamage() + 2.5);
			if (!world.isClientSide()) {
				world.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("better_tools:crystallite_place")), SoundSource.PLAYERS, 5, (float) 1.2);
			} else {
				world.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("better_tools:crystallite_place")), SoundSource.PLAYERS, 5, (float) 1.2, false);
			}
			this.firedArrow = null;
		}
	}
}