package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class ThunderShotTooltipProcedure {
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
		double chance = 0;
		if (EnchantmentHelper.getItemEnchantmentLevel(BetterToolsModEnchantments.THUNDER_SHOT.get(), itemstack) != 0 || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_BOW_TOPAZ.get()) {
			chance = itemstack.getEnchantmentLevel(BetterToolsModEnchantments.THUNDER_SHOT.get()) * 0.1;
			if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.ELECTRIC_NECKLACE.get(), lv).isPresent() : false) {
				chance = chance + 0.1;
			}
			if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:topaz_upgraded_crystallite_items")))) {
				chance = chance + 0.2;
			}
			if (chance > 0) {
				chance = chance + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(Math.min(100, chance * 100)) + "% Lightning Chance")));
				tooltip.add(Component.literal("\u00A76Effect is more likely in a thunderstorm"));
			}
		}
	}
}
