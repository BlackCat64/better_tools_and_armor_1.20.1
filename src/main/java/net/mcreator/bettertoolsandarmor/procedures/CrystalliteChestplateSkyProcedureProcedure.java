package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteChestplateSkyProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getDirectEntity());
		}
	}

	public static void execute(Entity entity, Entity immediatesourceentity) {
		execute(null, entity, immediatesourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity immediatesourceentity) {
		if (entity == null || immediatesourceentity == null)
			return;
		double knockback_power = 0;
		double distance = 0;
		double x_diff = 0;
		double z_diff = 0;
		double x_velocity = 0;
		double y_velocity = 0;
		double z_velocity = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SKY_CHESTPLATE.get()) {
			if (immediatesourceentity instanceof LivingEntity) {
				knockback_power = Math.max(0.75 * (1 - ((LivingEntity) immediatesourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE).getValue()), 0);
				x_diff = immediatesourceentity.getX() - entity.getX();
				z_diff = immediatesourceentity.getZ() - entity.getZ();
				distance = Math.sqrt(Math.pow(x_diff, 2) + Math.pow(z_diff, 2));
				x_velocity = (x_diff / distance) * knockback_power;
				y_velocity = 0.5 * knockback_power;
				z_velocity = (z_diff / distance) * knockback_power;
				if (knockback_power > 0) {
					immediatesourceentity.setDeltaMovement(new Vec3(x_velocity, y_velocity, z_velocity));
				}
			}
		}
	}
}
