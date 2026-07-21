package com.github.pigsteel.fletched.mixin.client;

import com.github.pigsteel.fletched.world.item.DrawDuration;
import com.github.pigsteel.fletched.world.item.ZoomModifier;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin {
    @Redirect(
            method = "getFieldOfViewModifier(ZF)F",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z",
                    ordinal = 0
            )
    )
    private boolean fletched$recognizeCustomBows(
			ItemStack stack, Object o
    ) {
        return stack.is((Item) o)
                || stack.getItem() instanceof DrawDuration;
    }

    @ModifyConstant(
            method = "getFieldOfViewModifier(ZF)F",
            constant = @Constant(floatValue = 20.0F)
    )
    private float fletched$useActualDrawDuration(float original) {
        AbstractClientPlayer player =
                (AbstractClientPlayer) (Object) this;

        ItemStack stack = player.getUseItem();

        if (stack.getItem() instanceof DrawDuration bow) {
            return bow.fletched$getDrawDuration(stack, player);
        }

        return original;
    }

	@ModifyExpressionValue(
			method = "getFieldOfViewModifier(ZF)F",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/util/Mth;square(F)F"
			)
	)
	private float fletched$applyZoomModifier(float squaredScale) {
		AbstractClientPlayer player =
				(AbstractClientPlayer) (Object) this;

		ItemStack stack = player.getUseItem();

		if (stack.getItem() instanceof ZoomModifier zoomModifier) {
			float multiplier = Math.max(
					0.0F,
					zoomModifier.fletched$getZoomModifier(stack, player)
			);

			return squaredScale * multiplier;
		}

		return squaredScale;
	}
}
