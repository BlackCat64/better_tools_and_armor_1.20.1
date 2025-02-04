
/*
*    MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;

import net.mcreator.bettertoolsandarmor.block.sign.MetalWallSignBlock;
import net.mcreator.bettertoolsandarmor.block.sign.MetalWallHangingSignBlock;
import net.mcreator.bettertoolsandarmor.block.sign.MetalSignBlock;
import net.mcreator.bettertoolsandarmor.block.sign.MetalHangingSignBlock;
import net.mcreator.bettertoolsandarmor.block.TopazOreBlock;
import net.mcreator.bettertoolsandarmor.block.TopazBlockBlock;
import net.mcreator.bettertoolsandarmor.block.SugarBlockBlock;
import net.mcreator.bettertoolsandarmor.block.SapphireOreBlock;
import net.mcreator.bettertoolsandarmor.block.SapphireBlockBlock;
import net.mcreator.bettertoolsandarmor.block.RubyOreBlock;
import net.mcreator.bettertoolsandarmor.block.RubyBlockBlock;
import net.mcreator.bettertoolsandarmor.block.RandomOreBlock;
import net.mcreator.bettertoolsandarmor.block.PurpleMushroomBlockBlock;
import net.mcreator.bettertoolsandarmor.block.PurpleMushroomBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedTopazWallBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedTopazTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedTopazStairsBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedTopazSlabBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedTopazBlockBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedRubyWallBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedRubyTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedRubyStairsBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedRubySlabBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedRubyBlockBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedRedstoneWallBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedRedstoneTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedRedstoneStairsBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedRedstoneSlabBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedRedstoneBlockBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedNetherDiamondWallBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedNetherDiamondTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedNetherDiamondStairsBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedNetherDiamondSlabBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedNetherDiamondBlockBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedGoldWallBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedGoldTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedGoldStairsBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedGoldSlabBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedGoldBlockBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedDiamondWallBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedDiamondTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedDiamondStairsBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedDiamondSlabBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedDiamondBlockBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedAmethystWallBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedAmethystTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedAmethystStairsBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedAmethystSlabBlock;
import net.mcreator.bettertoolsandarmor.block.PolishedAmethystBlockBlock;
import net.mcreator.bettertoolsandarmor.block.NetherDiamondOreBlock;
import net.mcreator.bettertoolsandarmor.block.NetherDiamondBlockBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedNetherDiamondWallBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedNetherDiamondTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedNetherDiamondStairsBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedNetherDiamondSlabBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedNetherDiamondBlockBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedLapisWallBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedLapisTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedLapisStairsBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedLapisSlabBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedLapisBlockBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedIronWallBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedIronTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedIronStairsBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedIronSlabBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedIronBlockBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedGoldWallBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedGoldTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedGoldStairsBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedGoldSlabBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedGoldBlockBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedEmeraldWallBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedEmeraldTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedEmeraldStairsBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedEmeraldSlabBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedEmeraldBlockBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedCopperWallBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedCopperTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedCopperStairsBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedCopperSlabBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedCopperBlockBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedCoalWallBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedCoalTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedCoalStairsBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedCoalSlabBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedCoalBlockBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedAmethystWallBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedAmethystTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedAmethystStairsBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedAmethystSlabBlock;
import net.mcreator.bettertoolsandarmor.block.LightPolishedAmethystBlockBlock;
import net.mcreator.bettertoolsandarmor.block.JewelleryTableBlock;
import net.mcreator.bettertoolsandarmor.block.FourLeafCloverBlock;
import net.mcreator.bettertoolsandarmor.block.FlintBlockwoodBlock;
import net.mcreator.bettertoolsandarmor.block.EndTitaniumOreBlock;
import net.mcreator.bettertoolsandarmor.block.EndTitaniumBlockBlock;
import net.mcreator.bettertoolsandarmor.block.DeepslateRubyOreBlock;
import net.mcreator.bettertoolsandarmor.block.DeepslateRandomOreBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedLapisWallBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedLapisTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedLapisStairsBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedLapisSlabBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedLapisBlockBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedIronWallBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedIronTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedIronStairsBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedIronSlabBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedIronBlockBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedEmeraldWallBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedEmeraldTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedEmeraldStairsBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedEmeraldSlabBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedEmeraldBlockBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedCopperWallBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedCopperTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedCopperStairsBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedCopperSlabBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedCopperBlockBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedCoalWallBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedCoalTrapdoorBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedCoalStairsBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedCoalSlabBlock;
import net.mcreator.bettertoolsandarmor.block.DarkPolishedCoalBlockBlock;
import net.mcreator.bettertoolsandarmor.block.CutCrystalliteBlockBlock;
import net.mcreator.bettertoolsandarmor.block.CrystalliteClusterAirBlock;
import net.mcreator.bettertoolsandarmor.block.CrystalliteBlockBlock;
import net.mcreator.bettertoolsandarmor.block.ClimbableWallBlock;
import net.mcreator.bettertoolsandarmor.block.ChargedIceOnBlock;
import net.mcreator.bettertoolsandarmor.block.BlueSlimeBlockBlock;
import net.mcreator.bettertoolsandarmor.block.BlueMushroomBlockBlock;
import net.mcreator.bettertoolsandarmor.block.BlueMushroomBlock;
import net.mcreator.bettertoolsandarmor.block.BlackstoneMagmaBlock;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, BetterToolsMod.MODID);
	public static final RegistryObject<Block> NETHER_DIAMOND_BLOCK = REGISTRY.register("nether_diamond_block", () -> new NetherDiamondBlockBlock());
	public static final RegistryObject<Block> SUGAR_BLOCK = REGISTRY.register("sugar_block", () -> new SugarBlockBlock());
	public static final RegistryObject<Block> RUBY_ORE = REGISTRY.register("ruby_ore", () -> new RubyOreBlock());
	public static final RegistryObject<Block> RUBY_BLOCK = REGISTRY.register("ruby_block", () -> new RubyBlockBlock());
	public static final RegistryObject<Block> RANDOM_ORE = REGISTRY.register("random_ore", () -> new RandomOreBlock());
	public static final RegistryObject<Block> END_TITANIUM_ORE = REGISTRY.register("end_titanium_ore", () -> new EndTitaniumOreBlock());
	public static final RegistryObject<Block> END_TITANIUM_BLOCK = REGISTRY.register("end_titanium_block", () -> new EndTitaniumBlockBlock());
	public static final RegistryObject<Block> BLUE_SLIME_BLOCK = REGISTRY.register("blue_slime_block", () -> new BlueSlimeBlockBlock());
	public static final RegistryObject<Block> BLUE_MUSHROOM = REGISTRY.register("blue_mushroom", () -> new BlueMushroomBlock());
	public static final RegistryObject<Block> BLUE_MUSHROOM_BLOCK = REGISTRY.register("blue_mushroom_block", () -> new BlueMushroomBlockBlock());
	public static final RegistryObject<Block> PURPLE_MUSHROOM = REGISTRY.register("purple_mushroom", () -> new PurpleMushroomBlock());
	public static final RegistryObject<Block> PURPLE_MUSHROOM_BLOCK = REGISTRY.register("purple_mushroom_block", () -> new PurpleMushroomBlockBlock());
	public static final RegistryObject<Block> BLACKSTONE_MAGMA = REGISTRY.register("blackstone_magma", () -> new BlackstoneMagmaBlock());
	public static final RegistryObject<Block> CHARGED_ICE_ON = REGISTRY.register("charged_ice_on", () -> new ChargedIceOnBlock());
	public static final RegistryObject<Block> SAPPHIRE_ORE = REGISTRY.register("sapphire_ore", () -> new SapphireOreBlock());
	public static final RegistryObject<Block> SAPPHIRE_BLOCK = REGISTRY.register("sapphire_block", () -> new SapphireBlockBlock());
	public static final RegistryObject<Block> TOPAZ_ORE = REGISTRY.register("topaz_ore", () -> new TopazOreBlock());
	public static final RegistryObject<Block> TOPAZ_BLOCK = REGISTRY.register("topaz_block", () -> new TopazBlockBlock());
	public static final RegistryObject<Block> FOUR_LEAF_CLOVER = REGISTRY.register("four_leaf_clover", () -> new FourLeafCloverBlock());
	public static final RegistryObject<Block> NETHER_DIAMOND_ORE = REGISTRY.register("nether_diamond_ore", () -> new NetherDiamondOreBlock());
	public static final RegistryObject<Block> CRYSTALLITE_BLOCK = REGISTRY.register("crystallite_block", () -> new CrystalliteBlockBlock());
	public static final RegistryObject<Block> CUT_CRYSTALLITE_BLOCK = REGISTRY.register("cut_crystallite_block", () -> new CutCrystalliteBlockBlock());
	public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = REGISTRY.register("deepslate_ruby_ore", () -> new DeepslateRubyOreBlock());
	public static final RegistryObject<Block> DEEPSLATE_RANDOM_ORE = REGISTRY.register("deepslate_random_ore", () -> new DeepslateRandomOreBlock());
	public static final RegistryObject<Block> FLINT_BLOCKWOOD = REGISTRY.register("flint_blockwood", () -> new FlintBlockwoodBlock());
	public static final RegistryObject<Block> DARK_POLISHED_COAL_BLOCK = REGISTRY.register("dark_polished_coal_block", () -> new DarkPolishedCoalBlockBlock());
	public static final RegistryObject<Block> DARK_POLISHED_COAL_SLAB = REGISTRY.register("dark_polished_coal_slab", () -> new DarkPolishedCoalSlabBlock());
	public static final RegistryObject<Block> DARK_POLISHED_COAL_STAIRS = REGISTRY.register("dark_polished_coal_stairs", () -> new DarkPolishedCoalStairsBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_COAL_BLOCK = REGISTRY.register("light_polished_coal_block", () -> new LightPolishedCoalBlockBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_COAL_SLAB = REGISTRY.register("light_polished_coal_slab", () -> new LightPolishedCoalSlabBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_COAL_STAIRS = REGISTRY.register("light_polished_coal_stairs", () -> new LightPolishedCoalStairsBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_IRON_BLOCK = REGISTRY.register("light_polished_iron_block", () -> new LightPolishedIronBlockBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_IRON_SLAB = REGISTRY.register("light_polished_iron_slab", () -> new LightPolishedIronSlabBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_IRON_STAIRS = REGISTRY.register("light_polished_iron_stairs", () -> new LightPolishedIronStairsBlock());
	public static final RegistryObject<Block> DARK_POLISHED_IRON_BLOCK = REGISTRY.register("dark_polished_iron_block", () -> new DarkPolishedIronBlockBlock());
	public static final RegistryObject<Block> DARK_POLISHED_IRON_SLAB = REGISTRY.register("dark_polished_iron_slab", () -> new DarkPolishedIronSlabBlock());
	public static final RegistryObject<Block> DARK_POLISHED_IRON_STAIRS = REGISTRY.register("dark_polished_iron_stairs", () -> new DarkPolishedIronStairsBlock());
	public static final RegistryObject<Block> DARK_POLISHED_LAPIS_BLOCK = REGISTRY.register("dark_polished_lapis_block", () -> new DarkPolishedLapisBlockBlock());
	public static final RegistryObject<Block> DARK_POLISHED_LAPIS_SLAB = REGISTRY.register("dark_polished_lapis_slab", () -> new DarkPolishedLapisSlabBlock());
	public static final RegistryObject<Block> DARK_POLISHED_LAPIS_STAIRS = REGISTRY.register("dark_polished_lapis_stairs", () -> new DarkPolishedLapisStairsBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_LAPIS_BLOCK = REGISTRY.register("light_polished_lapis_block", () -> new LightPolishedLapisBlockBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_LAPIS_SLAB = REGISTRY.register("light_polished_lapis_slab", () -> new LightPolishedLapisSlabBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_LAPIS_STAIRS = REGISTRY.register("light_polished_lapis_stairs", () -> new LightPolishedLapisStairsBlock());
	public static final RegistryObject<Block> POLISHED_DIAMOND_BLOCK = REGISTRY.register("polished_diamond_block", () -> new PolishedDiamondBlockBlock());
	public static final RegistryObject<Block> POLISHED_DIAMOND_SLAB = REGISTRY.register("polished_diamond_slab", () -> new PolishedDiamondSlabBlock());
	public static final RegistryObject<Block> POLISHED_DIAMOND_STAIRS = REGISTRY.register("polished_diamond_stairs", () -> new PolishedDiamondStairsBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_EMERALD_BLOCK = REGISTRY.register("light_polished_emerald_block", () -> new LightPolishedEmeraldBlockBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_EMERALD_SLAB = REGISTRY.register("light_polished_emerald_slab", () -> new LightPolishedEmeraldSlabBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_EMERALD_STAIRS = REGISTRY.register("light_polished_emerald_stairs", () -> new LightPolishedEmeraldStairsBlock());
	public static final RegistryObject<Block> DARK_POLISHED_EMERALD_BLOCK = REGISTRY.register("dark_polished_emerald_block", () -> new DarkPolishedEmeraldBlockBlock());
	public static final RegistryObject<Block> DARK_POLISHED_EMERALD_SLAB = REGISTRY.register("dark_polished_emerald_slab", () -> new DarkPolishedEmeraldSlabBlock());
	public static final RegistryObject<Block> DARK_POLISHED_EMERALD_STAIRS = REGISTRY.register("dark_polished_emerald_stairs", () -> new DarkPolishedEmeraldStairsBlock());
	public static final RegistryObject<Block> POLISHED_REDSTONE_BLOCK = REGISTRY.register("polished_redstone_block", () -> new PolishedRedstoneBlockBlock());
	public static final RegistryObject<Block> POLISHED_REDSTONE_SLAB = REGISTRY.register("polished_redstone_slab", () -> new PolishedRedstoneSlabBlock());
	public static final RegistryObject<Block> POLISHED_REDSTONE_STAIRS = REGISTRY.register("polished_redstone_stairs", () -> new PolishedRedstoneStairsBlock());
	public static final RegistryObject<Block> POLISHED_RUBY_BLOCK = REGISTRY.register("polished_ruby_block", () -> new PolishedRubyBlockBlock());
	public static final RegistryObject<Block> POLISHED_RUBY_SLAB = REGISTRY.register("polished_ruby_slab", () -> new PolishedRubySlabBlock());
	public static final RegistryObject<Block> POLISHED_RUBY_STAIRS = REGISTRY.register("polished_ruby_stairs", () -> new PolishedRubyStairsBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_COPPER_BLOCK = REGISTRY.register("light_polished_copper_block", () -> new LightPolishedCopperBlockBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_COPPER_SLAB = REGISTRY.register("light_polished_copper_slab", () -> new LightPolishedCopperSlabBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_COPPER_STAIRS = REGISTRY.register("light_polished_copper_stairs", () -> new LightPolishedCopperStairsBlock());
	public static final RegistryObject<Block> DARK_POLISHED_COPPER_BLOCK = REGISTRY.register("dark_polished_copper_block", () -> new DarkPolishedCopperBlockBlock());
	public static final RegistryObject<Block> DARK_POLISHED_COPPER_SLAB = REGISTRY.register("dark_polished_copper_slab", () -> new DarkPolishedCopperSlabBlock());
	public static final RegistryObject<Block> DARK_POLISHED_COPPER_STAIRS = REGISTRY.register("dark_polished_copper_stairs", () -> new DarkPolishedCopperStairsBlock());
	public static final RegistryObject<Block> POLISHED_TOPAZ_BLOCK = REGISTRY.register("polished_topaz_block", () -> new PolishedTopazBlockBlock());
	public static final RegistryObject<Block> POLISHED_TOPAZ_SLAB = REGISTRY.register("polished_topaz_slab", () -> new PolishedTopazSlabBlock());
	public static final RegistryObject<Block> POLISHED_TOPAZ_STAIRS = REGISTRY.register("polished_topaz_stairs", () -> new PolishedTopazStairsBlock());
	public static final RegistryObject<Block> POLISHED_GOLD_BLOCK = REGISTRY.register("polished_gold_block", () -> new PolishedGoldBlockBlock());
	public static final RegistryObject<Block> POLISHED_GOLD_SLAB = REGISTRY.register("polished_gold_slab", () -> new PolishedGoldSlabBlock());
	public static final RegistryObject<Block> POLISHED_GOLD_STAIRS = REGISTRY.register("polished_gold_stairs", () -> new PolishedGoldStairsBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_GOLD_BLOCK = REGISTRY.register("light_polished_gold_block", () -> new LightPolishedGoldBlockBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_GOLD_SLAB = REGISTRY.register("light_polished_gold_slab", () -> new LightPolishedGoldSlabBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_GOLD_STAIRS = REGISTRY.register("light_polished_gold_stairs", () -> new LightPolishedGoldStairsBlock());
	public static final RegistryObject<Block> POLISHED_NETHER_DIAMOND_BLOCK = REGISTRY.register("polished_nether_diamond_block", () -> new PolishedNetherDiamondBlockBlock());
	public static final RegistryObject<Block> POLISHED_NETHER_DIAMOND_SLAB = REGISTRY.register("polished_nether_diamond_slab", () -> new PolishedNetherDiamondSlabBlock());
	public static final RegistryObject<Block> POLISHED_NETHER_DIAMOND_STAIRS = REGISTRY.register("polished_nether_diamond_stairs", () -> new PolishedNetherDiamondStairsBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_NETHER_DIAMOND_BLOCK = REGISTRY.register("light_polished_nether_diamond_block", () -> new LightPolishedNetherDiamondBlockBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_NETHER_DIAMOND_SLAB = REGISTRY.register("light_polished_nether_diamond_slab", () -> new LightPolishedNetherDiamondSlabBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_NETHER_DIAMOND_STAIRS = REGISTRY.register("light_polished_nether_diamond_stairs", () -> new LightPolishedNetherDiamondStairsBlock());
	public static final RegistryObject<Block> POLISHED_AMETHYST_BLOCK = REGISTRY.register("polished_amethyst_block", () -> new PolishedAmethystBlockBlock());
	public static final RegistryObject<Block> POLISHED_AMETHYST_SLAB = REGISTRY.register("polished_amethyst_slab", () -> new PolishedAmethystSlabBlock());
	public static final RegistryObject<Block> POLISHED_AMETHYST_STAIRS = REGISTRY.register("polished_amethyst_stairs", () -> new PolishedAmethystStairsBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_AMETHYST_BLOCK = REGISTRY.register("light_polished_amethyst_block", () -> new LightPolishedAmethystBlockBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_AMETHYST_SLAB = REGISTRY.register("light_polished_amethyst_slab", () -> new LightPolishedAmethystSlabBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_AMETHYST_STAIRS = REGISTRY.register("light_polished_amethyst_stairs", () -> new LightPolishedAmethystStairsBlock());
	public static final RegistryObject<Block> DARK_POLISHED_COAL_TRAPDOOR = REGISTRY.register("dark_polished_coal_trapdoor", () -> new DarkPolishedCoalTrapdoorBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_COAL_TRAPDOOR = REGISTRY.register("light_polished_coal_trapdoor", () -> new LightPolishedCoalTrapdoorBlock());
	public static final RegistryObject<Block> DARK_POLISHED_IRON_TRAPDOOR = REGISTRY.register("dark_polished_iron_trapdoor", () -> new DarkPolishedIronTrapdoorBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_IRON_TRAPDOOR = REGISTRY.register("light_polished_iron_trapdoor", () -> new LightPolishedIronTrapdoorBlock());
	public static final RegistryObject<Block> DARK_POLISHED_LAPIS_TRAPDOOR = REGISTRY.register("dark_polished_lapis_trapdoor", () -> new DarkPolishedLapisTrapdoorBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_LAPIS_TRAPDOOR = REGISTRY.register("light_polished_lapis_trapdoor", () -> new LightPolishedLapisTrapdoorBlock());
	public static final RegistryObject<Block> POLISHED_DIAMOND_TRAPDOOR = REGISTRY.register("polished_diamond_trapdoor", () -> new PolishedDiamondTrapdoorBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_EMERALD_TRAPDOOR = REGISTRY.register("light_polished_emerald_trapdoor", () -> new LightPolishedEmeraldTrapdoorBlock());
	public static final RegistryObject<Block> DARK_POLISHED_EMERALD_TRAPDOOR = REGISTRY.register("dark_polished_emerald_trapdoor", () -> new DarkPolishedEmeraldTrapdoorBlock());
	public static final RegistryObject<Block> POLISHED_REDSTONE_TRAPDOOR = REGISTRY.register("polished_redstone_trapdoor", () -> new PolishedRedstoneTrapdoorBlock());
	public static final RegistryObject<Block> POLISHED_RUBY_TRAPDOOR = REGISTRY.register("polished_ruby_trapdoor", () -> new PolishedRubyTrapdoorBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_COPPER_TRAPDOOR = REGISTRY.register("light_polished_copper_trapdoor", () -> new LightPolishedCopperTrapdoorBlock());
	public static final RegistryObject<Block> DARK_POLISHED_COPPER_TRAPDOOR = REGISTRY.register("dark_polished_copper_trapdoor", () -> new DarkPolishedCopperTrapdoorBlock());
	public static final RegistryObject<Block> POLISHED_TOPAZ_TRAPDOOR = REGISTRY.register("polished_topaz_trapdoor", () -> new PolishedTopazTrapdoorBlock());
	public static final RegistryObject<Block> POLISHED_GOLD_TRAPDOOR = REGISTRY.register("polished_gold_trapdoor", () -> new PolishedGoldTrapdoorBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_GOLD_TRAPDOOR = REGISTRY.register("light_polished_gold_trapdoor", () -> new LightPolishedGoldTrapdoorBlock());
	public static final RegistryObject<Block> POLISHED_NETHER_DIAMOND_TRAPDOOR = REGISTRY.register("polished_nether_diamond_trapdoor", () -> new PolishedNetherDiamondTrapdoorBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_NETHER_DIAMOND_TRAPDOOR = REGISTRY.register("light_polished_nether_diamond_trapdoor", () -> new LightPolishedNetherDiamondTrapdoorBlock());
	public static final RegistryObject<Block> POLISHED_AMETHYST_TRAPDOOR = REGISTRY.register("polished_amethyst_trapdoor", () -> new PolishedAmethystTrapdoorBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_AMETHYST_TRAPDOOR = REGISTRY.register("light_polished_amethyst_trapdoor", () -> new LightPolishedAmethystTrapdoorBlock());
	public static final RegistryObject<Block> DARK_POLISHED_COAL_WALL = REGISTRY.register("dark_polished_coal_wall", () -> new DarkPolishedCoalWallBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_COAL_WALL = REGISTRY.register("light_polished_coal_wall", () -> new LightPolishedCoalWallBlock());
	public static final RegistryObject<Block> DARK_POLISHED_IRON_WALL = REGISTRY.register("dark_polished_iron_wall", () -> new DarkPolishedIronWallBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_IRON_WALL = REGISTRY.register("light_polished_iron_wall", () -> new LightPolishedIronWallBlock());
	public static final RegistryObject<Block> DARK_POLISHED_LAPIS_WALL = REGISTRY.register("dark_polished_lapis_wall", () -> new DarkPolishedLapisWallBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_LAPIS_WALL = REGISTRY.register("light_polished_lapis_wall", () -> new LightPolishedLapisWallBlock());
	public static final RegistryObject<Block> POLISHED_DIAMOND_WALL = REGISTRY.register("polished_diamond_wall", () -> new PolishedDiamondWallBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_EMERALD_WALL = REGISTRY.register("light_polished_emerald_wall", () -> new LightPolishedEmeraldWallBlock());
	public static final RegistryObject<Block> DARK_POLISHED_EMERALD_WALL = REGISTRY.register("dark_polished_emerald_wall", () -> new DarkPolishedEmeraldWallBlock());
	public static final RegistryObject<Block> POLISHED_REDSTONE_WALL = REGISTRY.register("polished_redstone_wall", () -> new PolishedRedstoneWallBlock());
	public static final RegistryObject<Block> POLISHED_RUBY_WALL = REGISTRY.register("polished_ruby_wall", () -> new PolishedRubyWallBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_COPPER_WALL = REGISTRY.register("light_polished_copper_wall", () -> new LightPolishedCopperWallBlock());
	public static final RegistryObject<Block> DARK_POLISHED_COPPER_WALL = REGISTRY.register("dark_polished_copper_wall", () -> new DarkPolishedCopperWallBlock());
	public static final RegistryObject<Block> POLISHED_TOPAZ_WALL = REGISTRY.register("polished_topaz_wall", () -> new PolishedTopazWallBlock());
	public static final RegistryObject<Block> POLISHED_GOLD_WALL = REGISTRY.register("polished_gold_wall", () -> new PolishedGoldWallBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_GOLD_WALL = REGISTRY.register("light_polished_gold_wall", () -> new LightPolishedGoldWallBlock());
	public static final RegistryObject<Block> POLISHED_NETHER_DIAMOND_WALL = REGISTRY.register("polished_nether_diamond_wall", () -> new PolishedNetherDiamondWallBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_NETHER_DIAMOND_WALL = REGISTRY.register("light_polished_nether_diamond_wall", () -> new LightPolishedNetherDiamondWallBlock());
	public static final RegistryObject<Block> POLISHED_AMETHYST_WALL = REGISTRY.register("polished_amethyst_wall", () -> new PolishedAmethystWallBlock());
	public static final RegistryObject<Block> LIGHT_POLISHED_AMETHYST_WALL = REGISTRY.register("light_polished_amethyst_wall", () -> new LightPolishedAmethystWallBlock());
	public static final RegistryObject<Block> CRYSTALLITE_CLUSTER_AIR = REGISTRY.register("crystallite_cluster_air", () -> new CrystalliteClusterAirBlock());
	public static final RegistryObject<Block> CLIMBABLE_WALL = REGISTRY.register("climbable_wall", () -> new ClimbableWallBlock());
	public static final RegistryObject<Block> JEWELLERY_TABLE = REGISTRY.register("jewellery_table", () -> new JewelleryTableBlock());
	// Start of user code block custom blocks
	public static final BlockBehaviour.Properties METAL_SIGN_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.OAK_SIGN).explosionResistance(6).destroyTime(2);
	public static final BlockBehaviour.Properties METAL_WALL_SIGN_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN).explosionResistance(6).destroyTime(2);
	public static final BlockBehaviour.Properties METAL_HANGING_SIGN_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN).explosionResistance(6).destroyTime(2);
	public static final BlockBehaviour.Properties METAL_WALL_HANGING_SIGN_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN).explosionResistance(6).destroyTime(2);
	public static final RegistryObject<Block> BLACK_METAL_SIGN = REGISTRY.register("black_metal_sign", () -> new MetalSignBlock(METAL_SIGN_PROPERTIES), BetterToolsModWoodTypes.BLACK_METAL_SIGN);
	public static final RegistryObject<Block> BLACK_METAL_WALL_SIGN = REGISTRY.register("black_metal_wall_sign", () -> new MetalWallSignBlock(METAL_WALL_SIGN_PROPERTIES), BetterToolsModWoodTypes.BLACK_METAL_SIGN);
	public static final RegistryObject<Block> BLACK_METAL_HANGING_SIGN = REGISTRY.register("black_metal_hanging_sign", () -> new MetalHangingSignBlock(METAL_HANGING_SIGN_PROPERTIES), BetterToolsModWoodTypes.BLACK_METAL_SIGN);
	public static final RegistryObject<Block> BLACK_METAL_WALL_HANGING_SIGN = REGISTRY.register("black_metal_wall_hanging_sign", () -> new MetalWallHangingSignBlock(METAL_WALL_HANGING_SIGN_PROPERTIES), BetterToolsModWoodTypes.BLACK_METAL_SIGN);
	// End of user code block custom blocks
}
