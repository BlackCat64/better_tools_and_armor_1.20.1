package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class LightningStaffProjectileParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity) {
		if (entity == null || immediatesourceentity == null)
			return;
		ItemStack staff = ItemStack.EMPTY;
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.WAX_ON, (immediatesourceentity.getX()), (immediatesourceentity.getY()), (immediatesourceentity.getZ()), 1, 0, 0, 0, 0);
		if (!(entity == null)) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.ELECTRIC_STAFF.get()) {
				staff = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
			} else {
				staff = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
			}
			if (staff.getItem() == BetterToolsModItems.ELECTRIC_STAFF.get() && immediatesourceentity.getPersistentData().getDouble("cooldown_ticks_on_hit") == 0) {
				immediatesourceentity.getPersistentData().putDouble("strikes",
						(staff.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:ensorcellation")))) + 1));
				if (IsInThunderstormProcedure.execute(world, x, y, z, entity)) {
					immediatesourceentity.getPersistentData().putDouble("strikes", (immediatesourceentity.getPersistentData().getDouble("strikes") * 2));
				}
				immediatesourceentity.getPersistentData().putDouble("radius",
						(2.5 + staff.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:ensorcellation")))) * 0.5));
				immediatesourceentity.getPersistentData().putDouble("cooldown_ticks_on_hit",
						(200 - 30 * staff.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:swift_cast"))))));
			}
		}
	}
}