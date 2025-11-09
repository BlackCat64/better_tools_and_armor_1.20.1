package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

public class GetEntityTextDataProcedure {
	public static String execute(Entity entity, String tagName) {
		if (entity == null || tagName == null)
			return "";
		String value = "";
		if (false) {
			entity.getPersistentData().putDouble(tagName, 0);
		}
		CompoundTag dataIndex = new CompoundTag();
		entity.saveWithoutId(dataIndex);
		value = dataIndex.getString(tagName);
		return value;
	}
}
