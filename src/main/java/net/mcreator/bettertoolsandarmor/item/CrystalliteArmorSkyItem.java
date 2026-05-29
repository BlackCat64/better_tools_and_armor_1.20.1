package net.mcreator.bettertoolsandarmor.item;

import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.Util;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;
import java.util.EnumMap;

@EventBusSubscriber
public abstract class CrystalliteArmorSkyItem extends ArmorItem {
	public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;

	@SubscribeEvent
	public static void registerArmorMaterial(RegisterEvent event) {
		event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
			ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 4);
				map.put(ArmorItem.Type.LEGGINGS, 6);
				map.put(ArmorItem.Type.CHESTPLATE, 8);
				map.put(ArmorItem.Type.HELMET, 4);
				map.put(ArmorItem.Type.BODY, 8);
			}), 20, DeferredHolder.create(Registries.SOUND_EVENT, ResourceLocation.parse("better_tools:crystallite_step")),
					() -> Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.PHANTOM_MEMBRANE)),
					List.of(new ArmorMaterial.Layer(ResourceLocation.parse("better_tools:crystallite_sky_"))), 2.5f, 0.125f);
			registerHelper.register(ResourceLocation.parse("better_tools:crystallite_armor_sky"), armorMaterial);
			ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
		});
	}

	public CrystalliteArmorSkyItem(ArmorItem.Type type, Item.Properties properties) {
		super(ARMOR_MATERIAL, type, properties);
	}

	public static class Helmet extends CrystalliteArmorSkyItem {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(40)).fireResistant());
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, list, flag);
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_helmet.description_0"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_helmet.description_1"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_helmet.description_2"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_helmet.description_3"));
		}
	}

	public static class Chestplate extends CrystalliteArmorSkyItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(40)).fireResistant());
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, list, flag);
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_chestplate.description_0"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_chestplate.description_1"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_chestplate.description_2"));
		}
	}

	public static class Leggings extends CrystalliteArmorSkyItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40)).fireResistant());
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, list, flag);
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_leggings.description_0"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_leggings.description_1"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_leggings.description_2"));
		}
	}

	public static class Boots extends CrystalliteArmorSkyItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(40)).fireResistant());
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, list, flag);
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_boots.description_0"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_boots.description_1"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_sky_boots.description_2"));
		}
	}
}