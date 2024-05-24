package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class CrystalliteHelmetAmethystArrowsProcedure {
	@SubscribeEvent
	public static void onUseItemStop(LivingEntityUseItemEvent.Stop event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getItem(), event.getDuration());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack, double duration) {
		execute(null, world, x, y, z, entity, itemstack, duration);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack, double duration) {
		if (entity == null)
			return;
		boolean shot = false;
		double distance = 0;
		double raycast = 0;
		double speed = 0;
		double charge_time = 0;
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:ruby_upgraded_crystallite_items")))) {
			charge_time = 71990;
		} else {
			charge_time = 71980;
		}
		if ((itemstack.getItem() == Items.BOW || itemstack.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_bows"))))
				&& ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_HELMET.get()
						|| itemstack.is(ItemTags.create(new ResourceLocation("better_tools:amethyst_upgraded_crystallite_items"))))) {
			if (duration <= charge_time) {
				raycast = 0;
				for (int index0 = 0; index0 < 7; index0++) {
					if (!shot) {
						raycast = raycast + 5;
						{
							final Vec3 _center = new Vec3(
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raycast)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
											.getX()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raycast)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
											.getY()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raycast)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
											.getZ()));
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (!shot) {
									if (((Entity) world
											.getEntitiesOfClass(LivingEntity.class,
													AABB.ofSize(
															new Vec3(
																	(entity.level()
																			.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raycast)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE,
																					entity))
																			.getBlockPos().getX()),
																	(entity.level()
																			.clip(new ClipContext(
																					entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raycast)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
																			.getBlockPos().getY()),
																	(entity.level().clip(
																			new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raycast)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
																			.getBlockPos().getZ())),
															5, 5, 5),
													e -> true)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
												}
											}.compareDistOf(
													(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raycast)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
															.getBlockPos().getX()),
													(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raycast)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
															.getBlockPos().getY()),
													(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raycast)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
															.getBlockPos().getZ())))
											.findFirst().orElse(null)) == entityiterator) {
										if (!(entityiterator == entity)) {
											distance = Math.sqrt(Math.pow(entityiterator.getX() - entity.getX(), 2) + Math.pow(entityiterator.getY() - entity.getY(), 2) + Math.pow(entityiterator.getZ() - entity.getZ(), 2));
											if (distance > 10) {
												speed = distance / 5;
											} else {
												speed = 2;
											}
											if (distance >= 30) {
												if (entity instanceof ServerPlayer _player) {
													Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:aimbot_adv"));
													AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
													if (!_ap.isDone()) {
														for (String criteria : _ap.getRemainingCriteria())
															_player.getAdvancements().award(_adv, criteria);
													}
												}
											}
											if (event != null && event.isCancelable()) {
												event.setCanceled(true);
											}
											if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemstack) != 0) {
												if (world instanceof ServerLevel projectileLevel) {
													Projectile _entityToSpawn = new Object() {
														public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
															AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
															entityToSpawn.setOwner(shooter);
															entityToSpawn.setBaseDamage(damage);
															entityToSpawn.setKnockback(knockback);
															entityToSpawn.setSecondsOnFire(100);
															entityToSpawn.setCritArrow(true);
															return entityToSpawn;
														}
													}.getArrow(projectileLevel, entity, (float) (1 + itemstack.getEnchantmentLevel(Enchantments.POWER_ARROWS) * 0.5), (int) (1 + itemstack.getEnchantmentLevel(Enchantments.PUNCH_ARROWS) * 0.5));
													_entityToSpawn.setPos(x, (y + 1.5), z);
													_entityToSpawn.shoot(((entityiterator.getX() - entity.getX()) / distance), ((entityiterator.getY() - entity.getY()) / distance), ((entityiterator.getZ() - entity.getZ()) / distance), (float) speed,
															0);
													projectileLevel.addFreshEntity(_entityToSpawn);
												}
											} else {
												if (world instanceof ServerLevel projectileLevel) {
													Projectile _entityToSpawn = new Object() {
														public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
															AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
															entityToSpawn.setOwner(shooter);
															entityToSpawn.setBaseDamage(damage);
															entityToSpawn.setKnockback(knockback);
															entityToSpawn.setCritArrow(true);
															return entityToSpawn;
														}
													}.getArrow(projectileLevel, entity, (float) (1 + itemstack.getEnchantmentLevel(Enchantments.POWER_ARROWS) * 0.5), (int) (1 + itemstack.getEnchantmentLevel(Enchantments.PUNCH_ARROWS) * 0.5));
													_entityToSpawn.setPos(x, (y + 1.5), z);
													_entityToSpawn.shoot(((entityiterator.getX() - entity.getX()) / distance), ((entityiterator.getY() - entity.getY()) / distance), ((entityiterator.getZ() - entity.getZ()) / distance), (float) speed,
															0);
													projectileLevel.addFreshEntity(_entityToSpawn);
												}
											}
											if (entity instanceof Player _player) {
												ItemStack _stktoremove = new ItemStack(Items.ARROW);
												_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
											}
											{
												ItemStack _ist = itemstack;
												if (_ist.hurt(1, RandomSource.create(), null)) {
													_ist.shrink(1);
													_ist.setDamageValue(0);
												}
											}
											if (world instanceof Level _level) {
												if (!_level.isClientSide()) {
													_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1);
												} else {
													_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1, false);
												}
											}
											shot = true;
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
