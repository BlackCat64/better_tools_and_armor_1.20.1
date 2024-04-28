
package net.mcreator.bettertoolsandarmor.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Items;
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

import net.mcreator.bettertoolsandarmor.procedures.CrystalliteHelmetSculkProcedureProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

import com.google.common.collect.Iterables;

public abstract class CrystalliteArmorSculkItem extends ArmorItem {
	public CrystalliteArmorSculkItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 44;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{4, 7, 8, 5}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 20;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("better_tools:crystallite_step"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.ECHO_SHARD));
			}

			@Override
			public String getName() {
				return "crystallite_armor_sculk";
			}

			@Override
			public float getToughness() {
				return 3.5f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.125f;
			}
		}, type, properties);
	}

	public static class Helmet extends CrystalliteArmorSculkItem {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A73Sculk"));
			list.add(Component.literal("\u00A77Abilities:"));
			list.add(Component.literal("\u00A73Deep Dweller - Gain night vision when in the dark"));
			list.add(Component.literal("\u00A73Darkness immunity"));
			list.add(Component.literal("\u00A73Enemy detection range reduced"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A73Enemy detection range reduces further"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_sculk__layer_1.png";
		}

		@Override
		public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			if (entity instanceof Player player && Iterables.contains(player.getArmorSlots(), itemstack)) {
				CrystalliteHelmetSculkProcedureProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
			}
		}
	}

	public static class Chestplate extends CrystalliteArmorSculkItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A73Sculk"));
			list.add(Component.literal("\u00A77Abilities:"));
			list.add(Component.literal("\u00A73Warden Heart - Has a chance to launch sonic booms at attackers"));
			list.add(Component.literal("\u00A73Enemy detection range reduced"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A73Enemy detection range reduces further"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_sculk__layer_1.png";
		}
	}

	public static class Leggings extends CrystalliteArmorSculkItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A73Sculk"));
			list.add(Component.literal("\u00A77Abilities:"));
			list.add(Component.literal("\u00A73Sneaky - Increases movement speed while crouching"));
			list.add(Component.literal("\u00A73Enemy detection range reduced"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A73Enemy detection range reduces further"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_sculk__layer_2.png";
		}
	}

	public static class Boots extends CrystalliteArmorSculkItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A73Sculk"));
			list.add(Component.literal("\u00A77Abilities:"));
			list.add(Component.literal("\u00A73Deep Dweller - Increased movement speed in the dark"));
			list.add(Component.literal("\u00A73Enemy detection range reduced"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A73Enemy detection range reduces further"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_sculk__layer_1.png";
		}
	}
}
