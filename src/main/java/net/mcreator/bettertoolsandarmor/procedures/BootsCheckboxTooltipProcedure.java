package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;
import java.util.Map;

public class BootsCheckboxTooltipProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY).getOrCreateTag().getBoolean("boots_active")
				? "Disable"
				: "Enable") + " Boots effect";
	}
}
