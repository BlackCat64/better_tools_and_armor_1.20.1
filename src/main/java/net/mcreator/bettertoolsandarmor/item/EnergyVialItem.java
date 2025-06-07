
package net.mcreator.bettertoolsandarmor.item;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class EnergyVialItem extends Item implements ICurioItem {
	public EnergyVialItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
	}
}
