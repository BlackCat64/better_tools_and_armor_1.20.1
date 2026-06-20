package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystallitePickaxeSkyAirHasteProcedure {
	@SubscribeEvent
	public static void onBlockBreaking(PlayerEvent.BreakSpeed event) {
		if (event.getPosition().isEmpty())
			return;
		execute(event, event.getEntity(), event.getNewSpeed());
	}

	public static void execute(Entity entity, double breakSpeed) {
		execute(null, entity, breakSpeed);
	}

	private static void execute(@Nullable Event event, Entity entity, double breakSpeed) {
		if (entity == null)
			return;
		if (!entity.onGround() && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:air_affinity_tools")))) {
			if (event instanceof PlayerEvent.BreakSpeed _speed3)
				_speed3.setNewSpeed((float) (breakSpeed * 5));
		}
	}
}