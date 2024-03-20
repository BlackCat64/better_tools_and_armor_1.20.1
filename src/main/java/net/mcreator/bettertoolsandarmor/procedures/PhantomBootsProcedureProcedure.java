package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class PhantomBootsProcedureProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double cost = 0;
		double time = 0;
		cost = 1;
		if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(MobEffects.SLOW_FALLING))) {
			if (itemstack.getItem() == BetterToolsModItems.PHANTOM_BOOTS.get()) {
				time = 100;
			} else if (itemstack.getItem() == BetterToolsModItems.IRON_PHANTOM_BOOTS.get()) {
				time = 160;
			} else if (itemstack.getItem() == BetterToolsModItems.DIAMOND_PHANTOM_BOOTS.get()) {
				time = 300;
				cost = 2;
			}
			if (!(itemstack.getDamageValue() >= itemstack.getMaxDamage() - cost)) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) time, 0, false, false));
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					{
						ItemStack _ist = itemstack;
						if (_ist.hurt((int) cost, RandomSource.create(), null)) {
							_ist.shrink(1);
							_ist.setDamageValue(0);
						}
					}
				}
			}
		}
	}
}
