package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

public class SetEntityLogicDataProcedure {
	public static void execute(Entity entity, boolean value, String tagName) {
		if (entity == null || tagName == null)
			return;
		if (false) {
			entity.getPersistentData().putBoolean(tagName, value);
		}
		CompoundTag dataIndex = new CompoundTag();
		entity.saveWithoutId(dataIndex);
		dataIndex.putBoolean(tagName, value);
		entity.load(dataIndex);
	}
}
