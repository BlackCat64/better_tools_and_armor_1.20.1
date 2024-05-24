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
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_bows")))) {
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
									CompoundTag dataIndex7 = new CompoundTag();
									entityiterator.saveWithoutId(dataIndex7);
									return dataIndex7.getBoolean("inGround");
								}
							}.getValue())) {
								if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:honey_upgraded_crystallite_items")))) {
									entityiterator.getPersistentData().putBoolean("crystallite_honey_upgrade", true);
								} else {
									if (duration <= 71990) {
										CompoundTag dataIndex12 = new CompoundTag();
										entityiterator.saveWithoutId(dataIndex12);
										dataIndex12.putDouble("damage", (new Object() {
											public double getValue() {
												CompoundTag dataIndex11 = new CompoundTag();
												entityiterator.saveWithoutId(dataIndex11);
												return dataIndex11.getDouble("damage");
											}
										}.getValue() + 1.5));
										entityiterator.load(dataIndex12);
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
												CompoundTag dataIndex28 = new CompoundTag();
												entityiterator.saveWithoutId(dataIndex28);
												dataIndex28.putDouble("damage", (new Object() {
													public double getValue() {
														CompoundTag dataIndex27 = new CompoundTag();
														entityiterator.saveWithoutId(dataIndex27);
														return dataIndex27.getDouble("damage");
													}
												}.getValue() + 2.5));
												entityiterator.load(dataIndex28);
											}
										} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:netherite_upgraded_crystallite_items")))) {
											CompoundTag dataIndex32 = new CompoundTag();
											entityiterator.saveWithoutId(dataIndex32);
											dataIndex32.putDouble("damage", (new Object() {
												public double getValue() {
													CompoundTag dataIndex31 = new CompoundTag();
													entityiterator.saveWithoutId(dataIndex31);
													return dataIndex31.getDouble("damage");
												}
											}.getValue() + 1.5));
											entityiterator.load(dataIndex32);
										} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:ruby_upgraded_crystallite_items")))) {
											CompoundTag dataIndex36 = new CompoundTag();
											entityiterator.saveWithoutId(dataIndex36);
											dataIndex36.putDouble("damage", (new Object() {
												public double getValue() {
													CompoundTag dataIndex35 = new CompoundTag();
													entityiterator.saveWithoutId(dataIndex35);
													return dataIndex35.getDouble("damage");
												}
											}.getValue() - 2));
											entityiterator.load(dataIndex36);
										} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items")))) {
											entityiterator.getPersistentData().putBoolean("crystallite_sapphire_upgrade", true);
										} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:topaz_upgraded_crystallite_items")))) {
											entityiterator.getPersistentData().putBoolean("crystallite_topaz_upgrade", true);
											entityiterator.getPersistentData().putBoolean("thunder_shot", true);
										} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:nether_diamond_upgraded_crystallite_items")))) {
											entityiterator.getPersistentData().putBoolean("crystallite_nether_diamond_upgrade", true);
										} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sculk_upgraded_crystallite_items")))) {
											CompoundTag dataIndex50 = new CompoundTag();
											entityiterator.saveWithoutId(dataIndex50);
											dataIndex50.putDouble("damage", (new Object() {
												public double getValue() {
													CompoundTag dataIndex49 = new CompoundTag();
													entityiterator.saveWithoutId(dataIndex49);
													return dataIndex49.getDouble("damage");
												}
											}.getValue() + 0.5));
											entityiterator.load(dataIndex50);
											CompoundTag dataIndex51 = new CompoundTag();
											entityiterator.saveWithoutId(dataIndex51);
											dataIndex51.putDouble("PierceLevel", 100);
											entityiterator.load(dataIndex51);
										} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sky_upgraded_crystallite_items")))) {
											entityiterator.setNoGravity(true);
										} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:prismarine_upgraded_crystallite_items")))) {
											entityiterator.getPersistentData().putBoolean("crystallite_prismarine_upgrade", true);
											if (entityiterator.isInWaterRainOrBubble()) {
												CompoundTag dataIndex60 = new CompoundTag();
												entityiterator.saveWithoutId(dataIndex60);
												dataIndex60.putDouble("damage", (new Object() {
													public double getValue() {
														CompoundTag dataIndex59 = new CompoundTag();
														entityiterator.saveWithoutId(dataIndex59);
														return dataIndex59.getDouble("damage");
													}
												}.getValue() + 2.5));
												entityiterator.load(dataIndex60);
											}
										}
									}
								}
							}
						}
						if (!world.isClientSide() && world.getServer() != null)
							world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Set damage of arrow to " + (new Object() {
								public double getValue() {
									CompoundTag dataIndex61 = new CompoundTag();
									entityiterator.saveWithoutId(dataIndex61);
									return dataIndex61.getDouble("damage");
								}
							}.getValue()))), false);
					}
				}
			});
		}
	}
}
