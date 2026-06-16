package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class CriticalityWeaponTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, entity, itemstack, tooltip);
	}

private static void execute(
@Nullable Event event,
Entity entity,
ItemStack itemstack,
List<Component> tooltip ) {
if (
entity == null ||
tooltip == null ) return ;
if (!=1.5) {if (itemstack.is(ItemTags.create(ResourceLocation.parse("forge:tools")))&&(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem():ItemStack.EMPTY).getItem() == itemstack.getItem()) {tooltip.add(Component.literal(("\u00A72 "+new java.text.DecimalFormat("##.##").format()+"x Critical Hit Multiplier")));}}
}
}