package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.SimpleContainer;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

public class BreakBlockWithPickaxeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		ItemStack item_to_drop = ItemStack.EMPTY;
		String reg_name = "";
		double count_to_drop = 0;
		count_to_drop = 1;
		reg_name = ForgeRegistries.BLOCKS.getKey(blockstate.getBlock()).toString();
		if (!world.isClientSide() && world.getServer() != null) {
			BlockPos _bpLootTblWorld = BlockPos.containing(x, y, z);
			for (ItemStack itemstackiterator : world.getServer().getLootData().getLootTable(new ResourceLocation(((reg_name.split("\\:")[0] + ":blocks/" + reg_name.split("\\:")[1])).toLowerCase(java.util.Locale.ENGLISH)))
					.getRandomItems(new LootParams.Builder((ServerLevel) world).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(_bpLootTblWorld)).withParameter(LootContextParams.BLOCK_STATE, world.getBlockState(_bpLootTblWorld))
							.withOptionalParameter(LootContextParams.BLOCK_ENTITY, world.getBlockEntity(_bpLootTblWorld)).create(LootContextParamSets.EMPTY))) {
				item_to_drop = itemstackiterator;
			}
		}
		if (world instanceof Level _level4 && _level4.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(item_to_drop), _level4).isPresent()
				&& (EnchantmentHelper.getItemEnchantmentLevel(BetterToolsModEnchantments.SMELTING_TOUCH.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:smelting_touch_tools"))))) {
			item_to_drop = (world instanceof Level _lvlSmeltResult
					? _lvlSmeltResult.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(item_to_drop), _lvlSmeltResult).map(recipe -> recipe.getResultItem(_lvlSmeltResult.registryAccess()).copy()).orElse(ItemStack.EMPTY)
					: ItemStack.EMPTY);
		}
		if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) {
			item_to_drop = (new ItemStack(blockstate.getBlock()));
		} else if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) {
			count_to_drop = item_to_drop.getCount() * FortuneGetNumOfDropsProcedure.execute((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(Enchantments.BLOCK_FORTUNE));
		}
		if (item_to_drop.getItem() == (new ItemStack(blockstate.getBlock())).getItem()) {
			count_to_drop = 1;
		} else if (CrystallitePickaxeTopazDoubleDropsProcedure.execute(world, x, y, z, blockstate, entity)) {
			count_to_drop = count_to_drop * 2;
		}
		item_to_drop.setCount((int) count_to_drop);
		world.destroyBlock(BlockPos.containing(x, y, z), false);
		if (world instanceof ServerLevel _level) {
			ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), item_to_drop);
			entityToSpawn.setPickUpDelay(10);
			_level.addFreshEntity(entityToSpawn);
		}
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			{
				ItemStack _ist = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
				if (_ist.hurt(1, RandomSource.create(), null)) {
					_ist.shrink(1);
					_ist.setDamageValue(0);
				}
			}
		}
	}
}
