package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber
public class AllCrystalliteToolsProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
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
				&& _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:all_crystallite_tools_adv"))).isDone())) {
			{
				AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
				entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
				if (_iitemhandlerref.get() != null) {
					for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
						ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
						if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:upgraded_crystallite_tools")))) {
							if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_swords"))) || itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_daggers")))) {
								sword = true;
							} else if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_pickaxes")))) {
								pickaxe = true;
							} else if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_axes")))) {
								axe = true;
							} else if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_shovels")))) {
								shovel = true;
							} else if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_hoes")))) {
								hoe = true;
							}
						}
					}
				}
			}
			if (sword && pickaxe && axe && shovel && hoe) {
				if (entity instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:all_crystallite_tools_adv"));
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
