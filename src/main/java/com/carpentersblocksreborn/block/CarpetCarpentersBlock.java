package com.carpentersblocksreborn.block;

import net.minecraft.block.CarpetBlock;
import net.minecraft.item.DyeColor;

public class CarpetCarpentersBlock extends CarpetBlock implements CarpentersBlock{
    public CarpetCarpentersBlock(DyeColor colorIn, Properties properties) {
        super(colorIn, properties);
    }
}
