package com.carpentersblocksreborn;

import com.carpentersblocksreborn.block.BarrierCarpentersBlock;
import com.carpentersblocksreborn.block.WaterloggableCarpentersBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
//import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
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

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("carpentersblocksreborn")
public class CarpentersBlocksReborn {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    private static Supplier<? extends TileEntity> factory;
    private static Block validBlocks;

    public CarpentersBlocksReborn() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        GeckoLib.initialize();
    }

    private static void setFactory(Supplier<? extends TileEntity> factory) {
        CarpentersBlocksReborn.factory = factory;
    }

    private static void setValidBlocks(Block validBlocks) {
        CarpentersBlocksReborn.validBlocks = validBlocks;
    }

    private void setup(final FMLCommonSetupEvent event) {
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
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        private static Block cbrPlanks;
        private static Block cbrBSlabs;
        private static Block cbrBSV;
        private static Block cbrBarrier;
        //private static Block cbrChisel;
        //private static Block cbrBed;
        private static Block cbrBlock;
        private static Block cbrBC;
        private static Block cbrSlabs;
        private static Block cbrSV;
        //private static Block cbrBoat;
        //private static Block cbrButton;
        private static Block cbrDS;
        //private static Block cbrDoor;
        //private static Block cbrFlowerpot;
        private static Block cbrGD;
        private static Block cbrGate;
        //private static Block cbrHammer;
        private static Block cbrHatch;
        //private static Block cbrIF;
        private static Block cbrLadder;
        private static Block cbrLantern;
        //private static Block cbrLever;
        //private static Block cbrMinecart;
        //private static Block cbrPP;
        //private static Block cbrRope;
        //private static Block cbrSafe;
        private static Block cbrScaffolding;
        private static Block cbrSlope;
        private static Block cbrSOE;
        private static Block cbrSOI;
        private static Block cbrSP;
        private static Block cbrSW;
        private static Block cbrSWP;
        private static Block cbrStairs;
        private static Block cbrStaircase;
        //private static Block cbrTorch;
        private static Block cbrWall;
        //private static Block cbrWindow;


        @SubscribeEvent
        public static void onBlocksRegistry(RegistryEvent.Register<Block> blockRegistryEvent) {
            // Register a new Block here
            LOGGER.info("HELLO from Block Registry");
            System.out.println("test test 1 2 3");
            IForgeRegistry<Block> blocks = blockRegistryEvent.getRegistry();
            cbrPlanks = new Block(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrPlanks.setRegistryName(new ResourceLocation("carpentersblocksreborn", "bamboo_planks"));
            blocks.register(cbrPlanks);

            cbrBSlabs = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrBSlabs.setRegistryName(new ResourceLocation("carpentersblocksreborn", "bamboo_slabs"));
            blocks.register(cbrBSlabs);

            cbrBSV = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrBSV.setRegistryName(new ResourceLocation("carpentersblocksreborn", "bamboo_slabs_vertical"));
            blocks.register(cbrBSV);

            cbrBarrier = new BarrierCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrBarrier.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_barrier"));
            blocks.register(cbrBarrier);

            cbrBlock = new Block(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrBlock.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block"));
            blocks.register(cbrBlock);

            cbrBC = new Block(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrBC.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block_collapsible"));
            blocks.register(cbrBC);

            cbrSlabs = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrSlabs.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slabs"));
            blocks.register(cbrSlabs);

            cbrSV = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrSV.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slabs_vertical"));
            blocks.register(cbrSV);

            cbrDS = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrDS.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_daylight_sensor"));
            blocks.register(cbrDS);

            //cbrDoor = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            //cbrDoor.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_door"));
            //blocks.register(cbrDoor);

            cbrGD = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrGD.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_garage_door"));
            blocks.register(cbrGD);

            cbrGate = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrGate.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_gate"));
            blocks.register(cbrGate);

            cbrHatch = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrHatch.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_hatch"));
            blocks.register(cbrHatch);

            cbrLadder = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrLadder.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_ladder"));
            blocks.register(cbrLadder);

            cbrLantern = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrLantern.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_lantern"));
            blocks.register(cbrLantern);

            cbrScaffolding = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrScaffolding.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_scaffolding"));
            blocks.register(cbrScaffolding);

            cbrSlope = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrSlope.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope"));
            blocks.register(cbrSlope);

            cbrSOE = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrSOE.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_oblique_exterior"));
            blocks.register(cbrSOE);

            cbrSOI = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrSOI.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_oblique_interior"));
            blocks.register(cbrSOI);

            cbrSP = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrSP.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_prism"));
            blocks.register(cbrSP);

            cbrSW = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrSW.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_wedge"));
            blocks.register(cbrSW);

            cbrSWP = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrSWP.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_wedge_prism"));
            blocks.register(cbrSWP);

            //cbrSafe = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            //cbrSafe.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_safe"));
            //blocks.register(cbrSafe);

            cbrStairs = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrStairs.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_stairs"));
            blocks.register(cbrStairs);

            cbrStaircase = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrStaircase.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_staircase"));
            blocks.register(cbrStaircase);

            //cbrTorch = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            //cbrTorch.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_torch"));
            //blocks.register(cbrTorch);

            cbrWall = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            cbrWall.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_wall"));
            blocks.register(cbrWall);

            //cbrWindow = new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1));
            //cbrWindow.setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_window"));
            //blocks.register(cbrWindow);

        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            // register a new item here
            LOGGER.info("HELLO from Item Registry");
            IForgeRegistry<Item> items = itemRegistryEvent.getRegistry();
            //items.register(new BlockItem(cbrChisel, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_chisel")));
            items.register(new BlockItem(cbrPlanks, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "bamboo_planks")));
            items.register(new BlockItem(cbrBSlabs, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "bamboo_slabs")));
            items.register(new BlockItem(cbrBSV, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "bamboo_slabs_vertical")));
            items.register(new BlockItem(cbrBarrier, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_barrier")));
            //items.register(new BlockItem(cbrBed, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_bed")));
            items.register(new BlockItem(cbrBlock, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block")));
            items.register(new BlockItem(cbrBC, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_block_collapsible")));
            items.register(new BlockItem(cbrSlabs, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slabs")));
            items.register(new BlockItem(cbrSV, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slabs_vertical")));
            //items.register(new BlockItem(cbrBoat, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_boat")));
            //items.register(new BlockItem(cbrButton, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_button")));
            items.register(new BlockItem(cbrDS, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_daylight_sensor")));
            //items.register(new BlockItem(cbrDoor, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_door")));
            //items.register(new BlockItem(cbrFlowerpot, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_flowerpot")));
            items.register(new BlockItem(cbrGD, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_garage_door")));
            items.register(new BlockItem(cbrGate, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_gate")));
            //items.register(new BlockItem(cbrHammer, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_hammer")));
            items.register(new BlockItem(cbrHatch, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_hatch")));
            //items.register(new BlockItem(cbrIF, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_item_frame")));
            items.register(new BlockItem(cbrLadder, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_ladder")));
            items.register(new BlockItem(cbrLantern, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_lantern")));
            //items.register(new BlockItem(cbrLever, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_lever")));
            //items.register(new BlockItem(cbrMinecart, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_minecart")));
            //items.register(new BlockItem(cbrPP, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_pressure_plate")));
            //items.register(new BlockItem(cbrRope, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_rope")));
            //items.register(new BlockItem(cbrSafe, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_safe")));
            items.register(new BlockItem(cbrScaffolding, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_scaffolding")));
            items.register(new BlockItem(cbrSlope, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope")));
            items.register(new BlockItem(cbrSOE, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_oblique_exterior")));
            items.register(new BlockItem(cbrSOI, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_oblique_interior")));
            items.register(new BlockItem(cbrSP, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_prism")));
            items.register(new BlockItem(cbrSW, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_wedge")));
            items.register(new BlockItem(cbrSWP, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_slope_wedge_prism")));
            items.register(new BlockItem(cbrStairs, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_stairs")));
            items.register(new BlockItem(cbrStaircase, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_staircase")));
            //items.register(new BlockItem(cbrTorch, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_torch")));
            items.register(new BlockItem(cbrWall, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_wall")));
            //items.register(new BlockItem(cbrWindow, new Item.Properties().group(creativeGroup)).setRegistryName(new ResourceLocation("carpentersblocksreborn", "carpenters_window")));
        }

        public static final ItemGroup creativeGroup = new ItemGroup("carpentersblocksreborn") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(RegistryEvents.cbrBlock);
            }
        };

        //@SubscribeEvent
        //public static void registerTE(RegistryEvent.Register<TileEntityType<?>> evt) {
        //    TileEntityType<?> type = TileEntityType.Builder.create(factory, validBlocks).build(null);
        //    type.setRegistryName("carpentersblocksreborn", "carpenters_bed");
        //    evt.getRegistry().register(type);
    }
}