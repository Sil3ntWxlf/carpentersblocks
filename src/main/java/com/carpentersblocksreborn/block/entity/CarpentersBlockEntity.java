package com.carpentersblocksreborn.block.entity;

import com.carpentersblocksreborn.init.CarpentersBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class CarpentersBlockEntity extends TileEntity {
    private ResourceLocation state;

    public CarpentersBlockEntity() {
        super(CarpentersBlockEntityTypes.CARPENTERS_BLOCK.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT defaultNbt = super.write(compound);
        defaultNbt.putString("displayedState", this.state == null ? "" : this.state.toString());
        return defaultNbt;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);

        this.state = new ResourceLocation(nbt.getString("displayedState"));
    }

    public ResourceLocation getState() {
        return this.state;
    }

    public void setState(ResourceLocation state) {
        this.state = state;
    }

    @Nonnull
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, -1, this.write(new CompoundNBT()));
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT defaultNbt = super.getUpdateTag();
        defaultNbt.putString("displayedState", this.state == null ? "" : this.state.toString());
        return defaultNbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        // TODO: Rendering
    }
}
