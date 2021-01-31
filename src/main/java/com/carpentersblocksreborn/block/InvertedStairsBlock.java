package com.carpentersblocksreborn.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

import java.util.function.Supplier;

public class InvertedStairsBlock extends StairsBlock {
    public InvertedStairsBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }
}
