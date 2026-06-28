package net.mcreator.bettertoolsandarmor.entity;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEntities;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class CrystallitePrismarineArrowEntity extends Arrow implements ItemSupplier {
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(Items.ARROW);

	//	public CrystallitePrismarineArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
	//		super(BetterToolsModEntities.CRYSTALLITE_PRISMARINE_ARROW.get(), world);
	//	}
	public CrystallitePrismarineArrowEntity(EntityType<? extends CrystallitePrismarineArrowEntity> type, Level level) {
		super(type, level);
	}

	public CrystallitePrismarineArrowEntity(Level level, LivingEntity shooter) {
		this(BetterToolsModEntities.CRYSTALLITE_PRISMARINE_ARROW.get(), level);
		this.setOwner(shooter);
		this.setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
	}

	public CrystallitePrismarineArrowEntity(Level level, double x, double y, double z) {
		this(BetterToolsModEntities.CRYSTALLITE_PRISMARINE_ARROW.get(), level);
		this.setPos(x, y, z);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		if (this.isInWaterRainOrBubble()) {
			this.setBaseDamage(this.getBaseDamage() + 1); // Increase damage by 1 when in water
		}
		if (this.getWeaponItem() != null) {
			int powerLevel = this.getWeaponItem().getEnchantmentLevel(this.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.POWER));
			if (powerLevel > 0) {
				this.setBaseDamage(this.getBaseDamage() + 0.5 + (0.5 * powerLevel));
			}
		}
		super.onHitEntity(result);
	}

	@Override
	protected float getWaterInertia() {
		// Allows arrow to travel fast in water
		return 0.99F;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected ItemStack getPickupItem() {
		return PROJECTILE_ITEM;
	}
}