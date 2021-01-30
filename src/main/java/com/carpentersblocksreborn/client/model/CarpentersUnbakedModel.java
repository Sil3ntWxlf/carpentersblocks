package com.carpentersblocksreborn.client.model;

import com.carpentersblocksreborn.client.UnbakedQuad;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CarpentersUnbakedModel implements IModelGeometry<CarpentersUnbakedModel> {
    private final List<UnbakedQuad> unbakedQuads;

    public CarpentersUnbakedModel(List<UnbakedQuad> unbakedQuads) {
        this.unbakedQuads = unbakedQuads;
    }

    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
        return new CarpentersBakedModel(unbakedQuads.stream().map(UnbakedQuad::bake).collect(Collectors.toList()));
    }

    @Override
    public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        // TODO our default texture should be the wooden scaffolding. This should be defined by the json though
        return ImmutableList.of();
    }
}
