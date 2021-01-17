package com.carpentersblocksreborn;

import com.carpentersblocksreborn.init.CarpentersBlockEntityTypes;
import com.carpentersblocksreborn.init.CarpentersItemGroups;
import com.carpentersblocksreborn.init.CarpentersBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(CarpentersBlocksReborn.MOD_ID)
public class CarpentersBlocksReborn {
    public static final String MOD_ID = "carpentersblocksreborn";

    public CarpentersBlocksReborn() {
        MinecraftForge.EVENT_BUS.register(this);

        CarpentersBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CarpentersBlockEntityTypes.BLOCK_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        GeckoLib.initialize();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            CarpentersBlocks.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .forEach(block -> {
                        Item.Properties properties = new Item.Properties().group(CarpentersItemGroups.CARPENTERS_BLOCKS);
                        BlockItem blockItem = new BlockItem(block, properties);

                        blockItem.setRegistryName(block.getRegistryName());
                        itemRegistryEvent.getRegistry().register(blockItem);
                    });
        }
    }
}