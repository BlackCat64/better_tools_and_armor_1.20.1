
/*
*	MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.common.BasicItemListing;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.npc.VillagerProfession;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BetterToolsModTrades {
	@SubscribeEvent
	public static void registerWanderingTrades(WandererTradesEvent event) {
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 2),

				new ItemStack(BetterToolsModBlocks.BLUE_MUSHROOM.get()), 12, 5, 0.05f));
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 2),

				new ItemStack(BetterToolsModBlocks.PURPLE_MUSHROOM.get()), 12, 5, 0.05f));
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD),

				new ItemStack(BetterToolsModBlocks.FOUR_LEAF_CLOVER.get()), 12, 5, 0.05f));
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 6),

				new ItemStack(BetterToolsModBlocks.SUGAR_BLOCK.get()), 4, 5, 0.05f));
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 3),

				new ItemStack(BetterToolsModBlocks.FLINT_BLOCK.get()), 7, 5, 0.05f));
		event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 3),

				new ItemStack(BetterToolsModBlocks.BLACKSTONE_MAGMA.get()), 5, 5, 0.05f));
	}

	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.WEAPONSMITH) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(BetterToolsModItems.NETHER_DIAMOND.get()), new ItemStack(Items.EMERALD, 5), new ItemStack(Items.DIAMOND), 16, 15, 0.1f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.DIAMOND), new ItemStack(Items.EMERALD, 5), new ItemStack(BetterToolsModItems.NETHER_DIAMOND.get()), 16, 15, 0.1f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.EMERALD, 20),

					new ItemStack(BetterToolsModItems.NETHER_DIAMOND_DAGGER.get()), 3, 25, 0.2f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),

					new ItemStack(BetterToolsModItems.DIAMOND_DAGGER.get()), 3, 25, 0.2f));
		}
		if (event.getType() == BetterToolsModVillagerProfessions.JEWELLER.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(BetterToolsModItems.RUBY.get(), 3),

					new ItemStack(Items.EMERALD), 16, 10, 0.02f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(BetterToolsModItems.SAPPHIRE.get(), 2),

					new ItemStack(Items.EMERALD), 16, 15, 0.02f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(BetterToolsModItems.CRYSTALLITE_CHARM_BASE.get()), new ItemStack(Items.EMERALD, 30), new ItemStack(BetterToolsModItems.CRYSTALLITE_CHARM_BASE.get(), 2), 5, 10, 0f));
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(BetterToolsModItems.TOPAZ.get(), 2),

					new ItemStack(Items.EMERALD), 16, 15, 0.02f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(BetterToolsModBlocks.PURPLE_MUSHROOM.get(), 16), new ItemStack(Items.EMERALD, 12), new ItemStack(BetterToolsModItems.POISON_CHARM.get()), 3, 30, 0.01f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(BetterToolsModBlocks.FOUR_LEAF_CLOVER.get(), 12), new ItemStack(Items.EMERALD, 12), new ItemStack(BetterToolsModItems.LUCKY_CHARM.get()), 3, 30, 0.01f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Blocks.LAPIS_BLOCK, 5), new ItemStack(Items.EMERALD, 12), new ItemStack(BetterToolsModItems.MAGIC_RING.get()), 3, 30, 0.01f));
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Blocks.IRON_BLOCK, 7), new ItemStack(Items.EMERALD, 16), new ItemStack(BetterToolsModItems.TOUGH_NECKLACE.get()), 2, 40, 0.01f));
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(BetterToolsModBlocks.TOPAZ_BLOCK.get(), 5), new ItemStack(Items.EMERALD, 16), new ItemStack(BetterToolsModItems.ELECTRIC_NECKLACE.get()), 2, 50, 0.01f));
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Blocks.GOLD_BLOCK, 5), new ItemStack(Items.EMERALD, 16), new ItemStack(BetterToolsModItems.GILDED_BRACELET.get()), 2, 40, 0.01f));
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(BetterToolsModBlocks.SAPPHIRE_BLOCK.get(), 5), new ItemStack(Items.EMERALD, 24), new ItemStack(BetterToolsModItems.ICY_BRACELET.get()), 2, 50, 0.01f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(BetterToolsModBlocks.NETHER_DIAMOND_BLOCK.get(), 2), new ItemStack(Items.EMERALD, 24), new ItemStack(BetterToolsModItems.FLAMING_CIRCLET.get()), 2, 50, 0.01f));
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Items.DIAMOND, 16), new ItemStack(Items.EMERALD, 24), new ItemStack(BetterToolsModItems.DIAMOND_HARD_PLATE.get()), 2, 40, 0.01f));
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(BetterToolsModItems.GUARDIAN_SPIKE.get(), 10), new ItemStack(Items.EMERALD, 24), new ItemStack(BetterToolsModItems.GUARDIAN_NECKLACE.get()), 2, 50, 0.01f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.EMERALD, 64), new ItemStack(Items.EMERALD, 64), new ItemStack(BetterToolsModItems.NATURE_RING.get()), 2, 50, 0.01f));
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Blocks.REDSTONE_BLOCK, 8), new ItemStack(Items.EMERALD, 40), new ItemStack(BetterToolsModItems.HEART_CHARM.get()), 2, 40, 0.01f));
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(BetterToolsModBlocks.RUBY_BLOCK.get(), 8), new ItemStack(Items.EMERALD, 40), new ItemStack(BetterToolsModItems.SPEEDY_NECKLACE.get()), 2, 50, 0.01f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.END_CRYSTAL, 4), new ItemStack(Items.EMERALD, 40), new ItemStack(BetterToolsModItems.REFLECT_CHARM.get()), 2, 50, 0.01f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Blocks.AMETHYST_CLUSTER, 10), new ItemStack(Items.EMERALD, 40), new ItemStack(BetterToolsModItems.EARTH_CIRCLET.get()), 2, 50, 0.01f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(BetterToolsModBlocks.BLUE_SLIME_BLOCK.get(), 6), new ItemStack(Items.EMERALD, 12), new ItemStack(BetterToolsModItems.BOUNCY_BRACELET.get()), 3, 30, 0.01f));
		}
	}
}
