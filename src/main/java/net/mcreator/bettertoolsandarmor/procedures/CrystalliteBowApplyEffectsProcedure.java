package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class CrystalliteBowApplyEffectsProcedure {
	@SubscribeEvent
	public static void onUseItemStop(LivingEntityUseItemEvent.Stop event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getItem(), event.getDuration());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack, double duration) {
		execute(null, world, entity, itemstack, duration);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, ItemStack itemstack, double duration) {
		if (entity == null)
			return;
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_bows"))) && !itemstack.is(ItemTags.create(new ResourceLocation("better_tools:honey_upgraded_crystallite_items")))) {
			if (duration <= 71990) {
				BetterToolsMod.queueServerWork(1, () -> {
					{
						final Vec3 _center = new Vec3(
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity)).getBlockPos().getX()),
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity)).getBlockPos().getY()),
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity)).getBlockPos().getZ()));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (!(entityiterator == entity) && entityiterator instanceof Arrow) {
								if (!(new Object() {
									public boolean getValue() {
										CompoundTag dataIndex9 = new CompoundTag();
										entityiterator.saveWithoutId(dataIndex9);
										return dataIndex9.getBoolean("inGround");
									}
								}.getValue())) {
									CompoundTag dataIndex11 = new CompoundTag();
									entityiterator.saveWithoutId(dataIndex11);
									dataIndex11.putDouble("damage", (new Object() {
										public double getValue() {
											CompoundTag dataIndex10 = new CompoundTag();
											entityiterator.saveWithoutId(dataIndex10);
											return dataIndex10.getDouble("damage");
										}
									}.getValue() + 1.5));
									entityiterator.load(dataIndex11);
									if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:iron_upgraded_crystallite_items")))) {
										entityiterator.getPersistentData().putBoolean("crystallite_iron_upgrade", true);
										((AbstractArrow) entityiterator).setKnockback(4);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:gold_upgraded_crystallite_items")))) {
										entityiterator.getPersistentData().putBoolean("crystallite_gold_upgrade", true);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:lapis_upgraded_crystallite_items")))) {
										entityiterator.getPersistentData().putBoolean("crystallite_lapis_upgrade", true);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:redstone_upgraded_crystallite_items")))) {
										entityiterator.getPersistentData().putBoolean("crystallite_redstone_upgrade", true);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:diamond_upgraded_crystallite_items")))) {
										if (Math.random() < 0.25) {
											CompoundTag dataIndex27 = new CompoundTag();
											entityiterator.saveWithoutId(dataIndex27);
											dataIndex27.putDouble("damage", (new Object() {
												public double getValue() {
													CompoundTag dataIndex26 = new CompoundTag();
													entityiterator.saveWithoutId(dataIndex26);
													return dataIndex26.getDouble("damage");
												}
											}.getValue() + 2.5));
											entityiterator.load(dataIndex27);
										}
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:netherite_upgraded_crystallite_items")))) {
										CompoundTag dataIndex31 = new CompoundTag();
										entityiterator.saveWithoutId(dataIndex31);
										dataIndex31.putDouble("damage", (new Object() {
											public double getValue() {
												CompoundTag dataIndex30 = new CompoundTag();
												entityiterator.saveWithoutId(dataIndex30);
												return dataIndex30.getDouble("damage");
											}
										}.getValue() + 1.5));
										entityiterator.load(dataIndex31);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:ruby_upgraded_crystallite_items")))) {
										CompoundTag dataIndex35 = new CompoundTag();
										entityiterator.saveWithoutId(dataIndex35);
										dataIndex35.putDouble("damage", (new Object() {
											public double getValue() {
												CompoundTag dataIndex34 = new CompoundTag();
												entityiterator.saveWithoutId(dataIndex34);
												return dataIndex34.getDouble("damage");
											}
										}.getValue() - 2));
										entityiterator.load(dataIndex35);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items")))) {
										entityiterator.getPersistentData().putBoolean("crystallite_sapphire_upgrade", true);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:topaz_upgraded_crystallite_items")))) {
										entityiterator.getPersistentData().putBoolean("crystallite_topaz_upgrade", true);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:nether_diamond_upgraded_crystallite_items")))) {
										entityiterator.getPersistentData().putBoolean("crystallite_nether_diamond_upgrade", true);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:emerald_upgraded_crystallite_items")))) {
										entityiterator.getPersistentData().putBoolean("crystallite_emerald_upgrade", true);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sculk_upgraded_crystallite_items")))) {
										CompoundTag dataIndex51 = new CompoundTag();
										entityiterator.saveWithoutId(dataIndex51);
										dataIndex51.putDouble("damage", (new Object() {
											public double getValue() {
												CompoundTag dataIndex50 = new CompoundTag();
												entityiterator.saveWithoutId(dataIndex50);
												return dataIndex50.getDouble("damage");
											}
										}.getValue() + 0.5));
										entityiterator.load(dataIndex51);
										CompoundTag dataIndex52 = new CompoundTag();
										entityiterator.saveWithoutId(dataIndex52);
										dataIndex52.putDouble("PierceLevel", 100);
										entityiterator.load(dataIndex52);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sky_upgraded_crystallite_items")))) {
										entityiterator.setNoGravity(true);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:amethyst_upgraded_crystallite_items")))) {
										entityiterator.getPersistentData().putBoolean("crystallite_amethyst_upgrade", true);
									} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:prismarine_upgraded_crystallite_items")))) {
										entityiterator.getPersistentData().putBoolean("crystallite_prismarine_upgrade", true);
									}
								}
							}
							if (!world.isClientSide() && world.getServer() != null)
								world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Set damage of arrow to " + (new Object() {
									public double getValue() {
										CompoundTag dataIndex62 = new CompoundTag();
										entityiterator.saveWithoutId(dataIndex62);
										return dataIndex62.getDouble("damage");
									}
								}.getValue()))), false);
						}
					}
				});
			}
		}
	}
}
