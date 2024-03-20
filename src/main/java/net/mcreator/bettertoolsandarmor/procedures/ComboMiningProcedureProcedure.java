package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ComboMiningProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getState(), event.getPlayer());
	}

	public static void execute(BlockState blockstate, Entity entity) {
		execute(null, blockstate, entity);
	}

	private static void execute(@Nullable Event event, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:combo_mining_tools")))) {
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_mined <= 20) {
				if (blockstate.getBlock() == ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).last_mined_block).getBlock()) {
					{
						double _setval = (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).block_mining_combo + 1;
						entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.block_mining_combo = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					{
						double _setval = 0;
						entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.block_mining_combo = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			} else {
				{
					double _setval = 0;
					entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.block_mining_combo = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		} else {
			{
				double _setval = 0;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.block_mining_combo = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		{
			BlockState _setval = blockstate;
			entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.last_mined_block = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = 0;
			entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.time_since_last_mined = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
