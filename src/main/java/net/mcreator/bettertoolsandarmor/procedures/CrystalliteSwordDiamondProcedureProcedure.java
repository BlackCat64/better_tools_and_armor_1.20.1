package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteSwordDiamondProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getDirectEntity(), event.getAmount());
		}
	}

	public static void execute(Entity entity, Entity immediatesourceentity, double amount) {
		execute(null, entity, immediatesourceentity, amount);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity immediatesourceentity, double amount) {
		if (entity == null || immediatesourceentity == null)
			return;
		double damage = 0;
		double boost = 0;
		if ((immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:precision_weapons")))
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) >= (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
			boost = (immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes"))) ? 2 : 2.5;
			if (immediatesourceentity instanceof LivingEntity _livingEntity6 && _livingEntity6.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
					&& immediatesourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES).critical_hit) {
				boost = boost * (immediatesourceentity instanceof LivingEntity _livingEntity7 && _livingEntity7.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
						? _livingEntity7.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).getValue()
						: 0);
			}
			if (event instanceof LivingIncomingDamageEvent _event) {
				_event.setAmount((float) (amount + boost));
			}
		}
	}
}