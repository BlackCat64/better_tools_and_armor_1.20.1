
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import net.mcreator.bettertoolsandarmor.enchantment.ThunderShotEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.SmeltingTouchEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.LifeAuraEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.KarmaCurseEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.FreezeShotEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.ExperienceEnchEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.DesperationEnchantment;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, BetterToolsMod.MODID);
	public static final RegistryObject<Enchantment> EXPERIENCE_ENCH = REGISTRY.register("experience_ench", () -> new ExperienceEnchEnchantment());
	public static final RegistryObject<Enchantment> KARMA_CURSE = REGISTRY.register("karma_curse", () -> new KarmaCurseEnchantment());
	public static final RegistryObject<Enchantment> THUNDER_SHOT = REGISTRY.register("thunder_shot", () -> new ThunderShotEnchantment());
	public static final RegistryObject<Enchantment> FREEZE_SHOT = REGISTRY.register("freeze_shot", () -> new FreezeShotEnchantment());
	public static final RegistryObject<Enchantment> LIFE_AURA = REGISTRY.register("life_aura", () -> new LifeAuraEnchantment());
	public static final RegistryObject<Enchantment> DESPERATION = REGISTRY.register("desperation", () -> new DesperationEnchantment());
	public static final RegistryObject<Enchantment> SMELTING_TOUCH = REGISTRY.register("smelting_touch", () -> new SmeltingTouchEnchantment());
}
