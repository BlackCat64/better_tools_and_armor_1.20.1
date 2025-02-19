package net.mcreator.bettertoolsandarmor.block.entity;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModBlocks;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BetterToolsMod.MODID);
	public static final RegistryObject<BlockEntityType<MetalSignBlockEntity>> METAL_SIGN = REGISTRY.register("metal_sign", () -> BlockEntityType.Builder.of(MetalSignBlockEntity::new, BetterToolsModBlocks.BLACK_METAL_SIGN.get(),
			BetterToolsModBlocks.BLACK_METAL_WALL_SIGN.get(), BetterToolsModBlocks.BLUE_METAL_SIGN.get(), BetterToolsModBlocks.BLUE_METAL_WALL_SIGN.get(), BetterToolsModBlocks.BROWN_METAL_SIGN.get(), BetterToolsModBlocks.BROWN_METAL_WALL_SIGN.get(),
			BetterToolsModBlocks.CREAM_METAL_SIGN.get(), BetterToolsModBlocks.CREAM_METAL_WALL_SIGN.get(), BetterToolsModBlocks.CYAN_METAL_SIGN.get(), BetterToolsModBlocks.CYAN_METAL_WALL_SIGN.get(), BetterToolsModBlocks.DARK_ORANGE_METAL_SIGN.get(),
			BetterToolsModBlocks.DARK_ORANGE_METAL_WALL_SIGN.get(), BetterToolsModBlocks.DARK_PINK_METAL_SIGN.get(), BetterToolsModBlocks.DARK_PINK_METAL_WALL_SIGN.get(), BetterToolsModBlocks.DARK_RED_METAL_SIGN.get(),
			BetterToolsModBlocks.DARK_RED_METAL_WALL_SIGN.get(), BetterToolsModBlocks.GRAY_METAL_SIGN.get(), BetterToolsModBlocks.GRAY_METAL_WALL_SIGN.get(), BetterToolsModBlocks.GREEN_METAL_SIGN.get(),
			BetterToolsModBlocks.GREEN_METAL_WALL_SIGN.get(), BetterToolsModBlocks.LIGHT_BLUE_METAL_SIGN.get(), BetterToolsModBlocks.LIGHT_BLUE_METAL_WALL_SIGN.get(), BetterToolsModBlocks.LIGHT_GRAY_METAL_SIGN.get(),
			BetterToolsModBlocks.LIGHT_GRAY_METAL_WALL_SIGN.get(), BetterToolsModBlocks.LIME_METAL_SIGN.get(), BetterToolsModBlocks.LIME_METAL_WALL_SIGN.get(), BetterToolsModBlocks.MAGENTA_METAL_SIGN.get(),
			BetterToolsModBlocks.MAGENTA_METAL_WALL_SIGN.get(), BetterToolsModBlocks.ORANGE_METAL_SIGN.get(), BetterToolsModBlocks.ORANGE_METAL_WALL_SIGN.get(), BetterToolsModBlocks.PINK_METAL_SIGN.get(),
			BetterToolsModBlocks.PINK_METAL_WALL_SIGN.get(), BetterToolsModBlocks.PURPLE_METAL_SIGN.get(), BetterToolsModBlocks.PURPLE_METAL_WALL_SIGN.get(), BetterToolsModBlocks.RED_METAL_SIGN.get(), BetterToolsModBlocks.RED_METAL_WALL_SIGN.get(),
			BetterToolsModBlocks.WHITE_METAL_SIGN.get(), BetterToolsModBlocks.WHITE_METAL_WALL_SIGN.get(), BetterToolsModBlocks.YELLOW_METAL_SIGN.get(), BetterToolsModBlocks.YELLOW_METAL_WALL_SIGN.get()).build(null));
	public static final RegistryObject<BlockEntityType<MetalHangingSignBlockEntity>> METAL_HANGING_SIGN = REGISTRY.register("metal_hanging_sign",
			() -> BlockEntityType.Builder.of(MetalHangingSignBlockEntity::new, BetterToolsModBlocks.BLACK_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.BLACK_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.BLUE_METAL_HANGING_SIGN.get(),
					BetterToolsModBlocks.BLUE_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.BROWN_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.BROWN_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.CREAM_METAL_HANGING_SIGN.get(),
					BetterToolsModBlocks.CREAM_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.CYAN_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.CYAN_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.DARK_ORANGE_METAL_HANGING_SIGN.get(),
					BetterToolsModBlocks.DARK_ORANGE_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.DARK_PINK_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.DARK_PINK_METAL_WALL_HANGING_SIGN.get(),
					BetterToolsModBlocks.DARK_RED_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.DARK_RED_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.GRAY_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.GRAY_METAL_WALL_HANGING_SIGN.get(),
					BetterToolsModBlocks.GREEN_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.GREEN_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.LIGHT_BLUE_METAL_HANGING_SIGN.get(),
					BetterToolsModBlocks.LIGHT_BLUE_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.LIGHT_GRAY_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.LIGHT_GRAY_METAL_WALL_HANGING_SIGN.get(),
					BetterToolsModBlocks.LIME_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.LIME_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.MAGENTA_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.MAGENTA_METAL_WALL_HANGING_SIGN.get(),
					BetterToolsModBlocks.ORANGE_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.ORANGE_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.PINK_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.PINK_METAL_WALL_HANGING_SIGN.get(),
					BetterToolsModBlocks.PURPLE_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.PURPLE_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.RED_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.RED_METAL_WALL_HANGING_SIGN.get(),
					BetterToolsModBlocks.WHITE_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.WHITE_METAL_WALL_HANGING_SIGN.get(), BetterToolsModBlocks.YELLOW_METAL_HANGING_SIGN.get(), BetterToolsModBlocks.YELLOW_METAL_WALL_HANGING_SIGN.get())
					.build(null));
}
