package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class ApplyBootsEffectProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack boots = ItemStack.EMPTY;
		boots = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY);
		if (boots.is(ItemTags.create(new ResourceLocation("better_tools:sugar_boots")))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, (int) (boots.is(ItemTags.create(new ResourceLocation("better_tools:diamond_tier_effect_armor"))) ? 2 : 1), true, false));
		} else if (boots.is(ItemTags.create(new ResourceLocation("better_tools:rabbit_boots")))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, (int) (boots.is(ItemTags.create(new ResourceLocation("better_tools:diamond_tier_effect_armor"))) ? 2 : 1), true, false));
		} else if (boots.is(ItemTags.create(new ResourceLocation("better_tools:phantom_boots")))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0, true, false));
		}
	}
}
