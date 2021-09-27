package com.carpentersblocksreborn;

import com.carpentersblocksreborn.block.CarpentersBlock;
import com.carpentersblocksreborn.client.model.CarpentersModelLoader;
import com.carpentersblocksreborn.init.CarpentersBlockEntityTypes;
import com.carpentersblocksreborn.init.CarpentersItemGroups;
import com.carpentersblocksreborn.init.CarpentersBlocks;
import com.carpentersblocksreborn.init.CarpentersItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//import software.bernie.geckolib3.GeckoLib;

import java.util.Objects;

@Mod(CarpentersBlocksReborn.MOD_ID)
public class CarpentersBlocksReborn {
    // TODO change to `carpentersblocks` when if we write model generation export
    public static final String MOD_ID = "carpentersblocksreborn";

    public CarpentersBlocksReborn() {
        MinecraftForge.EVENT_BUS.register(this);

        CarpentersBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CarpentersBlockEntityTypes.BLOCK_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        CarpentersItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener((FMLClientSetupEvent event) -> {
            RenderTypeLookup.setRenderLayer(CarpentersBlocks.CARPENTERS_TORCH.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(CarpentersBlocks.CARPENTERS_BLOCK.get(), RenderType.getTranslucent());
        });

        FMLJavaModLoadingContext.get().getModEventBus().addListener((ModelRegistryEvent event) -> {
            ModelLoaderRegistry.registerLoader(new ResourceLocation(CarpentersBlocksReborn.MOD_ID, "vertices"), CarpentersModelLoader.INSTANCE);

            System.out.println("Loaded custom model loader");
        });

        //GeckoLib.initialize();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            CarpentersBlocks.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .forEach(block -> {
                        Item.Properties properties = new Item.Properties().group(CarpentersItemGroups.CARPENTERS_BLOCKS);
                        Item item = null;
                        if (block instanceof CarpentersBlock) {
                            item = ((CarpentersBlock) block).getItem();
                        }
                        if (item == null) {
                            item = new BlockItem(block, properties);
                        }
                        item.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
                        itemRegistryEvent.getRegistry().register(item);
                    });
        }
    }
}