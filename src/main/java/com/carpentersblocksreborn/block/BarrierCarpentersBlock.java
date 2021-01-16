package com.carpentersblocksreborn.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.util.Direction;

public class BarrierCarpentersBlock extends FenceBlock {

    public BarrierCarpentersBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canConnect(BlockState state, boolean isSideSolid, Direction direction) {
        Block block = state.getBlock();
        boolean flag = block instanceof BarrierCarpentersBlock;
        boolean flag1 = block instanceof FenceGateBlock && FenceGateBlock.isParallel(state, direction);
        return !cannotAttach(block) && isSideSolid || flag || flag1;
    }
}


