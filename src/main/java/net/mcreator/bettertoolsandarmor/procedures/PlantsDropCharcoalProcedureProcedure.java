package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class PlantsDropCharcoalProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

private static void execute(
@Nullable Event event,
LevelAccessor world,
double x,
double y,
double z,
Entity entity
) {
if(
entity == null
) return ;
double chance = 0;
if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem():ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:plant_charring_tools")))&&&&!(entity instanceof Player _plr ? _plr.getAbilities().instabuild:false)) {if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem():ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:nether_diamond_upgraded_crystallite_items")))) {chance = 0.1;}else{chance = 0.05;}if ((entity.level().dimension()) == Level.NETHER) {chance = chance*2;}if (chance>0&&Math.random()<chance) {if (world instanceof ServerLevel _level) {
ItemEntity entityToSpawn = new ItemEntity(_level, (x+0.5), (y+0.5), (z+0.5), new ItemStack(Items.CHARCOAL));
entityToSpawn.setPickUpDelay(10);
_level.addFreshEntity(entityToSpawn);
}}}
}
}
