package net.mcreator.bettertoolsandarmor.init;

import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.CuriosCapability;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.procedures.ToughNecklaceUnequippedProcedure;
import net.mcreator.bettertoolsandarmor.procedures.ToughNecklaceEquippedProcedure;

public class BetterToolsModCuriosCompat {
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(CuriosCapability.ITEM, (stack, context) -> new ICurio() {
			@Override
			public ItemStack getStack() {
				return stack;
			}

			@Override
			public SoundInfo getEquipSound(SlotContext slotContext) {
				return new SoundInfo(DeferredHolder.create(Registries.SOUND_EVENT, ResourceLocation.parse("better_tools:crystallite_place")).value(), 1, 1);
			}

			@Override
			public void onEquip(SlotContext slotContext, ItemStack prevStack) {
				ToughNecklaceEquippedProcedure.execute();
			}

			@Override
			public void onUnequip(SlotContext slotContext, ItemStack newStack) {
				ToughNecklaceUnequippedProcedure.execute();
			}
		}, BetterToolsModItems.TOUGH_NECKLACE.get());
	}
}