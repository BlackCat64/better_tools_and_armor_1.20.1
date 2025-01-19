
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.bettertoolsandarmor.world.inventory.AttributesViewerMenu;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BetterToolsMod.MODID);
	public static final RegistryObject<MenuType<AttributesViewerMenu>> ATTRIBUTES_VIEWER = REGISTRY.register("attributes_viewer", () -> IForgeMenuType.create(AttributesViewerMenu::new));
}
