package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TrackCharmsEquippedProcedure {
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
		double num = 0;
		if (entity instanceof LivingEntity lv) {
			num = CuriosApi.getCuriosInventory(lv).map(inv -> {
				int count = 0;
				for (var handler : inv.getCurios().values()) {
					var stacks = handler.getStacks();
					for (int i = 0; i < stacks.getSlots(); i++) {
						if (!stacks.getStackInSlot(i).isEmpty() && stacks.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("better_tools:crystallite_charms")))) {
							count++;
						}
					}
				}
				return count;
			}).orElse(0);
		}
		{
			double _setval = num;
			entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.charms_equipped = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
