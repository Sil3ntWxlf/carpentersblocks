package com.carpentersblocksreborn.block;

import com.carpentersblocksreborn.block.entity.CarpentersBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class BarrierCarpentersBlock extends FenceBlock implements CarpentersBlock {
    public BarrierCarpentersBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canConnect(BlockState state, boolean isSideSolid, Direction direction) {
        Block block = state.getBlock();
        if (block instanceof BarrierCarpentersBlock) {
            return true;
        }
        boolean woodenFence = block.isIn(BlockTags.FENCES) && block.isIn(BlockTags.WOODEN_FENCES);
        boolean fenceGate = block instanceof FenceGateBlock && FenceGateBlock.isParallel(state, direction);
        return !cannotAttach(block) && isSideSolid || woodenFence || fenceGate;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        TileEntity blockEntity = world.getTileEntity(pos);
        if (blockEntity == null) {
            return super.onBlockActivated(state, world, pos, player, hand, hit);
        }

        if (!(blockEntity instanceof CarpentersBlockEntity)) {
            return super.onBlockActivated(state, world, pos, player, hand, hit);
        }

        CarpentersBlockEntity carpentersBlockEntity = (CarpentersBlockEntity) blockEntity;
        if (carpentersBlockEntity.getState() != null && !carpentersBlockEntity.getState().toString().isEmpty()) {
            return super.onBlockActivated(state, world, pos, player, hand, hit);
        }

        Item heldItem = player.getHeldItemMainhand().getItem();
        if (!(heldItem instanceof BlockItem) || ((BlockItem) heldItem).getBlock() instanceof CarpentersBlock) {
            return super.onBlockActivated(state, world, pos, player, hand, hit);
        }

        BlockItem item = (BlockItem) heldItem;
        carpentersBlockEntity.setState(item.getRegistryName());
        player.playSound(SoundEvents.ENTITY_ITEM_FRAME_PLACE, 1f, 1f);
        player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount() - 1);

        if (player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) player).connection.sendPacket(carpentersBlockEntity.getUpdatePacket());
        }
        return ActionResultType.CONSUME;
    }
}


