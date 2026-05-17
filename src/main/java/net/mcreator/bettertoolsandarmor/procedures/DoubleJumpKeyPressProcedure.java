package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class DoubleJumpKeyPressProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		boolean boots = false;
		ItemStack vial = ItemStack.EMPTY;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.WINGED_BOOTS_BOOTS.get() && PlayerHasEnergyVialEquippedProcedure.execute(entity)) {
			vial = GetEquippedVialProcedure.execute().copy();
			if (vial.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy") >= 50 && vial.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("boots_active")) {
				boots = true;
			}
		}
		if ((entity instanceof LivingEntity _livEnt4 && _livEnt4.hasEffect(BetterToolsModMobEffects.DOUBLE_JUMP) || boots) && entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).extra_jumps > 0
				&& !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false) && !entity.onGround() && !entity.isInWater() && !(entity instanceof LivingEntity _livEnt8 && _livEnt8.isFallFlying())) {
			entity.setDeltaMovement(
					new Vec3((entity.getDeltaMovement().x()), (0.5 + 0.1 * (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.JUMP) ? _livEnt.getEffect(MobEffects.JUMP).getAmplifier() : 0)), (entity.getDeltaMovement().z())));
			if (!(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_on_ground <= 2 && entity.getY() < entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y)) {
				{
					BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
					_vars.extra_jumps = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).extra_jumps - 1;
					_vars.syncPlayerVariables(entity);
				}
			}
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.time_since_last_jumped = 0;
				_vars.syncPlayerVariables(entity);
			}
			entity.fallDistance = 0;
			if (entity instanceof ServerPlayer _player) {
				AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:double_jump_adv"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
			if (boots) {
				{
					final String _tagName = "energy";
					final double _tagValue = (vial.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy") - 50);
					CustomData.update(DataComponents.CUSTOM_DATA, vial, tag -> tag.putDouble(_tagName, _tagValue));
				}
			}
		}
	}
}
