package com.carpentersblocksreborn.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;

public class WaterloggableCarpentersBlock extends Block implements IWaterLoggable {
    public WaterloggableCarpentersBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(BlockStateProperties.WATERLOGGED, false));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.WATERLOGGED);
    }
}
