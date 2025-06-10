package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

public class ApplyLeggingsEffectProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack leggings = ItemStack.EMPTY;
		leggings = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY);
		if (leggings.is(ItemTags.create(new ResourceLocation("better_tools:ruby_leggings")))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200, (int) (leggings.is(ItemTags.create(new ResourceLocation("better_tools:diamond_tier_effect_armor"))) ? 1 : 0), true, false));
		} else if (leggings.is(ItemTags.create(new ResourceLocation("better_tools:crystal_leggings")))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(BetterToolsModMobEffects.SWIFT_SWIM.get(), 200, (int) (leggings.is(ItemTags.create(new ResourceLocation("better_tools:diamond_tier_effect_armor"))) ? 2 : 1), true, false));
		} else if (leggings.is(ItemTags.create(new ResourceLocation("better_tools:gilded_blackstone_leggings")))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0, true, false));
		}
	}
}
