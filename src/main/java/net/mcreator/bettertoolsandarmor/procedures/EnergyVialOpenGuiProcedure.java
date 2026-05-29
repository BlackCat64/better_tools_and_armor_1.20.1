package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.world.inventory.EnergyVialMenuMenu;
import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMenus;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import io.netty.buffer.Unpooled;

public class EnergyVialOpenGuiProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		ItemStack vial = ItemStack.EMPTY;
		if (!(entity instanceof Player _plr0 && _plr0.containerMenu instanceof EnergyVialMenuMenu)) {
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = new BlockPos(0, -65, 0);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("EnergyVialMenu");
					}

					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new EnergyVialMenuMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
			if (PlayerHasEnergyVialEquippedProcedure.execute(entity)) {
				vial = GetEquippedVialProcedure.execute().copy();
				if (entity instanceof Player _player && _player.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu) {
					ItemStack _setstack = vial.copy();
					_setstack.setCount(1);
					_menu.getSlots().get(1).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
				{
					BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
					_vars.energy_vial_to_update = vial.copy();
					_vars.syncPlayerVariables(entity);
				}
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:energy_vials")))) {
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
				if (entity instanceof Player _player && _player.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu) {
					ItemStack _setstack = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
					_setstack.setCount(1);
					_menu.getSlots().get(1).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
				BetterToolsMod.queueServerWork(1, () -> {
					(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
				});
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu) {
				ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).copy();
				_setstack.setCount(1);
				_menu.getSlots().get(2).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu) {
				ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).copy();
				_setstack.setCount(1);
				_menu.getSlots().get(3).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu) {
				ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).copy();
				_setstack.setCount(1);
				_menu.getSlots().get(4).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof BetterToolsModMenus.MenuAccessor _menu) {
				ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).copy();
				_setstack.setCount(1);
				_menu.getSlots().get(5).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
	}
}