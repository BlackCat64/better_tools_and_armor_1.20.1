package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.SimpleContainer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

import java.util.Comparator;

@Mod.EventBusSubscriber
public class SmeltingTouchProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			if (blockstate.is(BlockTags.create(new ResourceLocation("forge:ores")))) {
				if (EnchantmentHelper.getItemEnchantmentLevel(BetterToolsModEnchantments.SMELTING_TOUCH.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:smelting_touch_tools")))) {
					BetterToolsMod.queueServerWork(1, () -> {
						if (IsNearestItemEntityNaturallyDroppedProcedure.execute(world, x, y, z)) {
							{
								ItemStack _setval = (((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.8), (z + 0.5)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf((x + 0.5), (y + 0.8), (z + 0.5))).findFirst().orElse(null)) instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY);
								entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.smelting_touch_item_to_smelt = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							if (world instanceof Level _level9 && _level9.getRecipeManager()
									.getRecipeFor(RecipeType.SMELTING,
											new SimpleContainer(((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).smelting_touch_item_to_smelt)), _level9)
									.isPresent()) {
								{
									ItemStack _setval = (world instanceof Level _lvlSmeltResult
											? _lvlSmeltResult.getRecipeManager()
													.getRecipeFor(RecipeType.SMELTING,
															new SimpleContainer(((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).smelting_touch_item_to_smelt)),
															_lvlSmeltResult)
													.map(recipe -> recipe.getResultItem(_lvlSmeltResult.registryAccess()).copy()).orElse(ItemStack.EMPTY)
											: ItemStack.EMPTY);
									entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.smelting_touch_item_to_drop = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).smelting_touch_item_to_drop)
										.setCount(((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).smelting_touch_item_to_smelt).getCount());
								if (!((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.8), (z + 0.5)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf((x + 0.5), (y + 0.8), (z + 0.5))).findFirst().orElse(null)).level().isClientSide())
									((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.8), (z + 0.5)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
										Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
											return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
										}
									}.compareDistOf((x + 0.5), (y + 0.8), (z + 0.5))).findFirst().orElse(null)).discard();
								if (world instanceof ServerLevel _level) {
									ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5),
											((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).smelting_touch_item_to_drop));
									entityToSpawn.setPickUpDelay(7);
									_level.addFreshEntity(entityToSpawn);
								}
							}
						}
					});
				}
			}
		}
	}
}
