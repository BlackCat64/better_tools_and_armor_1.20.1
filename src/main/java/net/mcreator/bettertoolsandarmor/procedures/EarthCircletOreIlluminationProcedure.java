package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModParticleTypes;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class EarthCircletOreIlluminationProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean ore_has_air = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double block_x = 0;
		double block_y = 0;
		double block_z = 0;
		double radius = 0;
		if ((HasCuriosItemEquippedProcedure.execute(world, entity, new ItemStack(BetterToolsModItems.EARTH_CIRCLET.get()))
				|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.AMETHYST_HELMET.get()
				|| entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(BetterToolsModMobEffects.ORE_VISION)) && entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_amethyst_ore_highlight_cooldown == 0) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.crystallite_amethyst_ore_highlight_cooldown = Mth.nextInt(RandomSource.create(), 40, 60);
				_vars.markSyncDirty();
			}
			radius = 24;
			sx = radius * (-1);
			for (int index0 = 0; index0 < (int) (radius * 2); index0++) {
				sy = radius * (-1);
				for (int index1 = 0; index1 < (int) (radius * 2); index1++) {
					sz = radius * (-1);
					for (int index2 = 0; index2 < (int) (radius * 2); index2++) {
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BlockTags.create(ResourceLocation.parse("forge:ores")))) {
							block_x = Math.floor(x) + sx + 0.5;
							block_y = Math.floor(y) + sy + 0.5;
							block_z = Math.floor(z) + sz + 0.5;
							for (Direction directioniterator : Direction.values()) {
								if (world.isEmptyBlock(BlockPos.containing(block_x + directioniterator.getStepX(), block_y + directioniterator.getStepY(), block_z + directioniterator.getStepZ()))) {
									for (int index3 = 0; index3 < Mth.nextInt(RandomSource.create(), 1, 3); index3++) {
										world.addParticle((SimpleParticleType) (BetterToolsModParticleTypes.ORE_VISION_PARTICLE.get()), (block_x + (directioniterator.getStepX() == 0 ? Math.random() - 0.5 : directioniterator.getStepX() * 0.6)),
												(block_y + (directioniterator.getStepY() == 0 ? Math.random() - 0.5 : directioniterator.getStepY() * 0.6)),
												(block_z + (directioniterator.getStepZ() == 0 ? Math.random() - 0.5 : directioniterator.getStepZ() * 0.6)), 0, 0, 0);
									}
								}
							}
						}
						sz = sz + 1;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
		}
	}
}