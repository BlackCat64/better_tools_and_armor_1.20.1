package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

public class ApplyChestplateEffectProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack chestplate = ItemStack.EMPTY;
		chestplate = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY);
		if (chestplate.is(ItemTags.create(new ResourceLocation("better_tools:magma_chestplates")))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0, true, false));
		} else if (chestplate.is(ItemTags.create(new ResourceLocation("better_tools:hearty_shirts")))) {
			if (HeartyShirtActiveProcedure.execute(entity)) {
				{
					CompoundTag dataIndex = new CompoundTag();
					entity.saveWithoutId(dataIndex);
					dataIndex.putDouble("AbsorptionAmount", (new Object() {
						public double getValue() {
							CompoundTag dataIndex = new CompoundTag();
							entity.saveWithoutId(dataIndex);
							return dataIndex.getDouble("AbsorptionAmount");
						}
					}.getValue() + 1));
					entity.load(dataIndex);
				}
				if (!(entity instanceof ServerPlayer _plr6 && _plr6.level() instanceof ServerLevel
						&& _plr6.getAdvancements().getOrStartProgress(_plr6.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:hearty_adv"))).isDone())) {
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:hearty_adv"));
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
