package com.carpentersblocksreborn.init;

import com.carpentersblocksreborn.CarpentersBlocksReborn;
import com.carpentersblocksreborn.block.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CarpentersBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CarpentersBlocksReborn.MOD_ID);

    public static final RegistryObject<Block> BAMBOO_PLANKS = BLOCKS.register("bamboo_planks", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> BAMBOO_SLABS = BLOCKS.register("bamboo_slabs", () -> new BambooSlabsBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> BAMBOO_SLABS_VERTICAL = BLOCKS.register("bamboo_slabs_vertical", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> BAMBOO_STAIRS = BLOCKS.register("bamboo_stairs", () -> new BambooStairsBlock(BAMBOO_PLANKS.get()::getDefaultState, AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_BARRIER = BLOCKS.register("carpenters_barrier", () -> new FenceCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_BUTTON = BLOCKS.register("carpenters_button", () -> new ButtonCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_BLOCK = BLOCKS.register("carpenters_block", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_BLOCK_COLLAPSIBLE = BLOCKS.register("carpenters_block_collapsible", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_CARPET = BLOCKS.register("carpenters_carpet", () -> new CarpetCarpentersBlock(AbstractBlock.Properties.create(Material.WOOL).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_SLABS = BLOCKS.register("carpenters_slabs", () -> new SlabsCarpentersBlock(CARPENTERS_BLOCK.get().getDefaultState(), AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_SLABS_VERTICAL = BLOCKS.register("carpenters_slabs_vertical", () -> new VerticalSlabsCarpentersBlock(CARPENTERS_BLOCK.get()::getDefaultState, AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_DAYLIGHT_SENSOR = BLOCKS.register("carpenters_daylight_sensor", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_DOOR = BLOCKS.register("carpenters_door", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_GARAGE_DOOR = BLOCKS.register("carpenters_garage_door", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_GATE = BLOCKS.register("carpenters_gate", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_HATCH = BLOCKS.register("carpenters_hatch", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_LADDER = BLOCKS.register("carpenters_ladder", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_LANTERN = BLOCKS.register("carpenters_lantern", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_LEVER = BLOCKS.register("carpenters_lever", () -> new LeverCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    //public static final RegistryObject<Block> CARPENTERS_PRESSURE_PLATE = BLOCKS.register("carpenters_pressure_plate", () -> new PressurePlateCarpentersBlock(CARPENTERS_BLOCK.get().getDefaultState(), AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_SCAFFOLDING = BLOCKS.register("carpenters_scaffolding", () -> new ScaffoldingCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    //public static final RegistryObject<Block> CARPENTERS_SLOPE = BLOCKS.register("carpenters_slope", () -> new SlopeCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_SLOPE_OBLIQUE_INTERIOR = BLOCKS.register("carpenters_slope_oblique_interior", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_SLOPE_PRISM = BLOCKS.register("carpenters_slope_prism", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_SLOPE_WEDGE = BLOCKS.register("carpenters_slope_wedge", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_SLOPE_WEDGE_PRISM = BLOCKS.register("carpenters_slope_wedge_prism", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_SAFE = BLOCKS.register("carpenters_safe", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_STAIRS = BLOCKS.register("carpenters_stairs", () -> new StairsCarpentersBlock(CARPENTERS_BLOCK.get()::getDefaultState, AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_STAIRCASE = BLOCKS.register("carpenters_staircase", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_TORCH = BLOCKS.register("carpenters_torch", () -> new TorchCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WOOD), ParticleTypes.FLAME));

    public static final RegistryObject<Block> CARPENTERS_WALL = BLOCKS.register("carpenters_wall", () -> new WallCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CARPENTERS_WINDOW = BLOCKS.register("carpenters_window", () -> new WaterloggableCarpentersBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(1).harvestTool(ToolType.AXE).hardnessAndResistance(1).sound(SoundType.WOOD)));
}
