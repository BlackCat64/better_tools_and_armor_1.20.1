
package net.mcreator.bettertoolsandarmor.init;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.List;

@JeiPlugin
public class BetterToolsModJeiInformation implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("better_tools:information");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModItems.TOUGH_CRYSTAL.get()), new ItemStack(BetterToolsModItems.GILDED_CRYSTAL.get()), new ItemStack(BetterToolsModItems.MAGIC_CRYSTAL.get()),
				new ItemStack(BetterToolsModItems.HEAL_CRYSTAL.get()), new ItemStack(BetterToolsModItems.DIAMOND_HARD_CRYSTAL.get()), new ItemStack(BetterToolsModItems.SPIKED_CRYSTAL.get()), new ItemStack(BetterToolsModItems.SPEEDY_CRYSTAL.get()),
				new ItemStack(BetterToolsModItems.SKY_CRYSTAL.get()), new ItemStack(BetterToolsModItems.NATURE_CRYSTAL.get()), new ItemStack(BetterToolsModItems.ECHO_CRYSTAL.get()), new ItemStack(BetterToolsModItems.EARTH_CRYSTAL.get()),
				new ItemStack(BetterToolsModItems.GUARDIAN_CRYSTAL.get()), new ItemStack(BetterToolsModItems.HIVE_CRYSTAL.get()), new ItemStack(BetterToolsModItems.FIRE_CRYSTAL.get()), new ItemStack(BetterToolsModItems.ICE_CRYSTAL.get()),
				new ItemStack(BetterToolsModItems.ELECTRIC_CRYSTAL.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.elemental_crystals_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_CHARM_BASE.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.crystallite_charm_base_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModBlocks.CRYSTALLITE_BLOCK.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()),
				new ItemStack(BetterToolsModBlocks.CUT_CRYSTALLITE_BLOCK.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.obtaining_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_IRON.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_IRON.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_IRON.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_IRON.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_IRON.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_IRON.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_IRON_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_IRON_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_IRON_LEGGINGS.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_IRON_BOOTS.get()), new ItemStack(BetterToolsModItems.TOUGH_CRYSTAL.get()), new ItemStack(BetterToolsModItems.TOUGH_NECKLACE.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.iron_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.GILDED_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_GOLD.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_GOLD.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_GOLD.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_GOLD.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_GOLD.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_GOLD.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_GOLD_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_GOLD_CHESTPLATE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_GOLD_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_GOLD_BOOTS.get()), new ItemStack(BetterToolsModItems.GILDED_BRACELET.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.gold_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.MAGIC_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_LAPIS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_LAPIS.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_LAPIS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_LAPIS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_LAPIS.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_LAPIS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_LAPIS_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_LAPIS_CHESTPLATE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_LAPIS_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_LAPIS_BOOTS.get()), new ItemStack(BetterToolsModItems.MAGIC_RING.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.lapis_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.HEAL_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_REDSTONE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_REDSTONE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_REDSTONE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_REDSTONE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_REDSTONE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_REDSTONE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_REDSTONE_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_REDSTONE_CHESTPLATE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_REDSTONE_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_REDSTONE_BOOTS.get()), new ItemStack(BetterToolsModItems.HEART_CHARM.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.redstone_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.DIAMOND_HARD_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_DIAMOND.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_DIAMOND.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_DIAMOND.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_DIAMOND.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_DIAMOND.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_DIAMOND.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_DIAMOND_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_DIAMOND_CHESTPLATE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_DIAMOND_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_DIAMOND_BOOTS.get()), new ItemStack(BetterToolsModItems.DIAMOND_HARD_PLATE.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.diamond_crystallite_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModItems.SPIKED_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_NETHERITE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_NETHERITE.get()),
				new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_NETHERITE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_NETHERITE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_NETHERITE.get()),
				new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_NETHERITE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_CHESTPLATE.get()),
				new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_BOOTS.get()), new ItemStack(BetterToolsModItems.WITHERED_GAUNTLET.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.netherite_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.SPEEDY_CRYSTAL.get()), new ItemStack(BetterToolsModItems.SPEEDY_NECKLACE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_RUBY.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_RUBY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_RUBY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_RUBY.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_RUBY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_RUBY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_RUBY_HELMET.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_RUBY_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_RUBY_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_RUBY_BOOTS.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.ruby_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.ICE_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_SAPPHIRE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_SAPPHIRE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_SAPPHIRE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_SAPPHIRE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_SAPPHIRE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_SAPPHIRE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_CHESTPLATE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_BOOTS.get()), new ItemStack(BetterToolsModItems.ICY_BRACELET.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.sapphire_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.ELECTRIC_CRYSTAL.get()), new ItemStack(BetterToolsModItems.ELECTRIC_NECKLACE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_TOPAZ.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_TOPAZ.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_TOPAZ.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_TOPAZ.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_TOPAZ.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_TOPAZ.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_HELMET.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_BOOTS.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.topaz_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.FIRE_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_NETHER_DIAMOND.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_NETHER_DIAMOND.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_NETHER_DIAMOND.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_NETHER_DIAMOND.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_NETHER_DIAMOND.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_NETHER_DIAMOND.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_HELMET.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_LEGGINGS.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_BOOTS.get()), new ItemStack(BetterToolsModItems.FLAMING_CIRCLET.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.nether_diamond_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.NATURE_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_EMERALD.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_EMERALD.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_EMERALD.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_EMERALD.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_EMERALD.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_EMERALD.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_EMERALD_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_EMERALD_CHESTPLATE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_EMERALD_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_EMERALD_BOOTS.get()), new ItemStack(BetterToolsModItems.NATURE_RING.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.emerald_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.ECHO_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_SCULK.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_SCULK.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_SCULK.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_SCULK.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_SCULK.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_SCULK.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_CHESTPLATE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_BOOTS.get()), new ItemStack(BetterToolsModItems.WARDEN_HEADBAND.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.sculk_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.SKY_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_SKY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_SKY.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_SKY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_SKY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_SKY.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_SKY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SKY_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SKY_CHESTPLATE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SKY_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_SKY_BOOTS.get()), new ItemStack(BetterToolsModItems.REFLECT_CHARM.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.sky_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.EARTH_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_AMETHYST.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_AMETHYST.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_AMETHYST.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_AMETHYST.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_AMETHYST.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_AMETHYST.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_CHESTPLATE.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_BOOTS.get()), new ItemStack(BetterToolsModItems.EARTH_CIRCLET.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.amethyst_crystallite_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModItems.GUARDIAN_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_PRISMARINE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_PRISMARINE.get()),
				new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_PRISMARINE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_PRISMARINE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_PRISMARINE.get()),
				new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_PRISMARINE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_PRISMARINE_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_PRISMARINE_CHESTPLATE.get()),
				new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_PRISMARINE_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_PRISMARINE_BOOTS.get()), new ItemStack(BetterToolsModItems.GUARDIAN_NECKLACE.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.prismarine_crystallite_info"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SWORD_HONEY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_PICKAXE_HONEY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_AXE_HONEY.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_SHOVEL_HONEY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_HOE_HONEY.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_DAGGER_HONEY.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_HONEY_HELMET.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_HONEY_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_HONEY_LEGGINGS.get()),
						new ItemStack(BetterToolsModItems.CRYSTALLITE_ARMOR_HONEY_BOOTS.get()), new ItemStack(BetterToolsModItems.HIVE_CRYSTAL.get()), new ItemStack(BetterToolsModItems.CURING_CHARM.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.honey_crystallite_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModItems.SUGAR_BOOTS.get()), new ItemStack(BetterToolsModItems.RABBIT_BOOTS.get()), new ItemStack(BetterToolsModItems.PHANTOM_BOOTS.get()),
				new ItemStack(BetterToolsModItems.CACTUS_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.HEARTY_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.RUBY_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYSTAL_LEGGINGS.get()),
				new ItemStack(BetterToolsModItems.GOLD_CARROT_HELMET.get()), new ItemStack(BetterToolsModItems.BLACKSTONE_LEGGINGS.get()), new ItemStack(BetterToolsModItems.IRON_SUGAR_BOOTS.get()),
				new ItemStack(BetterToolsModItems.IRON_RABBIT_BOOTS.get()), new ItemStack(BetterToolsModItems.IRON_PHANTOM_BOOTS.get()), new ItemStack(BetterToolsModItems.IRON_CACTUS_CHESTPLATE.get()),
				new ItemStack(BetterToolsModItems.IRON_HEARTY_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.IRON_GOLD_CARROT_HELMET.get()), new ItemStack(BetterToolsModItems.DIAMOND_SUGAR_BOOTS.get()),
				new ItemStack(BetterToolsModItems.DIAMOND_RABBIT_BOOTS.get()), new ItemStack(BetterToolsModItems.DIAMOND_PHANTOM_BOOTS.get()), new ItemStack(BetterToolsModItems.DIAMOND_CACTUS_CHESTPLATE.get()),
				new ItemStack(BetterToolsModItems.DIAMOND_HEARTY_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.DIAMOND_RUBY_LEGGINGS.get()), new ItemStack(BetterToolsModItems.DIAMOND_CRYSTAL_LEGGINGS.get()),
				new ItemStack(BetterToolsModItems.DIAMOND_GOLD_CARROT_HELMET.get()), new ItemStack(BetterToolsModItems.DIAMOND_BLACKSTONE_LEGGINGS.get()), new ItemStack(BetterToolsModItems.CRYING_OBSIDIAN_HELMET.get()),
				new ItemStack(BetterToolsModItems.DIAMOND_CRYING_OBSIDIAN_HELMET.get()), new ItemStack(BetterToolsModItems.WINGED_BOOTS_BOOTS.get()), new ItemStack(BetterToolsModItems.GLASS_HELMET.get()),
				new ItemStack(BetterToolsModItems.GLASS_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.GLASS_LEGGINGS.get()), new ItemStack(BetterToolsModItems.GLASS_BOOTS.get()), new ItemStack(BetterToolsModItems.MAGMA_CHESTPLATE.get()),
				new ItemStack(BetterToolsModItems.DIAMOND_MAGMA_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.IRON_GLASS_ARMOR_HELMET.get()), new ItemStack(BetterToolsModItems.IRON_GLASS_ARMOR_CHESTPLATE.get()),
				new ItemStack(BetterToolsModItems.IRON_GLASS_ARMOR_LEGGINGS.get()), new ItemStack(BetterToolsModItems.IRON_GLASS_ARMOR_BOOTS.get()), new ItemStack(BetterToolsModItems.DIAMOND_GLASS_ARMOR_HELMET.get()),
				new ItemStack(BetterToolsModItems.DIAMOND_GLASS_ARMOR_CHESTPLATE.get()), new ItemStack(BetterToolsModItems.DIAMOND_GLASS_ARMOR_LEGGINGS.get()), new ItemStack(BetterToolsModItems.DIAMOND_GLASS_ARMOR_BOOTS.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.effect_armor_jei_information"));
		registration.addIngredientInfo(
				List.of(new ItemStack(BetterToolsModBlocks.NETHER_DIAMOND_BLOCK.get()), new ItemStack(BetterToolsModBlocks.RUBY_BLOCK.get()), new ItemStack(BetterToolsModBlocks.END_TITANIUM_BLOCK.get()),
						new ItemStack(BetterToolsModBlocks.SAPPHIRE_BLOCK.get()), new ItemStack(BetterToolsModBlocks.TOPAZ_BLOCK.get()), new ItemStack(BetterToolsModBlocks.CUT_CRYSTALLITE_BLOCK.get()), new ItemStack(Blocks.LAPIS_BLOCK),
						new ItemStack(Blocks.QUARTZ_BLOCK), new ItemStack(Blocks.COPPER_BLOCK), new ItemStack(Blocks.WAXED_COPPER_BLOCK), new ItemStack(Blocks.AMETHYST_BLOCK)),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.beacon_base_blocks_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModItems.NETHER_DIAMOND.get()), new ItemStack(BetterToolsModItems.RUBY.get()), new ItemStack(BetterToolsModItems.END_TITANIUM_INGOT.get()),
				new ItemStack(BetterToolsModItems.SAPPHIRE.get()), new ItemStack(BetterToolsModItems.TOPAZ.get()), new ItemStack(Items.LAPIS_LAZULI), new ItemStack(Items.QUARTZ), new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()),
				new ItemStack(Items.AMETHYST_SHARD), new ItemStack(Items.COPPER_INGOT)), VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.beacon_payment_items_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModBlocks.FOUR_LEAF_CLOVER.get()), new ItemStack(BetterToolsModItems.LUCKY_CHARM.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.improved_luck_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModBlocks.BLUE_MUSHROOM.get()), new ItemStack(BetterToolsModBlocks.BLUE_MUSHROOM_BLOCK.get())), VanillaTypes.ITEM_STACK,
				Component.translatable("jei.better_tools.blue_mushroom_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModBlocks.PURPLE_MUSHROOM.get()), new ItemStack(BetterToolsModBlocks.PURPLE_MUSHROOM_BLOCK.get())), VanillaTypes.ITEM_STACK,
				Component.translatable("jei.better_tools.purple_mushroom_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModItems.RECALL_POTION.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.recall_potion_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModItems.LOST_SOULS_POTION.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.lost_souls_potion_info"));
		registration.addIngredientInfo(List.of(new ItemStack(BetterToolsModBlocks.JEWELLERY_TABLE.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.better_tools.jewellery_table_info"));
	}
}
