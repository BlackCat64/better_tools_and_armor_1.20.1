package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

public class GetEntityTextDataInListProcedure {
	public static String execute(Entity entity, String listTag, String tagName) {
		if (entity == null || listTag == null || tagName == null)
			return "";
		String value = "";
		if (false) {
			entity.getPersistentData().putDouble(tagName, 0);
			entity.getPersistentData().putDouble(listTag, 0);
		}
		CompoundTag dataIndex = new CompoundTag();
		entity.saveWithoutId(dataIndex);
		value = dataIndex.getCompound(listTag).getString(tagName);
		return value;
	}
}
