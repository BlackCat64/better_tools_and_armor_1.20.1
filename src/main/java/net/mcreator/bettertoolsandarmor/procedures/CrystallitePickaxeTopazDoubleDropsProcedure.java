package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

public class CrystallitePickaxeTopazDoubleDropsProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return false;
		double dupe_chance = 0;
		if (((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:topaz_pickaxes")))
				|| ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() > 0) && blockstate.is(BlockTags.create(new ResourceLocation("forge:ores")))
				&& !(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) && entity instanceof Player
				&& !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:topaz_upgraded_crystallite_items")))) {
				dupe_chance = 0.2;
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:topaz_pickaxes")))) {
				dupe_chance = 0.1;
			}
			dupe_chance = dupe_chance + 0.05 * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue();
			if (Math.random() < dupe_chance) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:topaz_pickaxes")))) {
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
				return true;
			}
		}
		return false;
	}
}
