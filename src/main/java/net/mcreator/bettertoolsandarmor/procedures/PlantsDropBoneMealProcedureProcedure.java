package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class PlantsDropBoneMealProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

private static void execute(
@Nullable Event event,
LevelAccessor world,
double x,
double y,
double z,
BlockState blockstate,
Entity entity
) {
if(
entity == null
) return ;
double chance = 0;
if (&&!(entity instanceof Player _plr ? _plr.getAbilities().instabuild:false)) {if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem():ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_HOE_EMERALD.get()) {chance = 0.1;}else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem():ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:combo_harvesting_tools")))&&(entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new BetterToolsModVariables.PlayerVariables())).block_mining_combo>=9&&blockstate.getBlock() == ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new BetterToolsModVariables.PlayerVariables())).last_mined_block)
.getBlock()) {chance = 0.2;}if (chance>0&&Math.random()<chance) {if (world instanceof ServerLevel _level) {
ItemEntity entityToSpawn = new ItemEntity(_level, (x+0.5), (y+0.5), (z+0.5), new ItemStack(Items.BONE_MEAL));
entityToSpawn.setPickUpDelay(10);
_level.addFreshEntity(entityToSpawn);
}}}
}
}
