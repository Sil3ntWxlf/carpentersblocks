package com.carpentersblocksreborn.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import java.util.function.Supplier;

public class StairsCarpentersBlock extends StairsBlock implements CarpentersBlock {
    public StairsCarpentersBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }
}
