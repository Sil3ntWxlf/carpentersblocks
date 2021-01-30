package com.carpentersblocksreborn.client;

import com.carpentersblocksreborn.client.model.CarpentersModelLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientRegisterEvents {

    @SubscribeEvent
    public void modelEvent(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation("carpentersblocksreloaded", "vertices"), CarpentersModelLoader.INSTANCE);
    }
}
