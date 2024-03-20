package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class ComboWeaponsResetProcedure {
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
		{
			double _setval = (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_attacked + 1;
			entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.time_since_last_attacked = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_attacked > 40) {
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
					.removeModifier((new AttributeModifier(UUID.fromString("741d8785-8249-46c6-b9ed-b50155c23d47"), "crystallite_sword_honey_1", 1, AttributeModifier.Operation.ADDITION)));
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
					.removeModifier((new AttributeModifier(UUID.fromString("0bc17d99-a491-4b8a-a9d2-40d3617d424c"), "crystallite_sword_honey_2", 2, AttributeModifier.Operation.ADDITION)));
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
					.removeModifier((new AttributeModifier(UUID.fromString("49519c81-a09a-4a23-967d-7f1c65c8564d"), "crystallite_sword_honey_3", 3, AttributeModifier.Operation.ADDITION)));
		}
		{
			double _setval = (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_mined + 1;
			entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.time_since_last_mined = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_mined > 20) {
			{
				double _setval = 0;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.block_mining_combo = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		{
			double _setval = (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_jumped + 1;
			entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.time_since_last_jumped = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
