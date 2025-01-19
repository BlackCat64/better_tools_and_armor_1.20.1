package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class SapphireSwordTooltipProcedure {
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
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:freezing_tools")))) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(((LivingEntity) entity).getAttribute(BetterToolsModAttributes.ATTACKFREEZECHANCE.get()).getValue() * 100) + "% Freeze Chance")));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(((LivingEntity) entity).getAttribute(BetterToolsModAttributes.ATTACKFREEZETIME.get()).getValue() / 20) + "s Freeze Time")));
			} else {
				if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).is_in_cold_biome) {
					tooltip.add(Component.literal(("\u00A72 " + (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items"))) ? "40" : "20") + "% Freeze Chance")));
					tooltip.add(Component.literal(("\u00A72 " + (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items"))) ? "15" : "10") + "s Freeze Time")));
				} else {
					tooltip.add(Component.literal(("\u00A72 " + (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items"))) ? "20" : "10") + "% Freeze Chance")));
					tooltip.add(Component.literal(("\u00A72 " + (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items"))) ? "10" : "5") + "s Freeze Time")));
				}
			}
		}
	}
}
