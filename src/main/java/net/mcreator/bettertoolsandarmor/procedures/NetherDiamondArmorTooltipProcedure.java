package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class NetherDiamondArmorTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		double armor_pieces = 0;
		double i = 0;
		double time = 0;
		boolean crystallite = false;
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:flaming_armor")))) {
			for (int index0 = 0; index0 < 4; index0++) {
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) i)) : ItemStack.EMPTY)
						.is(ItemTags.create(new ResourceLocation("better_tools:flaming_armor")))) {
					armor_pieces = armor_pieces + 1;
				}
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) i)) : ItemStack.EMPTY)
						.is(ItemTags.create(new ResourceLocation("better_tools:nether_diamond_upgraded_crystallite_items")))) {
					crystallite = true;
				}
				i = i + 1;
			}
			tooltip.add(Component.literal("\u00A77When on fire:"));
			tooltip.add(Component.literal(("\u00A72" + new java.text.DecimalFormat("#").format(Math.min(6 - armor_pieces, 5)) + "s Max Burn Time")));
			if (crystallite || itemstack.is(ItemTags.create(new ResourceLocation("better_tools:nether_diamond_upgraded_crystallite_items")))) {
				time = armor_pieces == 4 ? 15 : armor_pieces * 3;
				if (armor_pieces == 0) {
					tooltip.add(Component.literal("\u00A77For each armor piece worn:"));
					time = 3;
				}
			} else {
				tooltip.add(Component.literal("\u00A77When full set worn:"));
				time = 5;
			}
			if ((entity.level().dimension()) == Level.NETHER) {
				time = time * 2;
			}
			tooltip.add(Component.literal(("\u00A72" + new java.text.DecimalFormat("#").format(time) + "s Fire Resistance")));
		}
	}
}
