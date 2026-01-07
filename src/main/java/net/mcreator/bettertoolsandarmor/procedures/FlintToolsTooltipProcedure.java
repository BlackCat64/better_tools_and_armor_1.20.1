package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class FlintToolsTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:flint_tools"))) && !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem())) {
			tooltip.add(Component.literal("\u00A79+0.25x Critical Hit Multiplier"));
		}
	}
}
