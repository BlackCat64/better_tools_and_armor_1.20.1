
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

import net.mcreator.bettertoolsandarmor.procedures.CrystalliteChestplateAmethystProcedureProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

import com.google.common.collect.Iterables;

public abstract class CrystalliteArmorAmethystItem extends ArmorItem {
	public CrystalliteArmorAmethystItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 35;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{4, 6, 8, 4}[type.getSlot().getIndex()];
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
				return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.AMETHYST_SHARD));
			}

			@Override
			public String getName() {
				return "crystallite_armor_amethyst";
			}

			@Override
			public float getToughness() {
				return 2.5f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.125f;
			}
		}, type, properties);
	}

	public static class Helmet extends CrystalliteArmorAmethystItem {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7dAmethyst"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7dAccuracy - A fully charged bow shoots arrows directly at the target"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_amethyst__layer_1.png";
		}
	}

	public static class Chestplate extends CrystalliteArmorAmethystItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7dAmethyst"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7dCrystallized - Gain Absorption health when underground and not in combat"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_amethyst__layer_1.png";
		}

		@Override
		public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			if (entity instanceof Player player && Iterables.contains(player.getArmorSlots(), itemstack)) {
				CrystalliteChestplateAmethystProcedureProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
			}
		}
	}

	public static class Leggings extends CrystalliteArmorAmethystItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7dAmethyst"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7dCave Climber - Allows wall climbing on any solid block"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_amethyst__layer_2.png";
		}
	}

	public static class Boots extends CrystalliteArmorAmethystItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7dAmethyst"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7dShockwave - Damage nearby mobs when landing from a fall"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_amethyst__layer_1.png";
		}
	}
}
