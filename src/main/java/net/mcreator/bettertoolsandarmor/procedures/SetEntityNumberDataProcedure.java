package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

public class SetEntityNumberDataProcedure {
	public static void execute(Entity entity, double value, String tagName) {
		if (entity == null || tagName == null)
			return;
		if (false) {
			entity.getPersistentData().putDouble(tagName, value);
		}
		CompoundTag dataIndex = new CompoundTag();
		entity.saveWithoutId(dataIndex);
		dataIndex.putDouble(tagName, value);
		entity.load(dataIndex);
	}
}
