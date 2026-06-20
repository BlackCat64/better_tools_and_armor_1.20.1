package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class CrystalliteSculkArmorTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, Minecraft.getInstance().level, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, world, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		double range = 0;
		double replaceLine = 0;
		double speed = 0;
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_HELMET.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_CHESTPLATE.get()
				|| itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_LEGGINGS.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_BOOTS.get()) {
			if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_CHESTPLATE.get()) {
				tooltip.add(Component.literal("\u00A72 20% Sonic Boom Chance"));
				tooltip.add(Component.literal("\u00A72 10 Sonic Boom Damage"));
			}
			if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_LEGGINGS.get()) {
				speed = itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWIFT_SNEAK)) * 0.15 + 0.4;
				replaceLine = GetTooltipLineContainingProcedure.execute(" Sneaking Speed", tooltip);
				ReplaceTooltipLineProcedure.execute(replaceLine, "\u00A79+" + new java.text.DecimalFormat("##.##").format(speed) + " Sneaking Speed", tooltip);
			}
			range = 100;
			if (IsPlayerWearingItemProcedure.execute(entity, itemstack)) {
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_HELMET.get()) {
					range = range - 10;
				}
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_CHESTPLATE.get()) {
					range = range - 10;
				}
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_LEGGINGS.get()) {
					range = range - 10;
				}
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_BOOTS.get()) {
					range = range - 10;
				}
				if (range <= 60) {
					range = 50;
				}
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format(range) + "% Detection Range")));
			} else {
				tooltip.add(Component.literal("\u00A79-10% Detection Range"));
			}
			if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_BOOTS.get()) {
				if (IsPlayerWearingItemProcedure.execute(entity, itemstack) && IsPlayerInTheDarkProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity)) {
					tooltip.add(Component.literal(("\u00A72 "
							+ (new java.text.DecimalFormat("##")
									.format((entity instanceof LivingEntity _livingEntity31 && _livingEntity31.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED) ? _livingEntity31.getAttribute(Attributes.MOVEMENT_SPEED).getValue() : 0) * 1000))
							+ "% Movement Speed")));
				} else {
					tooltip.add(Component.literal("\u00A77When in the dark:"));
					tooltip.add(Component.literal("\u00A79+30% Speed"));
				}
			}
		}
	}
}