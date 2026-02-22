package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

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
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EarthCircletOreIlluminationProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
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
		if (((entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.EARTH_CIRCLET.get(), lv).isPresent() : false == true)
				|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.AMETHYST_HELMET.get())
				&& (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).crystallite_amethyst_ore_highlight_cooldown == 0) {
			{
				double _setval = Mth.nextInt(RandomSource.create(), 40, 60);
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.crystallite_amethyst_ore_highlight_cooldown = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			sx = -16;
			for (int index0 = 0; index0 < 32; index0++) {
				sy = -16;
				for (int index1 = 0; index1 < 32; index1++) {
					sz = -16;
					for (int index2 = 0; index2 < 32; index2++) {
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BlockTags.create(new ResourceLocation("forge:ores")))) {
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
