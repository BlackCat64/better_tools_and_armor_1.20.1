package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class NetherDiamondToolsPlantsTooltipProcedure {
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
		double chance = 0;
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:plant_charring_tools")))) {
			if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:nether_diamond_upgraded_crystallite_items")))) {
				chance = 0.1;
			} else {
				chance = 0.05;
			}
			if ((entity.level().dimension()) == Level.NETHER) {
				chance = chance * 2;
			}
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("#").format(chance * 100) + "% Charcoal Chance")));
		}
	}
}
