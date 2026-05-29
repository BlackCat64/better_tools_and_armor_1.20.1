/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.BlockSource;

import net.mcreator.bettertoolsandarmor.procedures.LightningStaffDispensedProcedure;
import net.mcreator.bettertoolsandarmor.procedures.IceStaffDispensedProcedure;
import net.mcreator.bettertoolsandarmor.procedures.FireStaffDispensedProcedure;

@EventBusSubscriber
public class BetterToolsModDispenseBehaviors {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			DispenserBlock.registerBehavior(BetterToolsModItems.FIRE_STAFF.get(), new DefaultDispenseItemBehavior() {
				public ItemStack execute(BlockSource blockSource, ItemStack itemstack) {
					return FireStaffDispensedProcedure.execute(blockSource.level(), blockSource.pos().getX(), blockSource.pos().getY(), blockSource.pos().getZ(), blockSource.state().getValue(DispenserBlock.FACING), itemstack.copy(), true);
				}
			});
			DispenserBlock.registerBehavior(BetterToolsModItems.ICE_STAFF.get(), new DefaultDispenseItemBehavior() {
				public ItemStack execute(BlockSource blockSource, ItemStack itemstack) {
					return IceStaffDispensedProcedure.execute(blockSource.level(), blockSource.pos().getX(), blockSource.pos().getY(), blockSource.pos().getZ(), blockSource.state().getValue(DispenserBlock.FACING), itemstack.copy(), true);
				}
			});
			DispenserBlock.registerBehavior(BetterToolsModItems.ELECTRIC_STAFF.get(), new DefaultDispenseItemBehavior() {
				public ItemStack execute(BlockSource blockSource, ItemStack itemstack) {
					return LightningStaffDispensedProcedure.execute(blockSource.level(), blockSource.pos().getX(), blockSource.pos().getY(), blockSource.pos().getZ(), blockSource.state().getValue(DispenserBlock.FACING), itemstack.copy(), true);
				}
			});
		});
	}
}