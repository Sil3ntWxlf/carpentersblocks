package com.carpentersblocksreborn.block;

import com.carpentersblocksreborn.init.CarpentersBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.extensions.IForgeBlock;

import javax.annotation.Nullable;

public interface CarpentersBlock extends IForgeBlock {
    @Override
    default boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    @Nullable
    default TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return CarpentersBlockEntityTypes.CARPENTERS_BLOCK.get().create();
    }

    default Item getItem() {
        return null;
    }
}
