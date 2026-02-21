
package net.mcreator.bettertoolsandarmor.entity;

import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEntities;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class CrystallitePrismarineArrowEntity extends Arrow implements ItemSupplier {
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(Items.ARROW);

	public CrystallitePrismarineArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(BetterToolsModEntities.CRYSTALLITE_PRISMARINE_ARROW.get(), world);
	}

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
		super.onHitEntity(result);
	}

	@Override
	protected float getWaterInertia() {
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
