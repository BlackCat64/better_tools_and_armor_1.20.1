package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystallitePickaxeSculkProcedureProcedure {
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
		double x_offset = 0;
		double y_offset = 0;
		double z_offset = 0;
		double count = 0;
		if (blockstate.is(BlockTags.create(new ResourceLocation("forge:ores"))) && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:vein_mining_pickaxes")))
				&& !((entity instanceof Player _plr ? _plr.getAbilities().instabuild : false) || entity.isShiftKeyDown())) {
			x_offset = -1;
			for (int index0 = 0; index0 < 3; index0++) {
				y_offset = -1;
				for (int index1 = 0; index1 < 3; index1++) {
					z_offset = -1;
					for (int index2 = 0; index2 < 3; index2++) {
						if (!(x_offset == 0 && y_offset == 0 && z_offset == 0) && (world.getBlockState(BlockPos.containing(x + x_offset, y + y_offset, z + z_offset))).getBlock() == blockstate.getBlock()) {
							BreakBlockWithPickaxeProcedure.execute(world, x + x_offset, y + y_offset, z + z_offset, blockstate, entity);
							count = count + 1;
						}
						z_offset = z_offset + 1;
					}
					y_offset = y_offset + 1;
				}
				x_offset = x_offset + 1;
			}
			if (count >= 10) {
				if (entity instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:vein_mining_adv"));
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
	}
}
