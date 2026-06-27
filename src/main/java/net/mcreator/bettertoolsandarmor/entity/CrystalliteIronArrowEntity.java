package net.mcreator.bettertoolsandarmor.entity;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEntities;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class CrystalliteIronArrowEntity extends Arrow implements ItemSupplier {
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(Items.ARROW);
	protected ItemStack firedFromWeapon;

	public CrystalliteIronArrowEntity(EntityType<? extends CrystalliteIronArrowEntity> type, Level level) {
		super(type, level);
	}

	public CrystalliteIronArrowEntity(Level level, LivingEntity shooter) {
		this(BetterToolsModEntities.CRYSTALLITE_IRON_ARROW.get(), level);
		this.setOwner(shooter);
		this.setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
	}

	public CrystalliteIronArrowEntity(Level level, double x, double y, double z) {
		this(BetterToolsModEntities.CRYSTALLITE_IRON_ARROW.get(), level);
		this.setPos(x, y, z);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	public ItemStack getPickupItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	public void setPickupItemStack(ItemStack itemstack) {
		super.setPickupItemStack(itemstack);
	}

	public void setWeaponItem(ItemStack item) {
		this.firedFromWeapon = item;
	}

	@Override
	public ItemStack getWeaponItem() {
		return this.firedFromWeapon;
	}

	@Override
	protected void doKnockback(LivingEntity entity, DamageSource dmgSource) {
		int punchLevel = this.getPunchLevel(entity.level());
		// Add 2 to Punch enchantment level
		double knockback = 2.0 + punchLevel;
		BetterToolsMod.LOGGER.info("" + knockback);
		double resistance = Math.max(0.0, 1.0 - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
		// Hardcode knockback manually
		Vec3 vec = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(knockback * 0.6 * resistance);
		if (vec.lengthSqr() > 0.0) {
			entity.push(vec.x, 0.1, vec.z);
		}
	}

	private int getPunchLevel(Level level) {
		return EnchantmentHelper.getItemEnchantmentLevel(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.PUNCH), this.getWeaponItem());
	}
}