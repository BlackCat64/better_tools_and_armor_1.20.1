package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@EventBusSubscriber
public class FlintToolsIncreasedCritDamageProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:flint_tools")))) {
			if (entity instanceof LivingEntity _entity) {
				AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:flint_tools_crit_multiplier"), 0.25, AttributeModifier.Operation.ADD_VALUE);
				if (!_entity.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).hasModifier(modifier.id())) {
					_entity.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).addPermanentModifier(modifier);
				}
			}
		} else {
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).removeModifier(ResourceLocation.parse("better_tools:flint_tools_crit_multiplier"));
			}
		}
	}
}