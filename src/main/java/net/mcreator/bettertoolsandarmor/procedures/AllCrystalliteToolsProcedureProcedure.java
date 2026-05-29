package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import javax.annotation.Nullable;

@EventBusSubscriber
public class AllCrystalliteToolsProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		boolean sword = false;
		boolean pickaxe = false;
		boolean axe = false;
		boolean shovel = false;
		boolean hoe = false;
		boolean dagger = false;
		if (!(entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel
				&& _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().get(ResourceLocation.parse("better_tools:all_crystallite_tools_adv"))).isDone())) {
			if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandlerIter) {
				for (int _idx = 0; _idx < _modHandlerIter.getSlots(); _idx++) {
					ItemStack itemstackiterator = _modHandlerIter.getStackInSlot(_idx).copy();
					if (itemstackiterator.is(ItemTags.create(ResourceLocation.parse("better_tools:upgraded_crystallite_tools")))) {
						if (itemstackiterator.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_swords"))) || itemstackiterator.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_daggers")))) {
							sword = true;
						} else if (itemstackiterator.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_pickaxes")))) {
							pickaxe = true;
						} else if (itemstackiterator.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_axes")))) {
							axe = true;
						} else if (itemstackiterator.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_shovels")))) {
							shovel = true;
						} else if (itemstackiterator.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_hoes")))) {
							hoe = true;
						}
					}
				}
			}
			if (sword && pickaxe && axe && shovel && hoe) {
				if (entity instanceof ServerPlayer _player) {
					AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:all_crystallite_tools_adv"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
		}
	}
}