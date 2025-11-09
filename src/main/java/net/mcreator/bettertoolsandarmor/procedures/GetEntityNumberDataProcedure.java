package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

public class GetEntityNumberDataProcedure {
	public static double execute(Entity entity, String tagName) {
		if (entity == null || tagName == null)
			return 0;
		double value = 0;
		if (false) {
			entity.getPersistentData().putDouble(tagName, 0);
		}
		CompoundTag dataIndex = new CompoundTag();
		entity.saveWithoutId(dataIndex);
		value = dataIndex.getDouble(tagName);
		return value;
	}
}
