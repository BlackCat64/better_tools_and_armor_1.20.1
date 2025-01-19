package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class FreezingWeaponsSetAttributesProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double chance = 0;
		double time = 0;
		if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.ATTACKFREEZECHANCE.get()) != null) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:freezing_tools")))) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items")))) {
					chance = 0.2;
					time = 200;
				} else {
					chance = 0.1;
					time = 100;
				}
			}
			if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.ICY_BRACELET.get(), lv).isPresent() : false) {
				chance = chance + 0.1;
			}
			if (world.getBiome(BlockPos.containing(x, y, z)).value().getBaseTemperature() * 100f <= 0.15) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items")))) {
					chance = chance * 2;
					time = time * 1.5;
				} else {
					chance = chance * 2;
					time = time * 2;
				}
			}
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.ATTACKFREEZECHANCE.get()).removeModifier((new AttributeModifier(UUID.fromString("f84aa605-971d-4d66-b38b-c669ec0138b7"), "", 0, AttributeModifier.Operation.ADDITION)));
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.ATTACKFREEZETIME.get()).removeModifier((new AttributeModifier(UUID.fromString("4fb08f53-c1cb-4b83-8d3f-585863b99f2b"), "", 0, AttributeModifier.Operation.ADDITION)));
			if (chance > 0) {
				if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK) != null) {
					chance = chance + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
				}
				((LivingEntity) entity).getAttribute(BetterToolsModAttributes.ATTACKFREEZECHANCE.get())
						.addTransientModifier((new AttributeModifier(UUID.fromString("f84aa605-971d-4d66-b38b-c669ec0138b7"), "freezing_weapons", chance, AttributeModifier.Operation.ADDITION)));
				((LivingEntity) entity).getAttribute(BetterToolsModAttributes.ATTACKFREEZETIME.get())
						.addTransientModifier((new AttributeModifier(UUID.fromString("4fb08f53-c1cb-4b83-8d3f-585863b99f2b"), "freezing_weapons", time, AttributeModifier.Operation.ADDITION)));
			}
		}
	}
}
