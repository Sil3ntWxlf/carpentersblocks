package carpentersblocksreborn;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("carpentersblocksreborn")
public class CarpentersBlocksReborn
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public CarpentersBlocksReborn() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        public static Block helloBlock = null;
        @SubscribeEvent
        public static void onBlocksRegistry(RegistryEvent.Register<Block> blockRegistryEvent) {
            IForgeRegistry<Block> blocks = blockRegistryEvent.getRegistry();
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_barrier"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block_collapsible"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slab"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slab_vertical"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_daylight_sensor"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_door"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_garage_door"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_gate"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_hatch"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_ladder"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_lantern"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_scaffolding"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block_slope"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block_slope_oblique_exterior"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block_oblique_interior"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_prism"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_wedge"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_wedge_prism"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_stairs"));
            blocks.register(helloBlock);
            helloBlock = new Block(AbstractBlock.Properties.create(Material.WOOD));
            helloBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_staircase"));
            blocks.register(helloBlock);

        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            // register a new item here
            LOGGER.info("HELLO from Item Registry");
            IForgeRegistry<Item> items = itemRegistryEvent.getRegistry();
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_chisel")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_barrier")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_bed")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block_collapsible")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slab")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slab_vertical")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_boat")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_button")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_daylight_sensor")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_door")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_flowerpot")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_garage_door")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_gate")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_hammer")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_hatch")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_item_frame")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_ladder")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_lantern")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_lever")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_minecart")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_pressure_plate")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_rope")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_safe")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_scaffolding")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_oblique_exterior")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_oblique_interior")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_prism")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_wedge")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_wedge_prism")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_stairs")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_staircase")));
            items.register(new BlockItem(helloBlock, new Item.Properties()).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_torch")));
        }

        @SubscribeEvent
        public static void onTilesRegistry(final RegistryEvent.Register<TileEntityType<?>> blockRegistryEvent) {
            // register a new tile entity type here
            LOGGER.info("HELLO from Tiles Registry");
        }
    }
}
