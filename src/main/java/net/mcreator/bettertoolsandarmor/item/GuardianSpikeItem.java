
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class GuardianSpikeItem extends Item {
	public GuardianSpikeItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
