package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class EndTitaniumChestplateProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		boolean valid = false;
		double rndx = 0;
		double rndz = 0;
		double rndy = 0;
		double chance = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.END_TITANIUM_CHESTPLATE.get()) {
			chance = 0.2;
			chance = chance + (entity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(Attributes.LUCK) ? _livingEntity2.getAttribute(Attributes.LUCK).getValue() : 0) * 0.05;
			if (!(sourceentity instanceof Player) && Math.random() <= chance) {
				for (int index0 = 0; index0 < 40; index0++) {
					rndx = sourceentity.getX() - 20 + Mth.nextInt(RandomSource.create(), 0, 40);
					rndy = Math.max(-60, sourceentity.getY() - 20) + Mth.nextInt(RandomSource.create(), 0, 40);
					rndz = sourceentity.getZ() - 20 + Mth.nextInt(RandomSource.create(), 0, 40);
					for (int index1 = 0; index1 < 40; index1++) {
						if (!(world.getBlockState(BlockPos.containing(rndx, rndy - 1, rndz)).canOcclude() && !world.getBlockState(BlockPos.containing(rndx, rndy, rndz)).canOcclude()
								&& !world.getBlockState(BlockPos.containing(rndx, rndy + 1, rndz)).canOcclude())) {
							rndy = rndy - 1;
						} else {
							valid = true;
							break;
						}
					}
					if (valid == true) {
						{
							Entity _ent = sourceentity;
							_ent.teleportTo(rndx, rndy, rndz);
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport(rndx, rndy, rndz, _ent.getYRot(), _ent.getXRot());
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						break;
					}
				}
			}
		}
	}
}