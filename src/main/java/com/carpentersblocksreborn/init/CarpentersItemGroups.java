package com.carpentersblocksreborn.init;

import com.carpentersblocksreborn.CarpentersBlocksReborn;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CarpentersItemGroups {
    public static final ItemGroup CARPENTERS_BLOCKS = new ItemGroup(CarpentersBlocksReborn.MOD_ID) {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(CarpentersBlocks.CARPENTERS_BLOCK.get());
        }
    };
}
