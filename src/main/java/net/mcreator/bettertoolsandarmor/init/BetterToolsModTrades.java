
/*
*	MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.npc.VillagerProfession;

@EventBusSubscriber
public class BetterToolsModTrades {
	@SubscribeEvent
	public static void registerWanderingTrades(WandererTradesEvent event) {
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 2), new ItemStack(BetterToolsModBlocks.BLUE_MUSHROOM.get()), 12, 5, 0.05f));
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 2), new ItemStack(BetterToolsModBlocks.PURPLE_MUSHROOM.get()), 12, 5, 0.05f));
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(BetterToolsModBlocks.FOUR_LEAF_CLOVER.get()), 12, 5, 0.05f));
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 6), new ItemStack(BetterToolsModBlocks.SUGAR_BLOCK.get()), 4, 5, 0.05f));
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 3), new ItemStack(BetterToolsModBlocks.BLACKSTONE_MAGMA.get()), 5, 5, 0.05f));
	}

	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.WEAPONSMITH) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(BetterToolsModItems.NETHER_DIAMOND.get()), new ItemStack(Items.EMERALD, 5), new ItemStack(Items.DIAMOND), 16, 15, 0.1f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.DIAMOND), new ItemStack(Items.EMERALD, 5), new ItemStack(BetterToolsModItems.NETHER_DIAMOND.get()), 16, 15, 0.1f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.EMERALD, 20), new ItemStack(BetterToolsModItems.NETHER_DIAMOND_DAGGER.get()), 3, 25, 0.2f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.EMERALD, 16), new ItemStack(BetterToolsModItems.DIAMOND_DAGGER.get()), 3, 25, 0.2f));
		}
	}
}
