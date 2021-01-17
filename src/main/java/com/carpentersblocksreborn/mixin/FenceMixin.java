package com.carpentersblocksreborn.mixin;

import com.carpentersblocksreborn.block.BarrierCarpentersBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.util.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FenceBlock.class)
public class FenceMixin {

    @Inject(at = @At("HEAD"), method = "canConnect(Lnet/minecraft/block/BlockState;ZLnet/minecraft/util/Direction;)Z", cancellable = true)
    public void canConnect(BlockState state, boolean isSideSolid, Direction direction, CallbackInfoReturnable<Boolean> info) {
        if (state.getBlock() instanceof BarrierCarpentersBlock) {
            info.setReturnValue(true);
        }
    }
}
