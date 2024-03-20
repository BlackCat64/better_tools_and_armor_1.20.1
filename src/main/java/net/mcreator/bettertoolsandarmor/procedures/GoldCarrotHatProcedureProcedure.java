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

public class GoldCarrotHatProcedureProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double cost = 0;
		double time = 0;
		cost = 1;
		if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.NIGHT_VISION) ? _livEnt.getEffect(MobEffects.NIGHT_VISION).getDuration() : 0) < 205) {
			if (itemstack.getItem() == BetterToolsModItems.GOLD_CARROT_HELMET.get()) {
				time = 300;
			} else if (itemstack.getItem() == BetterToolsModItems.IRON_GOLD_CARROT_HELMET.get()) {
				time = 360;
			} else if (itemstack.getItem() == BetterToolsModItems.DIAMOND_GOLD_CARROT_HELMET.get()) {
				time = 500;
				cost = 2;
			}
			if (!(itemstack.getDamageValue() >= itemstack.getMaxDamage() - cost)) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, (int) time, 0, false, false));
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					{
						ItemStack _ist = itemstack;
						if (_ist.hurt((int) cost, RandomSource.create(), null)) {
							_ist.shrink(1);
							_ist.setDamageValue(0);
						}
					}
				}
				if (!(entity instanceof ServerPlayer _plr15 && _plr15.level() instanceof ServerLevel
						&& _plr15.getAdvancements().getOrStartProgress(_plr15.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:night_phantom_adv"))).isDone()) && entity instanceof LivingEntity _livEnt16
						&& _livEnt16.hasEffect(MobEffects.SLOW_FALLING)) {
					if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.PHANTOM_BOOTS.get()
							|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.IRON_PHANTOM_BOOTS.get()
							|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.DIAMOND_PHANTOM_BOOTS.get()) {
						if (entity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:night_phantom_adv"));
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
