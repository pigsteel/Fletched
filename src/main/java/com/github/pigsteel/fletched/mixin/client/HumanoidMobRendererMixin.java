package com.github.pigsteel.fletched.mixin.client;

import com.github.pigsteel.fletched.world.item.AbstractCrossbowMethods;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HumanoidMobRenderer.class)
public class HumanoidMobRendererMixin {
	@WrapOperation(
			method = "extractHumanoidRenderState",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/item/CrossbowItem;getChargeDuration(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)I"
			)
	)
	private static int smcm$getCrossbowChargeDuration(
			ItemStack instance, LivingEntity user, Operation<Integer> original
	) {
		if (instance.getItem() instanceof AbstractCrossbowMethods crossbow)
			return crossbow.fletched$getChargeDuration(instance, user);
		return original.call(instance, user);
	}
}
