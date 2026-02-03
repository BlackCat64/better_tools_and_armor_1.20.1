package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class KarmaCurseProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, DamageSource damagesource, Entity sourceentity) {
		execute(null, world, damagesource, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource, Entity sourceentity) {
		if (damagesource == null || sourceentity == null)
			return;
		if (!damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:karma_curse_damage")))
				&& EnchantmentHelper.getItemEnchantmentLevel(BetterToolsModEnchantments.KARMA_CURSE.get(), (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) {
			sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:karma_curse_damage")))),
					(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(BetterToolsModEnchantments.KARMA_CURSE.get()));
		}
	}
}
