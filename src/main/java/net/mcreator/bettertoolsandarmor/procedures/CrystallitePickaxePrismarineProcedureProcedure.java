package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystallitePickaxePrismarineProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreaking(PlayerEvent.BreakSpeed event) {
		if (event.getPosition().isEmpty())
			return;
		execute(event, event.getState(), event.getEntity(), event.getNewSpeed());
	}

	public static void execute(BlockState blockstate, Entity entity, double breakSpeed) {
		execute(null, blockstate, entity, breakSpeed);
	}

	private static void execute(@Nullable Event event, BlockState blockstate, Entity entity, double breakSpeed) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:water_efficient_tools"))) && entity.isInWaterRainOrBubble()) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:shovels")))
					&& blockstate.is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/shovel")))) {
				if (event instanceof PlayerEvent.BreakSpeed _speed7)
					_speed7.setNewSpeed((float) (breakSpeed * 20));
			} else {
				if (event instanceof PlayerEvent.BreakSpeed _speed8)
					_speed8.setNewSpeed((float) (breakSpeed * 1.2));
			}
		}
	}
}