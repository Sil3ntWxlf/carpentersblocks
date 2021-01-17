package com.carpentersblocksreborn.init;

import com.carpentersblocksreborn.CarpentersBlocksReborn;
import com.carpentersblocksreborn.block.entity.CarpentersBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CarpentersBlockEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, CarpentersBlocksReborn.MOD_ID);

    public static final RegistryObject<TileEntityType<CarpentersBlockEntity>> CARPENTERS_BLOCK = BLOCK_ENTITY_TYPES.register("carpenters_block", () ->
            TileEntityType.Builder.create(CarpentersBlockEntity::new, CarpentersBlocks.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .filter(block -> block.getRegistryName().getPath()
                            .startsWith("carpenters"))
                    .toArray(Block[]::new))
                    .build(null)
    );
}
