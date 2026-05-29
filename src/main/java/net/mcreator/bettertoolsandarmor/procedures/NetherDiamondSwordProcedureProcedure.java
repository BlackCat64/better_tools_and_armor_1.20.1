package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

@EventBusSubscriber
public class NetherDiamondSwordProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getDirectEntity());
		}
	}

	public static void execute(Entity entity, Entity immediatesourceentity) {
		execute(null, entity, immediatesourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity immediatesourceentity) {
		if (entity == null || immediatesourceentity == null)
			return;
		double fire_chance = 0;
		if ((immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:flaming_tools")))) {
			if ((immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:nether_diamond_upgraded_crystallite_items")))) {
				fire_chance = 5;
			} else {
				fire_chance = 3;
			}
			if ((entity.level().dimension()) == Level.NETHER) {
				fire_chance = fire_chance * 2;
			}
			entity.igniteForSeconds((int) fire_chance);
		}
	}
}