package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteChestplateHoneyProcedureProcedure {
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
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_HONEY_CHESTPLATE.get()) {
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_hurt > 200) {
				if (false) {
					SetEntityNumberDataProcedure.execute(entity, (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 2, "AbsorptionAmount");
				}
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) >= (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
					if ((entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 3 - 1) {
						if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).crystallite_honey_absorption_timer <= 0) {
							SetEntityNumberDataProcedure.execute(entity, (entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) + 2, "AbsorptionAmount");
							{
								double _setval = 200;
								entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.crystallite_honey_absorption_timer = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
						}
					}
				}
			}
		} else if (true) {
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth(
						(float) ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + Math.min(entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0, entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)));
			SetEntityNumberDataProcedure.execute(entity, 0, "AbsorptionAmount");
		}
	}
}
