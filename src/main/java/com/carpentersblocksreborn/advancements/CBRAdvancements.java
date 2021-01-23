package com.carpentersblocksreborn.advancements;

import net.minecraft.advancements.CriteriaTriggers;

public class CBRAdvancements {
    public static final HasAdvancementTrigger ADVANCEMENT_UNLOCKED = CriteriaTriggers.register(new HasAdvancementTrigger());
    public static final CraftHammerTrigger CRAFT_CBR_HAMMER = CriteriaTriggers.register(new CraftHammerTrigger());
    public static final CraftChiselTrigger CRAFT_CBR_CHISEL = CriteriaTriggers.register(new CraftChiselTrigger());
    public static final PlaceCBRBlockTrigger PLACE_CBR_BLOCK = CriteriaTriggers.register(new PlaceCBRBlockTrigger());

    public static void init() {}
}
