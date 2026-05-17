
package net.mcreator.bettertoolsandarmor.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.procedures.EndTitaniumLeggingsProcedureProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

import com.google.common.collect.Iterables;

public abstract class EndTitaniumArmorItem extends ArmorItem {
	public EndTitaniumArmorItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 60;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{4, 8, 9, 4}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 18;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_netherite"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.END_TITANIUM_INGOT.get()));
			}

			@Override
			public String getName() {
				return "end_titanium";
			}

			@Override
			public float getToughness() {
				return 4f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.15f;
			}
		}, type, properties);
	}

	public static class Helmet extends EndTitaniumArmorItem {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.translatable("item.better_tools.end_titanium_helmet.description_0"));
			list.add(Component.translatable("item.better_tools.end_titanium_helmet.description_1"));
			list.add(Component.translatable("item.better_tools.end_titanium_helmet.description_2"));
			list.add(Component.translatable("item.better_tools.end_titanium_helmet.description_3"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/end_titanium__layer_1.png";
		}
	}

	public static class Chestplate extends EndTitaniumArmorItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.translatable("item.better_tools.end_titanium_chestplate.description_0"));
			list.add(Component.translatable("item.better_tools.end_titanium_chestplate.description_1"));
			list.add(Component.translatable("item.better_tools.end_titanium_chestplate.description_2"));
			list.add(Component.translatable("item.better_tools.end_titanium_chestplate.description_3"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/end_titanium__layer_1.png";
		}
	}

	public static class Leggings extends EndTitaniumArmorItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.translatable("item.better_tools.end_titanium_leggings.description_0"));
			list.add(Component.translatable("item.better_tools.end_titanium_leggings.description_1"));
			list.add(Component.translatable("item.better_tools.end_titanium_leggings.description_2"));
			list.add(Component.translatable("item.better_tools.end_titanium_leggings.description_3"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/end_titanium__layer_2.png";
		}

		@Override
		public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			if (entity instanceof Player player && Iterables.contains(player.getArmorSlots(), itemstack)) {
				EndTitaniumLeggingsProcedureProcedure.execute(entity);
			}
		}
	}

	public static class Boots extends EndTitaniumArmorItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.translatable("item.better_tools.end_titanium_boots.description_0"));
			list.add(Component.translatable("item.better_tools.end_titanium_boots.description_1"));
			list.add(Component.translatable("item.better_tools.end_titanium_boots.description_2"));
			list.add(Component.translatable("item.better_tools.end_titanium_boots.description_3"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/end_titanium__layer_1.png";
		}
	}
}
