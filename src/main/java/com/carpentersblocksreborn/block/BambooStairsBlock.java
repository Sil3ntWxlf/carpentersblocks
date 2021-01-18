package com.carpentersblocksreborn.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import java.util.function.Supplier;

public class BambooStairsBlock extends StairsBlock {
    public BambooStairsBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }
}
