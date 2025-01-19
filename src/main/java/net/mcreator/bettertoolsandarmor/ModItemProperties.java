/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside net.mcreator.bettertoolsandarmor as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package net.mcreator.bettertoolsandarmor;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
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
		ItemProperties.register(item, new ResourceLocation("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
			if (p_174637_ == null) {
				return 0.0f;
			} else {
				return p_174637_.getUseItem() != p_174635_ ? 0.0f : (float) (p_174635_.getUseDuration() - p_174637_.getUseItemRemainingTicks()) / 10.0f;
			}
		});
		ItemProperties.register(item, new ResourceLocation("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
			return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0f : 0.0f;
		});
	}
}
