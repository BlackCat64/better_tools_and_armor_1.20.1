package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.function.Supplier;
import java.util.Map;

public class EnergyVialMenuClosedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double i = 0;
		ItemStack fuel = ItemStack.EMPTY;
		fuel = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY);
		if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.ENERGY_VIAL.get(), lv).isPresent() : false) {
			int x = 0;
		} else {
			if (entity instanceof Player _player) {
				ItemStack _setstack = ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (!(fuel.getItem() == ItemStack.EMPTY.getItem())) {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, (entity.getX()), (entity.getY()), (entity.getZ()), fuel);
				entityToSpawn.setPickUpDelay(0);
				_level.addFreshEntity(entityToSpawn);
			}
		}
		for (int index0 = 0; index0 < 6; index0++) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get((int) i)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			i = i + 1;
		}
	}
}
