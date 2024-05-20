package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteBowNetherDiamondExplosionProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getSource().getDirectEntity());
		}
	}

	public static void execute(Entity immediatesourceentity) {
		execute(null, immediatesourceentity);
	}

	private static void execute(@Nullable Event event, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		if (immediatesourceentity.getPersistentData().getBoolean("crystallite_nether_diamond_upgrade")) {
			if (!immediatesourceentity.isInWaterRainOrBubble()) {
				{
					Entity _ent = immediatesourceentity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "summon armor_stand ~ ~ ~ {Invisible:1b,ForgeData:{crystallite_nether_diamond_upgrade:1b}}");
					}
				}
				if ((new Object() {
					public String getValue() {
						CompoundTag dataIndex3 = new CompoundTag();
						immediatesourceentity.saveWithoutId(dataIndex3);
						return dataIndex3.getString("Potion");
					}
				}.getValue()).length() > 0) {
					{
						Entity _ent = immediatesourceentity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("summon minecraft:area_effect_cloud ~ ~ ~ {Duration:600,DurationOnUse:0,Potion:\"" + "" + (new Object() {
										public String getValue() {
											CompoundTag dataIndex4 = new CompoundTag();
											immediatesourceentity.saveWithoutId(dataIndex4);
											return dataIndex4.getString("Potion");
										}
									}.getValue()) + "\",Particle:\"minecraft:entity_effect\",Radius:3.0f,RadiusOnUse:-0.5f,RadiusPerTick:-0.005f,ReapplicationDelay:20,WaitTime:0}"));
						}
					}
				}
				if (!immediatesourceentity.level().isClientSide())
					immediatesourceentity.discard();
			}
		}
	}
}
