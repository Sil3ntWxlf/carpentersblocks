package com.carpentersblocksreborn.client.model;

import com.carpentersblocksreborn.block.entity.CarpentersBlockEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.data.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class CarpentersBakedModel implements IDynamicBakedModel {
    private static final ModelProperty<TextureAtlasSprite> TEXTURE_PROPERTY = new ModelProperty<>();
    private final ImmutableList<BakedQuad> quads;
    private final TextureAtlasSprite particle;

    public CarpentersBakedModel(ImmutableList<BakedQuad> quads, TextureAtlasSprite particle) {
        this.quads = quads;
        this.particle = particle;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        return this.quads;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isSideLit() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return particle;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return null;
    }

    @Nonnull
    @Override
    public IModelData getModelData(@Nonnull IBlockDisplayReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData modelData) {
        TileEntity te = world.getTileEntity(pos);

        if (modelData == EmptyModelData.INSTANCE) {
            modelData = new ModelDataMap.Builder().withInitial(TEXTURE_PROPERTY,
                    // FIXME change the `withInitial` once we have the default texture implemented
                    Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(Blocks.OAK_PLANKS.getDefaultState()).getParticleTexture()
                    ).build();
        }

        if (te instanceof CarpentersBlockEntity) {
            Block block = ((CarpentersBlockEntity) te).getMimicBlock();

            BlockState blockState = Blocks.OAK_PLANKS.getDefaultState(); // TODO change
            if (!(block instanceof AirBlock))
                blockState = ((CarpentersBlockEntity) te).getMimicBlock().getDefaultState();
            // TODO Else get default once we get default tex

            modelData.setData(TEXTURE_PROPERTY, Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(blockState).getParticleTexture());
        }

        return modelData;
    }
}
