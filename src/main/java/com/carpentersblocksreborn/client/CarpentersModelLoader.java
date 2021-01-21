package com.carpentersblocksreborn.client;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class CarpentersModelLoader implements IModelLoader<CarpentersUnbakedModel> {
    public static final CarpentersModelLoader INSTANCE = new CarpentersModelLoader();

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public CarpentersUnbakedModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return null;
    }
}
