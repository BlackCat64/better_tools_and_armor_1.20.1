
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class CrystalliteCharmBaseItem extends Item {
	public CrystalliteCharmBaseItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.COMMON));
	}
}
