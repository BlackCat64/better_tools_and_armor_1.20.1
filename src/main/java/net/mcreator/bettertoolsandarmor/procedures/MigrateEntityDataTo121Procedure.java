package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

public class MigrateEntityDataTo121Procedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livingEntity) {
			if (!_livingEntity.getPersistentData().getBoolean("better_tools_migrated")) {
				// Remove legacy attribute modifiers
				for (AttributeInstance attribute : _livingEntity.getAttributes().getSyncableAttributes()) {
					for (AttributeModifier modifier : attribute.getModifiers()) {
						if (modifier.id().getNamespace().equals("better_tools")) {
							attribute.removeModifier(modifier.id()); // Remove all better_tools modifiers the first time this procedure is run for any entity
						} // New modifiers should be re-applied every tick, so this shouldn't cause issues
					}
				}
				CompoundTag persistentData = entity.getPersistentData();

				if (persistentData.contains("ForgeData")) {
					CompoundTag forgeData = persistentData.getCompound("ForgeData");
					CompoundTag neoForgeData = persistentData.getCompound("NeoForgeData");

					// Copy all ForgeData keys into NeoForgeData
					for (String key : forgeData.getAllKeys()) {
						if (!neoForgeData.contains(key)) {
							neoForgeData.put(key, forgeData.get(key).copy());
						}
					}
					persistentData.put("NeoForgeData", neoForgeData);

				}
				//				System.out.println("Migrated " + _livingEntity.getName().getString());
				_livingEntity.getPersistentData().putBoolean("better_tools_migrated", true);
			}
		}
	}
}