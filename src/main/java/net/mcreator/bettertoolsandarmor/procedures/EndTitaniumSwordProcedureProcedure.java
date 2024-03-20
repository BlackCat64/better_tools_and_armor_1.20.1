package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class EndTitaniumSwordProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		AttributeModifier damage_modifier = null;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.END_TITANIUM_SWORD.get()
				|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.END_TITANIUM_DAGGER.get()
				|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.END_TITANIUM_AXE.get()) {
			if ((entity.level().dimension()) == Level.END) {
				if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.hasModifier((new AttributeModifier(UUID.fromString("711c2ab8-161f-4f61-b30e-e7d41cfd1d5b"), "ender_titanium_sword", 2.5, AttributeModifier.Operation.ADDITION)))))
					((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.addTransientModifier((new AttributeModifier(UUID.fromString("711c2ab8-161f-4f61-b30e-e7d41cfd1d5b"), "ender_titanium_sword", 2.5, AttributeModifier.Operation.ADDITION)));
			} else {
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).removeModifier(UUID.fromString("711c2ab8-161f-4f61-b30e-e7d41cfd1d5b"));
			}
		} else {
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).removeModifier(UUID.fromString("711c2ab8-161f-4f61-b30e-e7d41cfd1d5b"));
		}
	}
}
