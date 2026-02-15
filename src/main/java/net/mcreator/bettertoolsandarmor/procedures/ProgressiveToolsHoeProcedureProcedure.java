package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.common.ToolActions;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ProgressiveToolsHoeProcedureProcedure {
	@SubscribeEvent
	public static void onUseHoe(BlockEvent.BlockToolModificationEvent event) {
		if (!event.isSimulated() && event.getToolAction() == ToolActions.HOE_TILL && event.getPlayer() != null) {
			execute(event, event.getContext().getLevel(), event.getContext().getClickedPos().getX(), event.getContext().getClickedPos().getY(), event.getContext().getClickedPos().getZ(),
					event.getContext().getLevel().getBlockState(event.getContext().getClickedPos()), event.getPlayer());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		double threshold_2 = 0;
		double threshold_1 = 0;
		String reg_name = "";
		ItemStack new_pickaxe = ItemStack.EMPTY;
		ItemStack hoe = ItemStack.EMPTY;
		boolean off_hand = false;
		if (blockstate.is(BlockTags.create(new ResourceLocation("better_tools:hoe_allowed_blocks"))) && world.isEmptyBlock(BlockPos.containing(x, y + 1, z))) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:progressive_tools")))
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("minecraft:hoes")))) {
				hoe = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:progressive_tools")))
					&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("minecraft:hoes"))))) {
				hoe = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
				off_hand = true;
			}
			if (!(hoe.getItem() == ItemStack.EMPTY.getItem())) {
				reg_name = ((ForgeRegistries.ITEMS.getKey(hoe.getItem()).toString()).replace("_upgrade_2", "")).replace("_upgrade_1", "");
				threshold_1 = 1000;
				threshold_2 = 3000;
				hoe.getOrCreateTag().putDouble("blocks_mined", (hoe.getOrCreateTag().getDouble("blocks_mined") + 1));
				if (!(ForgeRegistries.ITEMS.getKey(hoe.getItem()).toString()).endsWith("_upgrade_2") && hoe.getOrCreateTag().getDouble("blocks_mined") >= threshold_2) {
					new_pickaxe = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(((reg_name + "_upgrade_2")).toLowerCase(java.util.Locale.ENGLISH))));
					{
						Entity _ent = entity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("title @s actionbar \"\u00A7c" + "" + hoe.getDisplayName().getString() + " upgraded to Max Level\""));
						}
					}
				} else if (!(ForgeRegistries.ITEMS.getKey(hoe.getItem()).toString()).contains("_upgrade_") && hoe.getOrCreateTag().getDouble("blocks_mined") >= threshold_1) {
					new_pickaxe = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(((reg_name + "_upgrade_1")).toLowerCase(java.util.Locale.ENGLISH))));
					{
						Entity _ent = entity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("title @s actionbar \"\u00A7c" + "" + hoe.getDisplayName().getString() + " upgraded to Level 1\""));
						}
					}
				}
				if (new_pickaxe.is(ItemTags.create(new ResourceLocation("better_tools:progressive_tools")))) {
					{
						CompoundTag _nbtTag = hoe.getTag();
						if (_nbtTag != null)
							new_pickaxe.setTag(_nbtTag.copy());
					}
					if (off_hand) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new_pickaxe.copy();
							_setstack.setCount(1);
							_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
					} else {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new_pickaxe.copy();
							_setstack.setCount(1);
							_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
					}
				}
			}
		}
	}
}
