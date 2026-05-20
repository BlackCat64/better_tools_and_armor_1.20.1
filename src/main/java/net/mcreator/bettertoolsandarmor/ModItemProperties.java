package net.mcreator.bettertoolsandarmor;

import net.neoforged.fml.common.EventBusSubscriber;

import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

//@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModItemProperties {
	public static void addCustomItemProperties() {
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_IRON.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_GOLD.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_LAPIS.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_REDSTONE.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_DIAMOND.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_NETHERITE.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_RUBY.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_SAPPHIRE.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_TOPAZ.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_NETHER_DIAMOND.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_EMERALD.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_SCULK.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_SKY.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_AMETHYST.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_PRISMARINE.get());
		makeBow(BetterToolsModItems.CRYSTALLITE_BOW_HONEY.get());
	}

	private static void makeBow(Item item) {
		ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
			if (p_174637_ == null) {
				return 0.0f;
			} else {
				return p_174637_.getUseItem() != p_174635_ ? 0.0f : (float) (p_174635_.getUseDuration(p_174637_) - p_174637_.getUseItemRemainingTicks()) / 10.0f;
			}
		});
		ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
			return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0f : 0.0f;
		});
	}
}
