
package net.mcreator.bettertoolsandarmor.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.common.PlantType;

import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.procedures.PurpleMushroomSpaceCheckProcedure;
import net.mcreator.bettertoolsandarmor.procedures.PurpleMushroomProcedureProcedure;
import net.mcreator.bettertoolsandarmor.procedures.PurpleMushroomGrowProcedureProcedure;

import java.util.List;
import java.util.Collections;

public class PurpleMushroomBlock extends FlowerBlock implements BonemealableBlock {
	public PurpleMushroomBlock() {
		super(() -> MobEffects.SATURATION, 0,
				BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.GRASS).instabreak().lightLevel(s -> 4).noCollission().replaceable().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 100;
	}

	@Override
	public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
		return BlockPathTypes.DAMAGE_OTHER;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this));
	}

	@Override
	public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
		return groundState.is(Blocks.MYCELIUM);
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState groundState = worldIn.getBlockState(blockpos);
		return this.mayPlaceOn(groundState, worldIn, blockpos);
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.CAVE;
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		PurpleMushroomProcedureProcedure.execute(world, entity);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState blockstate, boolean clientSide) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState blockstate) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		return PurpleMushroomSpaceCheckProcedure.execute(world, x, y, z);
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState blockstate) {
		PurpleMushroomGrowProcedureProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}
