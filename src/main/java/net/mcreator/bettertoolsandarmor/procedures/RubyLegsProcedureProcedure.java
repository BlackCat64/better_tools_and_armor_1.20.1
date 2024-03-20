package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class RubyLegsProcedureProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double cost = 0;
		double time = 0;
		if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(MobEffects.DIG_SPEED))) {
			if (itemstack.getItem() == BetterToolsModItems.RUBY_LEGGINGS.get()) {
				time = 100;
				cost = 1;
			} else if (itemstack.getItem() == BetterToolsModItems.DIAMOND_RUBY_LEGGINGS.get()) {
				time = 160;
				cost = 2;
			}
			if (!(itemstack.getDamageValue() >= itemstack.getMaxDamage() - cost)) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, (int) time, (int) (cost - 1), false, false));
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					{
						ItemStack _ist = itemstack;
						if (_ist.hurt((int) cost, RandomSource.create(), null)) {
							_ist.shrink(1);
							_ist.setDamageValue(0);
						}
					}
				}
				if (!(entity instanceof ServerPlayer _plr13 && _plr13.level() instanceof ServerLevel
						&& _plr13.getAdvancements().getOrStartProgress(_plr13.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:ruby_sugar_adv"))).isDone()) && entity instanceof LivingEntity _livEnt14
						&& _livEnt14.hasEffect(MobEffects.MOVEMENT_SPEED)) {
					if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.SUGAR_BOOTS.get()
							|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.IRON_SUGAR_BOOTS.get()
							|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.DIAMOND_SUGAR_BOOTS.get()) {
						if (entity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:ruby_sugar_adv"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
				}
			}
		}
	}
}
