
package net.mcreator.bettertoolsandarmor.item;

import java.util.function.Consumer;
import net.minecraft.client.model.Model;

public abstract class NetherDiamondArmorItem extends ArmorItem {

	public NetherDiamondArmorItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 33;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{3, 6, 8, 3}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 10;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_diamond"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.NETHER_DIAMOND.get()));
			}

			@Override
			public String getName() {
				return "nether_diamond";
			}

			@Override
			public float getToughness() {
				return 2f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		}, type, properties);
	}

	public static class Helmet extends NetherDiamondArmorItem {

		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A74Reduces maximum burn time"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A74 Gives Fire Resistance when on fire"));
			list.add(Component.literal("\u00A74 Effect is stronger in the Nether"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/netherdiamond__layer_1.png";
		}

	}

	public static class Chestplate extends NetherDiamondArmorItem {

		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A74Reduces maximum burn time"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A74 Gives Fire Resistance when on fire"));
			list.add(Component.literal("\u00A74 Effect is stronger in the Nether"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/netherdiamond__layer_1.png";
		}

	}

	public static class Leggings extends NetherDiamondArmorItem {

		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A74Reduces maximum burn time"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A74 Gives Fire Resistance when on fire"));
			list.add(Component.literal("\u00A74 Effect is stronger in the Nether"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/netherdiamond__layer_2.png";
		}

	}

	public static class Boots extends NetherDiamondArmorItem {

		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A74Reduces maximum burn time"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A74 Gives Fire Resistance when on fire"));
			list.add(Component.literal("\u00A74 Effect is stronger in the Nether"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/netherdiamond__layer_1.png";
		}

	}

}
