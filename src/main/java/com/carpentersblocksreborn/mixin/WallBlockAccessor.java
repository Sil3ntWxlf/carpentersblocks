package com.carpentersblocksreborn.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WallHeight;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WallBlock.class)
public interface WallBlockAccessor {

    @Invoker("hasHeightForProperty") static boolean accessor$hasHeightForProperty(BlockState state, Property<WallHeight> heightProperty) {
        throw new IllegalStateException("Cannot create transformer!");
    }

    @Invoker("func_235626_a_") BlockState invoker$updateShape(IWorldReader reader, BlockState state, BlockPos pos, BlockState collisionState, boolean connectedSouth, boolean connectedWest, boolean connectedNorth, boolean connectedEast);
}
