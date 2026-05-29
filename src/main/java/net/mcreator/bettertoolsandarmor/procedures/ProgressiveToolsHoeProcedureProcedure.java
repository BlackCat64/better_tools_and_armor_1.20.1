package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@EventBusSubscriber
public class ProgressiveToolsHoeProcedureProcedure {
	@SubscribeEvent
	public static void onUseHoe(BlockEvent.BlockToolModificationEvent event) {
		if (!event.isSimulated() && event.getItemAbility() == ItemAbilities.HOE_TILL && event.getPlayer() != null) {
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
		if (blockstate.is(BlockTags.create(ResourceLocation.parse("better_tools:hoe_allowed_blocks"))) && world.isEmptyBlock(BlockPos.containing(x, y + 1, z))) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools")))
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:hoes")))) {
				hoe = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools")))
					&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:hoes"))))) {
				hoe = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
				off_hand = true;
			}
			if (!(hoe.getItem() == ItemStack.EMPTY.getItem())) {
				reg_name = ((BuiltInRegistries.ITEM.getKey(hoe.getItem()).toString()).replace("_upgrade_2", "")).replace("_upgrade_1", "");
				threshold_1 = 1000;
				threshold_2 = 3000;
				{
					final String _tagName = "blocks_mined";
					final double _tagValue = (hoe.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") + 1);
					CustomData.update(DataComponents.CUSTOM_DATA, hoe, tag -> tag.putDouble(_tagName, _tagValue));
				}
				if (!(BuiltInRegistries.ITEM.getKey(hoe.getItem()).toString()).endsWith("_upgrade_2") && hoe.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") >= threshold_2) {
					new_pickaxe = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse(((reg_name + "_upgrade_2")).toLowerCase(java.util.Locale.ENGLISH)))).copy();
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7c" + hoe.getDisplayName().getString() + " upgraded to Max Level")), true);
				} else if (!(BuiltInRegistries.ITEM.getKey(hoe.getItem()).toString()).contains("_upgrade_") && hoe.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") >= threshold_1) {
					new_pickaxe = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse(((reg_name + "_upgrade_1")).toLowerCase(java.util.Locale.ENGLISH)))).copy();
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7c" + hoe.getDisplayName().getString() + " upgraded to Level 1")), true);
				}
				if (new_pickaxe.is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools")))) {
					new_pickaxe.applyComponents(hoe.getComponents());
					if (off_hand) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack30 = new_pickaxe.copy();
							_setstack30.setCount(1);
							_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack30);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
					} else {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack31 = new_pickaxe.copy();
							_setstack31.setCount(1);
							_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack31);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
					}
				}
			}
		}
	}
}