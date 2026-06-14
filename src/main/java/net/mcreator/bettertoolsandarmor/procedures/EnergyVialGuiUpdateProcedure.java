package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;

import net.mcreator.bettertoolsandarmor.world.inventory.EnergyVialMenuMenu;
import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMenus;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

@EventBusSubscriber
public class EnergyVialGuiUpdateProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
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
		if (entity instanceof Player _plr0 && _plr0.containerMenu instanceof EnergyVialMenuMenu) {
			fuel = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu1 ? _menu1.getSlots().get(0).getItem() : ItemStack.EMPTY);
			vial = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu3 ? _menu3.getSlots().get(1).getItem() : ItemStack.EMPTY);
			BetterToolsMod.LOGGER.info("" + entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).energy_vial_slot);
			if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).energy_vial_slot >= 0 && !((BuiltInRegistries.ITEM.getKey((entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandler5
					? _modHandler5.getStackInSlot((int) entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).energy_vial_slot).copy()
					: ItemStack.EMPTY).getItem()).toString()).equals(BuiltInRegistries.ITEM.getKey(vial.getItem()).toString()))) {
				if (entity instanceof Player _player)
					_player.closeContainer();
				return;
			}
			energy = vial.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy");
			max_energy = GetEnergyVialCapacityProcedure.execute(vial);
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
				helmet_active = (entity instanceof Player _entity19 && _entity19.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu19) && _menu19.getMenuState(1, "helmet_active", false);
				chestplate_active = (entity instanceof Player _entity20 && _entity20.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu20) && _menu20.getMenuState(1, "chestplate_active", false);
				leggings_active = (entity instanceof Player _entity21 && _entity21.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu21) && _menu21.getMenuState(1, "leggings_active", false);
				boots_active = (entity instanceof Player _entity22 && _entity22.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu22) && _menu22.getMenuState(1, "boots_active", false);
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
				BetterToolsMod.LOGGER.info("" + helmet_active);
				BetterToolsMod.LOGGER.info("" + chestplate_active);
				BetterToolsMod.LOGGER.info("" + leggings_active);
				BetterToolsMod.LOGGER.info("" + boots_active);
				BetterToolsMod.LOGGER.info("========================================");
			}
			if (PlayerHasEnergyVialEquippedProcedure.execute(world, entity)) {
				GetEquippedVialProcedure.execute(world, entity).applyComponents(vial.getComponents());
			} else if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).energy_vial_slot >= 0) {
				(entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandler28
						? _modHandler28.getStackInSlot((int) entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).energy_vial_slot)
						: ItemStack.EMPTY).applyComponents(vial.getComponents());
			}
		}
	}
}