package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class BarkOnStrippedWoodProcedureProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getLevel().getBlockState(event.getPos()), event.getEntity());
	}

	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		return execute(null, world, x, y, z, blockstate, entity);
	}

	private static boolean execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return false;
		BlockState original_log = Blocks.AIR.defaultBlockState();
		if (blockstate.is(BlockTags.create(ResourceLocation.parse("minecraft:logs"))) && (BuiltInRegistries.BLOCK.getKey(blockstate.getBlock()).toString()).contains(":stripped_")) {
			original_log = BuiltInRegistries.BLOCK.get(ResourceLocation.parse((((BuiltInRegistries.BLOCK.getKey(blockstate.getBlock()).toString()).replace("stripped_", ""))).toLowerCase(java.util.Locale.ENGLISH))).defaultBlockState();
			if (original_log.getBlock() == Blocks.AIR) {
				return false;
			}
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.BARK.get()) {
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
				}
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.BARK.get()
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					(entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).shrink(1);
				}
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.OFF_HAND, true);
			} else {
				return false;
			}
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = original_log;
				BlockState _bso = world.getBlockState(_bp);
				for (Property<?> _propertyOld : _bso.getProperties()) {
					Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
					if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
						try {
							_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
						} catch (Exception e) {
						}
				}
				world.setBlock(_bp, _bs, 3);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.axe.strip")), SoundSource.BLOCKS, 1, (float) 0.75);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.axe.strip")), SoundSource.BLOCKS, 1, (float) 0.75, false);
				}
			}
			if (entity instanceof ServerPlayer _player) {
				AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:bark_on_log_adv"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		return true;
	}
}