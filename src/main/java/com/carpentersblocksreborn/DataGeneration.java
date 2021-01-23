package com.carpentersblocksreborn;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = CarpentersBlocksReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent evt) {
        DataGenerator generator = evt.getGenerator();
        if (evt.includeServer()) {
            evt.getGenerator().addProvider(new Recipes(generator));
            evt.getGenerator().addProvider(new LootTables(generator));
        }

    }
}
