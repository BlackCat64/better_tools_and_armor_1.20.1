package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class CrystalliteSwordHoneyProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getSource().getEntity());
		}
	}

	public static void execute(Entity sourceentity) {
		execute(null, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:combo_weapons")))) {
			if ((sourceentity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_attacked <= 40) {
				if (((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.hasModifier((new AttributeModifier(UUID.fromString("741d8785-8249-46c6-b9ed-b50155c23d47"), "crystallite_sword_honey_1", 1, AttributeModifier.Operation.ADDITION)))) {
					((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.removeModifier((new AttributeModifier(UUID.fromString("741d8785-8249-46c6-b9ed-b50155c23d47"), "crystallite_sword_honey_1", 1, AttributeModifier.Operation.ADDITION)));
					if (!(((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.hasModifier((new AttributeModifier(UUID.fromString("0bc17d99-a491-4b8a-a9d2-40d3617d424c"), "crystallite_sword_honey_2", 2, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
								.addTransientModifier((new AttributeModifier(UUID.fromString("0bc17d99-a491-4b8a-a9d2-40d3617d424c"), "crystallite_sword_honey_2", 2, AttributeModifier.Operation.ADDITION)));
				} else if (((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.hasModifier((new AttributeModifier(UUID.fromString("0bc17d99-a491-4b8a-a9d2-40d3617d424c"), "crystallite_sword_honey_2", 2, AttributeModifier.Operation.ADDITION)))) {
					((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.removeModifier((new AttributeModifier(UUID.fromString("741d8785-8249-46c6-b9ed-b50155c23d47"), "crystallite_sword_honey_1", 1, AttributeModifier.Operation.ADDITION)));
					((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.removeModifier((new AttributeModifier(UUID.fromString("0bc17d99-a491-4b8a-a9d2-40d3617d424c"), "crystallite_sword_honey_2", 2, AttributeModifier.Operation.ADDITION)));
					if (!(((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.hasModifier((new AttributeModifier(UUID.fromString("49519c81-a09a-4a23-967d-7f1c65c8564d"), "crystallite_sword_honey_3", 3, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
								.addTransientModifier((new AttributeModifier(UUID.fromString("49519c81-a09a-4a23-967d-7f1c65c8564d"), "crystallite_sword_honey_3", 3, AttributeModifier.Operation.ADDITION)));
				}
			}
			if (!(((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
					.hasModifier((new AttributeModifier(UUID.fromString("741d8785-8249-46c6-b9ed-b50155c23d47"), "crystallite_sword_honey_1", 1, AttributeModifier.Operation.ADDITION)))
					|| ((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.hasModifier((new AttributeModifier(UUID.fromString("0bc17d99-a491-4b8a-a9d2-40d3617d424c"), "crystallite_sword_honey_2", 2, AttributeModifier.Operation.ADDITION)))
					|| ((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.hasModifier((new AttributeModifier(UUID.fromString("49519c81-a09a-4a23-967d-7f1c65c8564d"), "crystallite_sword_honey_3", 3, AttributeModifier.Operation.ADDITION))))) {
				if (!(((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.hasModifier((new AttributeModifier(UUID.fromString("741d8785-8249-46c6-b9ed-b50155c23d47"), "crystallite_sword_honey_1", 1, AttributeModifier.Operation.ADDITION)))))
					((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.addTransientModifier((new AttributeModifier(UUID.fromString("741d8785-8249-46c6-b9ed-b50155c23d47"), "crystallite_sword_honey_1", 1, AttributeModifier.Operation.ADDITION)));
			}
		}
		{
			double _setval = 0;
			sourceentity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.time_since_last_attacked = _setval;
				capability.syncPlayerVariables(sourceentity);
			});
		}
	}
}
