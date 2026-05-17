
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.world.inventory.EnergyVialMenuMenu;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, BetterToolsMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<EnergyVialMenuMenu>> ENERGY_VIAL_MENU = REGISTRY.register("energy_vial_menu", () -> IMenuTypeExtension.create(EnergyVialMenuMenu::new));
}
