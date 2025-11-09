package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

public class GetEntityLogicDataProcedure {
	public static boolean execute(Entity entity, String tagName) {
		if (entity == null || tagName == null)
			return false;
		boolean value = false;
		if (false) {
			entity.getPersistentData().putDouble(tagName, 0);
		}
		CompoundTag dataIndex = new CompoundTag();
		entity.saveWithoutId(dataIndex);
		value = dataIndex.getBoolean(tagName);
		return value;
	}
}
