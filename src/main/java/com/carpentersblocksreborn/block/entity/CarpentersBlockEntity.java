package com.carpentersblocksreborn.block.entity;

import com.carpentersblocksreborn.init.CarpentersBlockEntityTypes;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class CarpentersBlockEntity extends TileEntity {
    private static final String NBT_KEY_BLOCK = "displayedState";

    @Nonnull
    private Block blockMimic = Blocks.AIR;

    public CarpentersBlockEntity() {
        super(CarpentersBlockEntityTypes.CARPENTERS_BLOCK.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        compound.putString(NBT_KEY_BLOCK, this.blockMimic.getRegistryName().toString());

        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);

        //noinspection ConstantConditions - Resolved by the following conditional
        this.blockMimic = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(nbt.getString(NBT_KEY_BLOCK)));

        if (this.blockMimic == null) {
            this.blockMimic = Blocks.AIR;
        }
    }

    public boolean isMimicBlock() {
        return !(this.blockMimic instanceof AirBlock);
    }

    public void setMimicBlock(Block block) {
        this.blockMimic = block;
    }

    @Nonnull
    public Block getMimicBlock() {
        return this.blockMimic;
    }

    @Nonnull
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, -1, this.write(new CompoundNBT()));
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT defaultNBT = super.getUpdateTag();

        defaultNBT.putString(NBT_KEY_BLOCK, this.blockMimic.getRegistryName().toString());

        return defaultNBT;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        // TODO: Rendering
    }
}
