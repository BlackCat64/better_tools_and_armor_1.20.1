package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Display.BlockDisplay;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.CompoundTag;

public class FrozenEffectAppliedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double speed = 0;
		if (false) {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("add world dep"), false);
			}
		}
		entity.clearFire();
		if ((entity instanceof Mob || entity instanceof Player) && !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false) && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
			speed = entity instanceof LivingEntity _livingEntity6 && _livingEntity6.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED) ? _livingEntity6.getAttribute(Attributes.MOVEMENT_SPEED).getValue() : 0;
			if (entity instanceof LivingEntity _entity) {
				AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:frozen_effect"), (speed * (-1)), AttributeModifier.Operation.ADD_VALUE);
				if (!_entity.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(modifier.id())) {
					_entity.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(modifier);
				}
			}
			if (world instanceof ServerLevel level) {
				BlockDisplay display = EntityType.BLOCK_DISPLAY.create(level);
				if (display != null) {
					display.moveTo(x, y, z);
					CompoundTag nbt = new CompoundTag();
					// Define block to display as Ice
					CompoundTag blockState = new CompoundTag();
					blockState.putString("Name", "minecraft:ice");
					nbt.put("block_state", blockState);
					// Transform the block display to be the same size as the frozen entity
					CompoundTag transformation = new CompoundTag();
					transformation.put("left_rotation", floatList(0f, 0f, 0f, 1f));
					transformation.put("right_rotation", floatList(0f, 0f, 0f, 1f));
					transformation.put("scale", floatList((float) (entity.getBbWidth() * 1.25), (float) (entity.getBbHeight() * 1.1), (float) (entity.getBbWidth() * 1.25)));
					transformation.put("translation", floatList((float) (-entity.getBbWidth() * 0.625), 0f, (float) (-entity.getBbWidth() * 0.625)));
					nbt.put("transformation", transformation);
					// Load NBT data into Block Display
					display.load(nbt);
					display.getPersistentData().putBoolean("freeze_effect", true);
					level.addFreshEntity(display);
					entity.getPersistentData().putString("frozen_block_display", (display.getStringUUID()));
				}
			}
			entity.getPersistentData().putDouble("frozen_at_x", x);
			entity.getPersistentData().putDouble("frozen_at_y", y);
			entity.getPersistentData().putDouble("frozen_at_z", z);
		}
	}

	private static ListTag floatList(float... values) {
		ListTag list = new ListTag();
		for (float x : values)
			list.add(FloatTag.valueOf(x));
		return list;
	}
}