package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class EnergyVialGuiUpdateProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		ItemStack fuel = ItemStack.EMPTY;
		ItemStack vial = ItemStack.EMPTY;
		double energy = 0;
		double energy_gain = 0;
		boolean updated = false;
		fuel = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY);
		vial = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY);
		energy = vial.getOrCreateTag().getDouble("energy");
		if (energy < 18000) {
			if (fuel.getItem() == Items.BLAZE_POWDER) {
				energy_gain = 1000;
			} else if (fuel.getItem() == Blocks.NETHER_WART.asItem()) {
				energy_gain = 500;
			}
			fuel.shrink(1);
			vial.getOrCreateTag().putDouble("energy", Math.min(energy + energy_gain, 18000));
		}
		((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).getOrCreateTag().putDouble("energy",
				Math.min(vial.getOrCreateTag().getDouble("energy"), 18000));
		vial.getOrCreateTag().putBoolean("helmet_active", (guistate.containsKey("checkbox:helmet_active") && ((Checkbox) guistate.get("checkbox:helmet_active")).selected()));
		vial.getOrCreateTag().putBoolean("chestplate_active", (guistate.containsKey("checkbox:chestplate_active") && ((Checkbox) guistate.get("checkbox:chestplate_active")).selected()));
		vial.getOrCreateTag().putBoolean("leggings_active", (guistate.containsKey("checkbox:leggings_active") && ((Checkbox) guistate.get("checkbox:leggings_active")).selected()));
		vial.getOrCreateTag().putBoolean("boots_active", (guistate.containsKey("checkbox:boots_active") && ((Checkbox) guistate.get("checkbox:boots_active")).selected()));
		((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).getOrCreateTag().putBoolean("helmet_active",
				(guistate.containsKey("checkbox:helmet_active") && ((Checkbox) guistate.get("checkbox:helmet_active")).selected()));
		((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).getOrCreateTag().putBoolean("chestplate_active",
				(guistate.containsKey("checkbox:chestplate_active") && ((Checkbox) guistate.get("checkbox:chestplate_active")).selected()));
		((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).getOrCreateTag().putBoolean("leggings_active",
				(guistate.containsKey("checkbox:leggings_active") && ((Checkbox) guistate.get("checkbox:leggings_active")).selected()));
		((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).getOrCreateTag().putBoolean("boots_active",
				(guistate.containsKey("checkbox:boots_active") && ((Checkbox) guistate.get("checkbox:boots_active")).selected()));
		if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.ENERGY_VIAL.get(), lv).isPresent() : false) {
			int x = 0;
		} else {
			{
				ItemStack _setval = vial;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.energy_vial_to_update = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
