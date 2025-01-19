package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class KnockbackTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		double value = 0;
		if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, itemstack) != 0 || itemstack.is(ItemTags.create(new ResourceLocation("better_tools:high_knockback_weapons")))) {
			if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:iron_upgraded_crystallite_items")))) {
				value = 2 + itemstack.getEnchantmentLevel(Enchantments.KNOCKBACK);
			} else if (itemstack.getItem() == BetterToolsModItems.BLUE_SLIME_STICK.get()) {
				value = 5;
			} else {
				value = itemstack.getEnchantmentLevel(Enchantments.KNOCKBACK);
			}
			if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.BOUNCY_BRACELET.get(), lv).isPresent() : false) {
				value = value + 2;
			}
			if (!itemstack.is(ItemTags.create(new ResourceLocation("minecraft:tools")))) {
				tooltip.add(Component.literal("\u00A77When in Main Hand:"));
			}
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(value) + " Attack Knockback")));
		}
	}
}
