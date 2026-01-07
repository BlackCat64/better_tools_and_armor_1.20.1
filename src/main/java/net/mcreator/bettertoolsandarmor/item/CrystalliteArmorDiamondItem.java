
package net.mcreator.bettertoolsandarmor.item;

import java.util.function.Consumer;
import net.minecraft.client.model.Model;

public abstract class CrystalliteArmorDiamondItem extends ArmorItem {

	public CrystalliteArmorDiamondItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 70;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{5, 8, 9, 6}[type.getSlot().getIndex()];
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
				return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.DIAMOND));
			}

			@Override
			public String getName() {
				return "crystallite_armor_diamond";
			}

			@Override
			public float getToughness() {
				return 4.5f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.125f;
			}
		}, type, properties);
	}

	public static class Helmet extends CrystalliteArmorDiamondItem {

		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7bDiamond"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7bDiamond Hard - Increased Durability and Protection"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_diamond__layer_1.png";
		}

	}

	public static class Chestplate extends CrystalliteArmorDiamondItem {

		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7bDiamond"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7bDiamond Hard - Increased Durability and Protection"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_diamond__layer_1.png";
		}

	}

	public static class Leggings extends CrystalliteArmorDiamondItem {

		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7bDiamond"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7bDiamond Hard - Increased Durability and Protection"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_diamond__layer_2.png";
		}

	}

	public static class Boots extends CrystalliteArmorDiamondItem {

		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7bDiamond"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7bDiamond Hard - Increased Durability and Protection"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_diamond__layer_1.png";
		}

	}

}
