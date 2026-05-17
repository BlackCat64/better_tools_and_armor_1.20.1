package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class BreakBlockWithPickaxeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		ItemStack item_to_drop = ItemStack.EMPTY;
		String reg_name = "";
		double count_to_drop = 0;
		count_to_drop = 1;
		reg_name = BuiltInRegistries.BLOCK.getKey(blockstate.getBlock()).toString();
		if (!world.isClientSide() && world.getServer() != null) {
			BlockPos _bpLootTblWorld = BlockPos.containing(x, y, z);
			for (ItemStack itemstackiterator : world.getServer().reloadableRegistries()
					.getLootTable(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse(((reg_name.split("\\:")[0] + ":blocks/" + reg_name.split("\\:")[1])).toLowerCase(java.util.Locale.ENGLISH))))
					.getRandomItems(new LootParams.Builder((ServerLevel) world).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(_bpLootTblWorld)).withParameter(LootContextParams.BLOCK_STATE, world.getBlockState(_bpLootTblWorld))
							.withOptionalParameter(LootContextParams.BLOCK_ENTITY, world.getBlockEntity(_bpLootTblWorld)).create(LootContextParamSets.EMPTY))) {
				item_to_drop = itemstackiterator.copy();
			}
		}
		if (world instanceof Level _level4 && _level4.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(item_to_drop), _level4).isPresent()
				&& ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
						.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:smelting_touch")))) != 0
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:smelting_touch_tools"))))) {
			item_to_drop = (world instanceof Level _lvlSmeltResult
					? _lvlSmeltResult.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(item_to_drop), _lvlSmeltResult).map(recipe -> recipe.value().getResultItem(_lvlSmeltResult.registryAccess()).copy())
							.orElse(ItemStack.EMPTY)
					: ItemStack.EMPTY).copy();
		} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH)) != 0) {
			item_to_drop = (new ItemStack(blockstate.getBlock())).copy();
		}
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)) != 0) {
			count_to_drop = item_to_drop.getCount() * FortuneGetNumOfDropsProcedure
					.execute((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)));
		}
		if (item_to_drop.getItem() == (new ItemStack(blockstate.getBlock())).getItem()) {
			count_to_drop = 1;
		} else if (CrystallitePickaxeTopazDoubleDropsProcedure.execute(world, x, y, z, blockstate, entity)) {
			count_to_drop = count_to_drop * 2;
		}
		item_to_drop.setCount((int) count_to_drop);
		world.destroyBlock(BlockPos.containing(x, y, z), false);
		if (world instanceof Level _level)
			_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		if (world instanceof ServerLevel _level) {
			ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), item_to_drop);
			entityToSpawn.setPickUpDelay(10);
			_level.addFreshEntity(entityToSpawn);
		}
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			if (world instanceof ServerLevel _level) {
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {
				});
			}
		}
	}
}
