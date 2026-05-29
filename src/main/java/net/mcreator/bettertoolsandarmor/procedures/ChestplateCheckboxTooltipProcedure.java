package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMenus;

public class ChestplateCheckboxTooltipProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:effect_armor")))) {
			return ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(1).getItem() : ItemStack.EMPTY)
					.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("chestplate_active") ? "Disable" : "Enable") + " Chestplate effect";
		}
		return "No effect to " + ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu4 ? _menu4.getSlots().get(1).getItem() : ItemStack.EMPTY)
				.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("chestplate_active") ? "Disable" : "Enable") + "!";
	}
}