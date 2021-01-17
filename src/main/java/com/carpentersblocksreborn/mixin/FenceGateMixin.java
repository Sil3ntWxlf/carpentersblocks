package com.carpentersblocksreborn.mixin;

import com.carpentersblocksreborn.block.BarrierCarpentersBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FenceGateBlock.class)
public class FenceGateMixin {

    @Inject(at = @At("HEAD"), method = "isWall(Lnet/minecraft/block/BlockState;)Z", cancellable = true)
    private void isWall(BlockState state, CallbackInfoReturnable<Boolean> info) {
        if (state.getBlock() instanceof BarrierCarpentersBlock) {
            info.setReturnValue(true);
        }
    }
}
