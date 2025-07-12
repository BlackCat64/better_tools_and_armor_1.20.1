package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class EnergyVialExecuteCommandProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		ItemStack vial = ItemStack.EMPTY;
		if (!(entity == null)) {
			if ((StringArgumentType.getString(arguments, "armor")).equals("helmet") || (StringArgumentType.getString(arguments, "armor")).equals("chestplate") || (StringArgumentType.getString(arguments, "armor")).equals("leggings")
					|| (StringArgumentType.getString(arguments, "armor")).equals("boots")) {
				if (PlayerHasEnergyVialEquippedProcedure.execute(entity)) {
					vial = GetEquippedVialProcedure.execute(world, entity);
				} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:energy_vials")))) {
					vial = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
				}
				if (vial.getItem() == ItemStack.EMPTY.getItem()) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7cFound no Energy Vial to update"), false);
				} else {
					vial.getOrCreateTag().putBoolean((StringArgumentType.getString(arguments, "armor") + "_active"), (BoolArgumentType.getBool(arguments, "active")));
					ItemStack[] vial_arr = new ItemStack[]{ItemStack.EMPTY};
					vial_arr[0] = vial;
					Component hoverableVial = ComponentUtils.wrapInSquareBrackets(vial_arr[0].getHoverName()).withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new HoverEvent.ItemStackInfo(vial_arr[0]))));
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("Updated ").append(hoverableVial).append(" successfully"), false);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cBad parameter - Must be 'helmet', 'chestplate', 'leggings' or 'boots'"), false);
			}
		}
	}
}
