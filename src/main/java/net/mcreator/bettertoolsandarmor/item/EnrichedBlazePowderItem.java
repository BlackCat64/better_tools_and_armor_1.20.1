
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class EnrichedBlazePowderItem extends Item {
	public EnrichedBlazePowderItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
