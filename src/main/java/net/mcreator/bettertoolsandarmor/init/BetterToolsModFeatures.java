
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

import net.mcreator.bettertoolsandarmor.world.features.TopazOreFeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.RubyOreFeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.FlintBlockFeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.DeepslateRubyOreFeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.CrystalliteCluster36FeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.CrystalliteCluster36DeepslateFeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.CrystalliteCluster34FeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.CrystalliteCluster34DeepslateFeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.CrystalliteCluster32FeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.CrystalliteCluster32DeepslateFeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.CrystalliteCluster30FeatureFeature;
import net.mcreator.bettertoolsandarmor.world.features.CrystalliteCluster30DeepslateFeatureFeature;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

@Mod.EventBusSubscriber
public class BetterToolsModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, BetterToolsMod.MODID);
	public static final RegistryObject<Feature<?>> FLINT_BLOCK_FEATURE = REGISTRY.register("flint_block_feature", FlintBlockFeatureFeature::new);
	public static final RegistryObject<Feature<?>> RUBY_ORE_FEATURE = REGISTRY.register("ruby_ore_feature", RubyOreFeatureFeature::new);
	public static final RegistryObject<Feature<?>> TOPAZ_ORE_FEATURE = REGISTRY.register("topaz_ore_feature", TopazOreFeatureFeature::new);
	public static final RegistryObject<Feature<?>> CRYSTALLITE_CLUSTER_30_FEATURE = REGISTRY.register("crystallite_cluster_30_feature", CrystalliteCluster30FeatureFeature::new);
	public static final RegistryObject<Feature<?>> CRYSTALLITE_CLUSTER_32_FEATURE = REGISTRY.register("crystallite_cluster_32_feature", CrystalliteCluster32FeatureFeature::new);
	public static final RegistryObject<Feature<?>> CRYSTALLITE_CLUSTER_34_FEATURE = REGISTRY.register("crystallite_cluster_34_feature", CrystalliteCluster34FeatureFeature::new);
	public static final RegistryObject<Feature<?>> CRYSTALLITE_CLUSTER_36_FEATURE = REGISTRY.register("crystallite_cluster_36_feature", CrystalliteCluster36FeatureFeature::new);
	public static final RegistryObject<Feature<?>> DEEPSLATE_RUBY_ORE_FEATURE = REGISTRY.register("deepslate_ruby_ore_feature", DeepslateRubyOreFeatureFeature::new);
	public static final RegistryObject<Feature<?>> CRYSTALLITE_CLUSTER_30_DEEPSLATE_FEATURE = REGISTRY.register("crystallite_cluster_30_deepslate_feature", CrystalliteCluster30DeepslateFeatureFeature::new);
	public static final RegistryObject<Feature<?>> CRYSTALLITE_CLUSTER_32_DEEPSLATE_FEATURE = REGISTRY.register("crystallite_cluster_32_deepslate_feature", CrystalliteCluster32DeepslateFeatureFeature::new);
	public static final RegistryObject<Feature<?>> CRYSTALLITE_CLUSTER_34_DEEPSLATE_FEATURE = REGISTRY.register("crystallite_cluster_34_deepslate_feature", CrystalliteCluster34DeepslateFeatureFeature::new);
	public static final RegistryObject<Feature<?>> CRYSTALLITE_CLUSTER_36_DEEPSLATE_FEATURE = REGISTRY.register("crystallite_cluster_36_deepslate_feature", CrystalliteCluster36DeepslateFeatureFeature::new);
}
