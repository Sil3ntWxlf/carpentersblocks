package com.carpentersblocksreborn.init;

import com.carpentersblocksreborn.CarpentersBlocksReborn;
import com.carpentersblocksreborn.item.CarpentersChisel;
import com.carpentersblocksreborn.item.CarpentersHammer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CarpentersItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CarpentersBlocksReborn.MOD_ID);

    public static final RegistryObject<Item> CARPENTERS_HAMMER = ITEMS.register("carpenters_hammer", () -> new CarpentersHammer(new Item.Properties().group(CarpentersItemGroups.CARPENTERS_BLOCKS)));

    public static final RegistryObject<Item> CARPENTERS_CHISEL = ITEMS.register("carpenters_chisel", () -> new CarpentersChisel(new Item.Properties().group(CarpentersItemGroups.CARPENTERS_BLOCKS)));
}
