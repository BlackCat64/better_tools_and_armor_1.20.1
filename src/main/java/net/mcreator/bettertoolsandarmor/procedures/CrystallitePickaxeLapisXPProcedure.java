package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModBlocks;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystallitePickaxeLapisXPProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState(), event.getPlayer(), event.getExpToDrop());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, double xpAmount) {
		execute(null, world, x, y, z, blockstate, entity, xpAmount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, double xpAmount) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_PICKAXE_LAPIS.get()) {
			if (blockstate.is(BlockTags.create(new ResourceLocation("forge:ores"))) && !(blockstate.getBlock() == BetterToolsModBlocks.END_TITANIUM_ORE.get() || blockstate.getBlock() == Blocks.ANCIENT_DEBRIS)) {
				if (xpAmount == 0) {
					if (world instanceof ServerLevel _level)
						_level.addFreshEntity(new ExperienceOrb(_level, x, y, z, 5));
				} else {
					if (xpAmount <= 500) {
						if (world instanceof ServerLevel _level)
							_level.addFreshEntity(new ExperienceOrb(_level, x, y, z, (int) xpAmount));
					} else {
						if (world instanceof ServerLevel _level)
							_level.addFreshEntity(new ExperienceOrb(_level, x, y, z, 500));
					}
				}
			}
		}
	}
}
