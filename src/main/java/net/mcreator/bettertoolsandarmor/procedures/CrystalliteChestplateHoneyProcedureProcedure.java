package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteChestplateHoneyProcedureProcedure {
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
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_HONEY_CHESTPLATE.get()) {
			if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_hurt > 200) {
				if (false) {
					SetEntityNumberDataProcedure.execute(entity, (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 2, "AbsorptionAmount");
				}
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) >= (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
					if ((entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 3 - 1) {
						if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_honey_absorption_timer <= 0) {
							SetEntityNumberDataProcedure.execute(entity, (entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) + 2, "AbsorptionAmount");
							{
								BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
								_vars.crystallite_honey_absorption_timer = 200;
								_vars.syncPlayerVariables(entity);
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
