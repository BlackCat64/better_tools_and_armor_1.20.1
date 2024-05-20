package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class CrystalliteBowNetherDiamondImpactExplodeProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		if (!(((Entity) world.getEntitiesOfClass(Arrow.class, AABB.ofSize(new Vec3(x, y, z), 100, 100, 100), e -> true).stream().sorted(new Object() {
			Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
			}
		}.compareDistOf(x, y, z)).findFirst().orElse(null)) == null)) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(100 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator.getPersistentData().getBoolean("crystallite_nether_diamond_upgrade") && new Object() {
						public boolean getValue() {
							CompoundTag dataIndex3 = new CompoundTag();
							entityiterator.saveWithoutId(dataIndex3);
							return dataIndex3.getBoolean("inGround");
						}
					}.getValue()) {
						if (!entityiterator.isInWaterRainOrBubble()) {
							{
								Entity _ent = entityiterator;
								if (!_ent.level().isClientSide() && _ent.getServer() != null) {
									_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null,
											4, _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "summon armor_stand ~ ~ ~ {Invisible:1b,ForgeData:{crystallite_nether_diamond_upgrade:1b}}");
								}
							}
							if ((new Object() {
								public String getValue() {
									CompoundTag dataIndex6 = new CompoundTag();
									entityiterator.saveWithoutId(dataIndex6);
									return dataIndex6.getString("Potion");
								}
							}.getValue()).length() > 0) {
								{
									Entity _ent = entityiterator;
									if (!_ent.level().isClientSide() && _ent.getServer() != null) {
										_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(),
												_ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent),
												("summon minecraft:area_effect_cloud ~ ~ ~ {Duration:600,DurationOnUse:0,Potion:\"" + "" + (new Object() {
													public String getValue() {
														CompoundTag dataIndex7 = new CompoundTag();
														entityiterator.saveWithoutId(dataIndex7);
														return dataIndex7.getString("Potion");
													}
												}.getValue()) + "\",Particle:\"minecraft:entity_effect\",Radius:3.0f,RadiusOnUse:-0.5f,RadiusPerTick:-0.005f,ReapplicationDelay:20,WaitTime:0}"));
									}
								}
							}
							if (!entityiterator.level().isClientSide())
								entityiterator.discard();
						}
					}
				}
			}
		}
	}
}
