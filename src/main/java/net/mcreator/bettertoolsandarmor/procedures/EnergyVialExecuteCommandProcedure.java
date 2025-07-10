package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class EnergyVialExecuteCommandProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		if (!(entity == null)) {
			if (PlayerHasEnergyVialEquippedProcedure.execute(entity)) {
				GetEquippedVialProcedure.execute(world, entity).getOrCreateTag().putBoolean((StringArgumentType.getString(arguments, "armor") + "_active"), (BoolArgumentType.getBool(arguments, "active")));
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:energy_vials")))) {
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putBoolean((StringArgumentType.getString(arguments, "armor") + "_active"), (BoolArgumentType.getBool(arguments, "active")));
			}
		}
	}
}
