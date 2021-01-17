package com.carpentersblocksreborn.mixin;

import com.carpentersblocksreborn.block.WallCarpentersBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.util.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WallBlock.class)
public class WallBlockMixin {

    @Inject(at = @At("HEAD"), method = "shouldConnect(Lnet/minecraft/block/BlockState;ZLnet/minecraft/util/Direction;)Z", cancellable = true)
    private void shouldConnect(BlockState state, boolean sideSolid, Direction direction, CallbackInfoReturnable<Boolean> info) {
        if (state.getBlock() instanceof WallCarpentersBlock) {
            info.setReturnValue(true);
        }
    }
}
