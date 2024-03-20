package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteSwordLapisMagicProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, double amount) {
		execute(null, world, entity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity, double amount) {
		if (entity == null || sourceentity == null)
			return;
		if (!BetterToolsModVariables.being_damaged_flag) {
			if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:magic_damage_tools")))) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
				BetterToolsModVariables.being_damaged_flag = true;
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:magic_weapon_damage"))), sourceentity,
						sourceentity), (float) amount);
				BetterToolsModVariables.being_damaged_flag = false;
				if ((sourceentity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).critical_hit) {
					{
						boolean _setval = false;
						sourceentity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.critical_hit = _setval;
							capability.syncPlayerVariables(sourceentity);
						});
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.CRIT, (entity.getX()), (entity.getY() + 1), (entity.getZ()), 10, 0.5, 1, 0.5, 0.05);
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.DAMAGE_INDICATOR, (entity.getX()), (entity.getY() + 1), (entity.getZ()), (int) (amount / 2), 0.4, 0.6, 0.4, 0.025);
				}
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getArmorValue() : 0) > 0) {
					if (sourceentity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:deal_dmg_through_armor_adv"));
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
