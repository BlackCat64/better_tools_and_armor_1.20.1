package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

import java.util.Comparator;

@EventBusSubscriber
public class HomingArrowShotProcedure {
	@SubscribeEvent
	public static void onUseItemStop(LivingEntityUseItemEvent.Stop event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getItem(), event.getDuration());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack, double duration) {
		execute(null, world, entity, itemstack, duration);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, ItemStack itemstack, double duration) {
		if (entity == null)
			return;
		double charge_time = 0;
		double radius = 0;
		if ((itemstack.getItem() == Items.BOW || itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_bows"))))
				&& ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:amethyst_upgraded_crystallite_items")))
						|| itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:amethyst_upgraded_crystallite_items"))))) {
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:ruby_upgraded_crystallite_items")))) {
				charge_time = 71990;
			} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:honey_upgraded_crystallite_items")))) {
				charge_time = 80000;
			} else {
				charge_time = 71980;
			}
			if (duration <= charge_time) {
				BetterToolsMod.queueServerWork(1, () -> {
					{
						final Vec3 _center = new Vec3(
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity)).getBlockPos().getX()),
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity)).getBlockPos().getY()),
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity)).getBlockPos().getZ()));
						for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
							if (!(entityiterator == entity) && entityiterator instanceof Arrow && !GetEntityLogicDataProcedure.execute(entityiterator, "inGround")) {
								CrystalliteBowAmethystFiredProcedure.execute(world, entityiterator, entity,
										(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:amethyst_upgraded_crystallite_items")))
												&& itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:amethyst_upgraded_crystallite_items"))) ? 3 : 1.5);
							}
						}
					}
				});
			}
		}
	}
}