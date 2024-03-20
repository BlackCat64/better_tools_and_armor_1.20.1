package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class CrystalliteChestplateAmethystProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).crystallite_amethyst_absorption_timer == 0) {
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_hurt > 200) {
				if ((entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) < 4) {
					CompoundTag dataIndex2 = new CompoundTag();
					entity.saveWithoutId(dataIndex2);
					dataIndex2.putDouble("AbsorptionAmount", (new Object() {
						public double getValue() {
							CompoundTag dataIndex1 = new CompoundTag();
							entity.saveWithoutId(dataIndex1);
							return dataIndex1.getDouble("AbsorptionAmount");
						}
					}.getValue() + 1));
					entity.load(dataIndex2);
					{
						double _setval = 300;
						entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.crystallite_amethyst_absorption_timer = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		} else {
			if (entity.getY() < 60 && !world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
				{
					double _setval = (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).crystallite_amethyst_absorption_timer - 1;
					entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.crystallite_amethyst_absorption_timer = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
