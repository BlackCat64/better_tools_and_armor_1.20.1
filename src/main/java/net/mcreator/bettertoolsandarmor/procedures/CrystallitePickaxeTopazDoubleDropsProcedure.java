package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

import java.util.Comparator;

@Mod.EventBusSubscriber
public class CrystallitePickaxeTopazDoubleDropsProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		double dupe_chance = 0;
		if (entity instanceof Player) {
			if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_PICKAXE_TOPAZ.get()
						|| ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() > 0) {
					if (!(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0)) {
						if (blockstate.is(BlockTags.create(new ResourceLocation("forge:ores")))) {
							if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_PICKAXE_TOPAZ.get()) {
								dupe_chance = 0.1;
							}
							dupe_chance = dupe_chance + 0.05 * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue();
							if (Math.random() < dupe_chance) {
								BetterToolsMod.queueServerWork(2, () -> {
									if (IsNearestItemEntityNaturallyDroppedProcedure.execute(world, x, y, z)) {
										if (((((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.8), (z + 0.5)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf((x + 0.5), (y + 0.8), (z + 0.5))).findFirst().orElse(null)) instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem() instanceof BlockItem _bi
												? _bi.getBlock().defaultBlockState()
												: Blocks.AIR.defaultBlockState()).getBlock() == Blocks.AIR) {
											if (world instanceof ServerLevel _level) {
												ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5),
														(((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.8), (z + 0.5)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
															Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
															}
														}.compareDistOf((x + 0.5), (y + 0.8), (z + 0.5))).findFirst().orElse(null)) instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY));
												entityToSpawn.setPickUpDelay(5);
												_level.addFreshEntity(entityToSpawn);
											}
											if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_PICKAXE_TOPAZ.get()) {
												if (world instanceof ServerLevel _level)
													_level.sendParticles(ParticleTypes.WAX_ON, (x + 0.5), (y + 0.5), (z + 0.5), 8, 0.5, 0.5, 0.5, 0.1);
											}
											if (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() > 0) {
												if (world instanceof ServerLevel _level)
													_level.sendParticles(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), 8, 0.5, 0.5, 0.5, 0.1);
												if (entity instanceof ServerPlayer _player) {
													Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:luck_adv"));
													AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
													if (!_ap.isDone()) {
														for (String criteria : _ap.getRemainingCriteria())
															_player.getAdvancements().award(_adv, criteria);
													}
												}
											}
											if (world instanceof Level _level) {
												if (!_level.isClientSide()) {
													_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.elder_guardian.curse")), SoundSource.NEUTRAL, (float) 0.3, (float) 1.33);
												} else {
													_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.elder_guardian.curse")), SoundSource.NEUTRAL, (float) 0.3, (float) 1.33, false);
												}
											}
										}
									}
								});
							}
						}
					}
				}
			}
		}
	}
}
