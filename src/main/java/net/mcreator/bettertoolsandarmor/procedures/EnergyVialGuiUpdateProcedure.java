package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMenus;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class EnergyVialGuiUpdateProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double energy = 0;
		double energy_gain = 0;
		double max_energy = 0;
		boolean helmet_active = false;
		boolean chestplate_active = false;
		boolean leggings_active = false;
		boolean boots_active = false;
		ItemStack fuel = ItemStack.EMPTY;
		ItemStack vial = ItemStack.EMPTY;
		fuel = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(0).getItem() : ItemStack.EMPTY);
		vial = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(1).getItem() : ItemStack.EMPTY);
		if (PlayerHasEnergyVialEquippedProcedure.execute(world, entity)) {
			energy = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).energy_vial_to_update.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy");
		} else {
			energy = vial.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy");
		}
		max_energy = GetEnergyVialCapacityProcedure.execute(vial);
		if (world instanceof ServerLevel _level) {
			_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal((energy + "/" + max_energy)), false);
		}
		if (energy < max_energy) {
			if (fuel.getItem() == BetterToolsModItems.ULTRA_ENRICHED_BLAZE_POWDER.get()) {
				energy_gain = 20000;
			} else if (fuel.getItem() == BetterToolsModItems.SUPER_ENRICHED_BLAZE_POWDER.get()) {
				energy_gain = 10000;
			} else if (fuel.getItem() == BetterToolsModItems.ENRICHED_BLAZE_POWDER.get()) {
				energy_gain = 5000;
			} else if (fuel.getItem() == Items.BLAZE_POWDER) {
				energy_gain = 1000;
			} else if (fuel.getItem() == Blocks.NETHER_WART.asItem()) {
				energy_gain = 500;
			}
			fuel.shrink(1);
			{
				final String _tagName = "energy";
				final double _tagValue = Math.min(energy + energy_gain, max_energy);
				CustomData.update(DataComponents.CUSTOM_DATA, vial, tag -> tag.putDouble(_tagName, _tagValue));
			}
		}
		if (world.isClientSide()) {
			helmet_active = (entity instanceof Player _entity15 && _entity15.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu15) && _menu15.getMenuState(1, "helmet_active", false);
			chestplate_active = (entity instanceof Player _entity16 && _entity16.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu16) && _menu16.getMenuState(1, "chestplate_active", false);
			leggings_active = (entity instanceof Player _entity17 && _entity17.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu17) && _menu17.getMenuState(1, "leggings_active", false);
			boots_active = (entity instanceof Player _entity18 && _entity18.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu18) && _menu18.getMenuState(1, "boots_active", false);
			{
				final String _tagName = "helmet_active";
				final boolean _tagValue = helmet_active;
				CustomData.update(DataComponents.CUSTOM_DATA, vial, tag -> tag.putBoolean(_tagName, _tagValue));
			}
			{
				final String _tagName = "chestplate_active";
				final boolean _tagValue = chestplate_active;
				CustomData.update(DataComponents.CUSTOM_DATA, vial, tag -> tag.putBoolean(_tagName, _tagValue));
			}
			{
				final String _tagName = "leggings_active";
				final boolean _tagValue = leggings_active;
				CustomData.update(DataComponents.CUSTOM_DATA, vial, tag -> tag.putBoolean(_tagName, _tagValue));
			}
			{
				final String _tagName = "boots_active";
				final boolean _tagValue = boots_active;
				CustomData.update(DataComponents.CUSTOM_DATA, vial, tag -> tag.putBoolean(_tagName, _tagValue));
			}
		}
		if (PlayerHasEnergyVialEquippedProcedure.execute(world, entity)) {
			entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).energy_vial_to_update.applyComponents(vial.getComponents());
		} else {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.energy_vial_to_update = vial;
				_vars.markSyncDirty();
			}
		}
	}
}