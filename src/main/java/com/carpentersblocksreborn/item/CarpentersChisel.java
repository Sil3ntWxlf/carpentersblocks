package com.carpentersblocksreborn.item;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nullable;
import java.util.Set;

public class CarpentersChisel implements IForgeItem {
    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return null;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
        return 0;
    }

    @Override
    public ItemStackTileEntityRenderer getItemStackTileEntityRenderer() {
        return null;
    }

    @Override
    public Set<ResourceLocation> getTags() {
        return null;
    }
}
