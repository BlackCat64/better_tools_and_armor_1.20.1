package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
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

import javax.annotation.Nullable;

@EventBusSubscriber
public class ProgressiveToolsProcedureProcedure {
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
		ItemStack new_pickaxe = ItemStack.EMPTY;
		String reg_name = "";
		double threshold_1 = 0;
		double threshold_2 = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools")))
				&& ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:pickaxes")))
						&& blockstate.is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/pickaxe")))
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
								&& blockstate.is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/axe")))
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:shovels")))
								&& blockstate.is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/shovel")))
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:hoes")))
								&& blockstate.is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/hoe"))))) {
			reg_name = ((BuiltInRegistries.ITEM.getKey((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()).toString()).replace("_upgrade_2", "")).replace("_upgrade_1", "");
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:pickaxes")))) {
				threshold_1 = 5000;
				threshold_2 = 20000;
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))) {
				threshold_1 = 1000;
				threshold_2 = 4000;
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:shovels")))) {
				threshold_1 = 10000;
				threshold_2 = 30000;
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:hoes")))) {
				threshold_1 = 1000;
				threshold_2 = 3000;
			}
			{
				final String _tagName = "blocks_mined";
				final double _tagValue = ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") + 1);
				CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY), tag -> tag.putDouble(_tagName, _tagValue));
			}
			if (!((BuiltInRegistries.ITEM.getKey((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()).toString()).endsWith("_upgrade_2"))
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") >= threshold_2) {
				new_pickaxe = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse(((reg_name + "_upgrade_2")).toLowerCase(java.util.Locale.ENGLISH)))).copy();
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7c" + ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getDisplayName().getString()) + " upgraded to Max Level")), true);
			} else if (!((BuiltInRegistries.ITEM.getKey((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()).toString()).contains("_upgrade_"))
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") >= threshold_1) {
				new_pickaxe = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse(((reg_name + "_upgrade_1")).toLowerCase(java.util.Locale.ENGLISH)))).copy();
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7c" + ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getDisplayName().getString()) + " upgraded to Level 1")), true);
			}
			if (new_pickaxe.is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools")))) {
				new_pickaxe.applyComponents((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getComponents());
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack51 = new_pickaxe.copy();
					_setstack51.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack51);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
		}
	}
}