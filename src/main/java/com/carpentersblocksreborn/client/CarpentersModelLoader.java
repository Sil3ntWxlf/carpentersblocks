package com.carpentersblocksreborn.client;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

import java.util.List;
import java.util.function.Function;

/* Instead of building models in the clean element model parser of vanilla default,
    we'll instead build our own models through basically raw vertex input to build our quads instead.
    This will let us build the less-mundane models, especially the slopes */
public class CarpentersModelLoader implements IModelLoader<CarpentersUnbakedModel> {
    public static final CarpentersModelLoader INSTANCE = new CarpentersModelLoader();

    private static final Function<JsonElement, DataResult<List<UnbakedQuad>>> DESERIALIZER = JsonOps.INSTANCE.withParser(UnbakedQuad.CODEC.listOf());

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public CarpentersUnbakedModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new CarpentersUnbakedModel(DESERIALIZER.apply(modelContents.getAsJsonArray("quads")).mapError(s -> "Model deserialization error: " + s).getOrThrow(false, System.out::println));
    }
}
