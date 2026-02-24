package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteSwordDiamondProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource(), event.getEntity(), event.getSource().getDirectEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, DamageSource damagesource, Entity entity, Entity immediatesourceentity, double amount) {
		execute(null, world, damagesource, entity, immediatesourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource, Entity entity, Entity immediatesourceentity, double amount) {
		if (damagesource == null || entity == null || immediatesourceentity == null)
			return;
		double damage = 0;
		double boost = 0;
		if ((immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:precision_weapons")))
				&& !damagesource.is(TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:damage_overrides")))
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) == (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
			if (event != null && event.isCancelable()) {
				event.setCanceled(true);
			} else if (event != null && event.hasResult()) {
				event.setResult(Event.Result.DENY);
			}
			damage = amount;
			boost = (immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:crystallite_axes"))) ? 2 : 2.5;
			if (entity instanceof LivingEntity && ((LivingEntity) immediatesourceentity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()) != null
					&& (immediatesourceentity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).critical_hit) {
				if (((LivingEntity) immediatesourceentity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).getValue() != 1.5) {
					damage = (damage + boost) * ((LivingEntity) immediatesourceentity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).getValue();
				} else {
					damage = damage + 1.5 * boost;
				}
			} else {
				damage = damage + boost;
			}
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:override_weapon_damage"))), immediatesourceentity,
					immediatesourceentity), (float) damage);
			if (!(immediatesourceentity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
				{
					ItemStack _ist = (immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
					if (_ist.hurt(1, RandomSource.create(), null)) {
						_ist.shrink(1);
						_ist.setDamageValue(0);
					}
				}
			}
			if ((immediatesourceentity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).critical_hit) {
				CriticalHitParticlesProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), damage);
			}
		}
	}
}
