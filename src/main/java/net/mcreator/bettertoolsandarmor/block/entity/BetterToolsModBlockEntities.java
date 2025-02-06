package net.mcreator.bettertoolsandarmor.block.entity;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModBlocks;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BetterToolsMod.MODID);
	public static final RegistryObject<BlockEntityType<MetalSignBlockEntity>> METAL_SIGN = REGISTRY.register("metal_sign",
			() -> BlockEntityType.Builder.of(MetalSignBlockEntity::new, BetterToolsModBlocks.BLACK_METAL_SIGN.get(), BetterToolsModBlocks.BLACK_METAL_WALL_SIGN.get()).build(null));
}
