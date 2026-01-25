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

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class FreezeShotTooltipProcedure {
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
		double FreezeShotChance = 0;
		double freeze_time = 0;
		if (EnchantmentHelper.getItemEnchantmentLevel(BetterToolsModEnchantments.FREEZE_SHOT.get(), itemstack) != 0 || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_BOW_SAPPHIRE.get()) {
			FreezeShotChance = itemstack.getEnchantmentLevel(BetterToolsModEnchantments.FREEZE_SHOT.get()) * 0.1;
			freeze_time = itemstack.getEnchantmentLevel(BetterToolsModEnchantments.FREEZE_SHOT.get()) * 66;
			if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.ICY_BRACELET.get(), lv).isPresent() : false) {
				FreezeShotChance = FreezeShotChance + 0.1;
			}
			if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items")))) {
				FreezeShotChance = FreezeShotChance + 0.2;
				freeze_time = freeze_time == 0 ? 70 : freeze_time * 1.5;
			}
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).is_in_cold_biome) {
				FreezeShotChance = FreezeShotChance * 2;
			}
			if (FreezeShotChance > 0) {
				FreezeShotChance = FreezeShotChance + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(Math.min(100, FreezeShotChance * 100)) + "% Freeze Chance")));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format(freeze_time / 20) + "s Freeze Time")));
			}
		}
	}
}
