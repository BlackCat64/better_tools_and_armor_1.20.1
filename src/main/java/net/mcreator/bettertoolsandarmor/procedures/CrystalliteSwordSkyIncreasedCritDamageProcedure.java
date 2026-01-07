package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class CrystalliteSwordSkyIncreasedCritDamageProcedure {
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
		AttributeModifier modifier = null;
		modifier = new AttributeModifier(UUID.fromString("1bdaa54a-b347-4111-85e1-2e2bc6528571"), "increased_crit_damage", 0.5, AttributeModifier.Operation.ADDITION);
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:increased_crit_multiplier_weapons")))) {
			if (!(((LivingEntity) entity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).hasModifier(modifier)))
				((LivingEntity) entity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).addTransientModifier(modifier);
		} else {
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).removeModifier(modifier);
		}
	}
}
