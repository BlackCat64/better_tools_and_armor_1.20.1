package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class FlintToolsIncreasedCritDamageProcedure {
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
		modifier = new AttributeModifier(UUID.fromString("b142e962-510b-4849-9215-39d451edde4c"), "flint_tools", 0.25, AttributeModifier.Operation.ADDITION);
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:flint_tools")))) {
			if (!(((LivingEntity) entity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).hasModifier(modifier)))
				((LivingEntity) entity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).addTransientModifier(modifier);
		} else {
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).removeModifier(modifier);
		}
	}
}
