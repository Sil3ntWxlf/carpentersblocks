package com.carpentersblocksreborn.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import java.util.function.Supplier;

import static net.minecraft.block.SlabBlock.WATERLOGGED;

public class VerticalSlabsBambooBlock extends WaterloggableCarpentersBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty FULL = BooleanProperty.create("full");

    protected static final VoxelShape N_CORNER = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape S_CORNER = Block.makeCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape E_CORNER = Block.makeCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape W_CORNER = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);

    public VerticalSlabsBambooBlock(Supplier<BlockState> state, Properties properties) {
       super(properties);

       this.setDefaultState(this.stateContainer.getBaseState()
               .with(FACING, Direction.NORTH)
               .with(FULL, false)
               .with(WATERLOGGED, false)
       );
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, FULL, WATERLOGGED);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(blockpos);
        BlockState blockstate = this.getDefaultState()
                .with(FACING, context.getPlacementHorizontalFacing())
                .with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
        if (context.replacingClickedOnBlock()) {
            return blockstate.with(FULL, true);
        }
        return blockstate;
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        ItemStack itemstack = useContext.getItem();
        boolean full = state.get(FULL);
        if (!full && itemstack.getItem() == this.asItem()) {
            if (!useContext.getNearestLookingDirection().getAxis().isHorizontal()) {
                return false;
            }
            if (useContext.replacingClickedOnBlock()) {
                return state.get(FACING) == useContext.getNearestLookingDirection();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(FULL)) {
            return VoxelShapes.fullCube();
        }
        switch (state.get(FACING)) {
            case NORTH:
                return N_CORNER;
            case SOUTH:
                return S_CORNER;
            case EAST:
                return E_CORNER;
            case WEST:
                return W_CORNER;
            default:
                return VoxelShapes.fullCube();
        }
    }
}
