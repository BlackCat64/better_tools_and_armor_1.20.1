package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class SapphireArmorTooltipProcedure {
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
		double percent = 0;
		double seconds = 0;
		String default_time_chance_str = "";
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:freezing_armor")))) {
			percent = ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FREEZETHORNSCHANCE.get()).getValue() * 100;
			seconds = ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FREEZETHORNSTIME.get()).getValue() / 20;
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).is_in_cold_biome) {
				default_time_chance_str = itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items"))) ? "15" : "10";
			} else {
				default_time_chance_str = itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items"))) ? "10" : "5";
			}
			tooltip.add(Component.literal((!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == itemstack.getItem())
							? "\u00A79+" + default_time_chance_str + "% Freeze Chance"
							: "\u00A72 " + new java.text.DecimalFormat("##").format(percent) + "% Freeze Chance")));
			tooltip.add(Component.literal(("\u00A72 " + (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == itemstack.getItem()) ? default_time_chance_str : new java.text.DecimalFormat("##").format(seconds))
					+ "s Freeze Time")));
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal("\u00A77Full-set bonus:"));
				tooltip.add(Component.literal(("\u00A79+" + (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items"))) ? "10" : "5") + "% Freeze Chance")));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}
