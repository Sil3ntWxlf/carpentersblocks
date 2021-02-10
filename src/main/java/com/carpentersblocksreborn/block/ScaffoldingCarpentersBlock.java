package com.carpentersblocksreborn.block;

import com.carpentersblocksreborn.init.CarpentersBlocks;
import com.carpentersblocksreborn.init.CarpentersItemGroups;
import com.carpentersblocksreborn.item.ScaffoldingCarpentersItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ScaffoldingCarpentersBlock extends ScaffoldingBlock implements CarpentersBlock {
    public ScaffoldingCarpentersBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        World world = context.getWorld();
        int i = getDistance(world, blockpos);
        return this.getDefaultState().with(WATERLOGGED, world.getFluidState(blockpos).getFluid() == Fluids.WATER)
                .with(DISTANCE, i)
                .with(BOTTOM, this.hasScaffoldingBelow(world, blockpos, i));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, WATERLOGGED, BOTTOM);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        int i = getDistance(worldIn, pos);
        BlockState blockstate = state.with(DISTANCE, i)
                .with(BOTTOM, this.hasScaffoldingBelow(worldIn, pos, i));
        if (blockstate.get(DISTANCE) == 7) {
            if (state.get(DISTANCE) == 7) {
                worldIn.addEntity(new FallingBlockEntity(worldIn, (double) pos.getX() + 0.5D,pos.getY(), (double) pos.getZ() + 0.5D, blockstate.with(WATERLOGGED, false)));
            } else {
                worldIn.destroyBlock(pos, true);
            }
        } else if (state != blockstate) {
            worldIn.setBlockState(pos, blockstate, 3);
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return getDistance(worldIn, pos) < 7;
    }

    private boolean hasScaffoldingBelow(IBlockReader blockReader, BlockPos pos, int distance) {
        return distance > 0 && !blockReader.getBlockState(pos.down()).isIn(this);
    }

    public static int getDistance(IBlockReader blockReader, BlockPos pos) {
        BlockPos.Mutable mutablePos = pos.toMutable().move(Direction.DOWN);
        BlockState blockstate = blockReader.getBlockState(mutablePos);
        int i = 7;
        if (blockstate.isIn(CarpentersBlocks.CARPENTERS_SCAFFOLDING.get())) {
            i = blockstate.get(DISTANCE);
        } else if (blockstate.isSolidSide(blockReader, mutablePos, Direction.UP)) {
            return 0;
        }

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockState blockstate1 = blockReader.getBlockState(mutablePos.setAndMove(pos, direction));
            if (blockstate1.isIn(CarpentersBlocks.CARPENTERS_SCAFFOLDING.get())) {
                i = Math.min(i, blockstate1.get(DISTANCE) + 1);
                if (i == 1) {
                    break;
                }
            }
        }

        return i;
    }

    @Override
    public Item getItem() {
        return new ScaffoldingCarpentersItem(this, new Item.Properties().group(CarpentersItemGroups.CARPENTERS_BLOCKS));
    }
}
