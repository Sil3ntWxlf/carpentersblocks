package com.carpentersblocksreborn.client.model;

import com.carpentersblocksreborn.client.UnbakedQuad;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class CarpentersUnbakedModel implements IModelGeometry<CarpentersUnbakedModel> {
    private final List<UnbakedQuad> unbakedQuads;
    private final static RenderMaterial DEFAULT = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, MissingTextureSprite.getLocation());

    public CarpentersUnbakedModel(List<UnbakedQuad> unbakedQuads) {
        this.unbakedQuads = unbakedQuads;
    }

    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {


        return new CarpentersBakedModel(unbakedQuads.stream().map(unbakedQuad -> unbakedQuad.bake(spriteGetter)).collect(ImmutableList.toImmutableList()), spriteGetter.apply(resolveTextureName("particle")));
    }

    private Either<RenderMaterial, String> findTexture(String nameIn) {
        for(BlockModel blockmodel = this; blockmodel != null; blockmodel = blockmodel.parent) {
            Either<RenderMaterial, String> either = blockmodel.textures.get(nameIn);
            if (either != null) {
                return either;
            }
        }

        return Either.left(DEFAULT);
    }    private static boolean startsWithPound(String strIn) {
        return strIn.charAt(0) == '#';
    }

    public RenderMaterial resolveTextureName(String nameIn) {
        if (startsWithPound(nameIn)) {
            nameIn = nameIn.substring(1);
        }

        List<String> list = Lists.newArrayList();

        while(true) {
            Either<RenderMaterial, String> either = this.findTexture(nameIn);
            Optional<RenderMaterial> optional = either.left();
            if (optional.isPresent()) {
                return optional.get();
            }

            nameIn = either.right().get();
            if (list.contains(nameIn)) {
                LOGGER.warn("Unable to resolve texture due to reference chain {}->{} in {}", Joiner.on("->").join(list), nameIn, this.name);
                return new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, MissingTextureSprite.getLocation());
            }

            list.add(nameIn);
        }
    }

    @Override
    public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        Set<RenderMaterial> set1 = Sets.newHashSet(this.resolveTextureName("particle"));

        // TODO our default texture should be the wooden scaffolding. This should be defined by the json though
        return ImmutableList.of();
    }
}
