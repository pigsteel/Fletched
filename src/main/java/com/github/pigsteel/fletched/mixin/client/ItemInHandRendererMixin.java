package com.github.pigsteel.fletched.mixin.client;

import com.github.pigsteel.fletched.world.item.DrawDuration;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
	@Shadow
	@Final
	private Minecraft minecraft;

	//? >= 26.2 {
	@ModifyExpressionValue(
			method = "submitArmWithItem(Lnet/minecraft/client/player/AbstractClientPlayer;FFLnet/minecraft/world/InteractionHand;FLnet/minecraft/world/item/ItemStack;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V",
			at = @At(
					value = "CONSTANT",
					args = "floatValue=20.0"
			)
	)
	//?} < 26.2 {
	/*@ModifyExpressionValue(
			method = "renderArmWithItem",
			at = @At(
					value = "CONSTANT",
					args = "floatValue=20.0"
			)
	)
	*///?}
	private float fletched$modifyBowDrawDuration(float originalDuration) {
		LocalPlayer player = this.minecraft.player;
		assert player != null;

		ItemStack stack = player.getUseItem();

		if (stack.getItem() instanceof DrawDuration bow) {
			return Math.max(
					1.0F,
					bow.fletched$getDrawDuration(stack, player)
			);
		}

		return originalDuration;
	}

	@Redirect(
			method = {
					"evaluateWhichHandsToRender",
					"selectionUsingItemWhileHoldingBowLike",
					"isChargedCrossbow"
			},
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"
			)
	)
	private static boolean fletched$recognizeCrossbowItemsStatic(
			ItemStack instance, Object o
	) {
		return fletched$recognizeCrossbowItems(instance, (Item) o);
	}

	//? fabric {
	/*@Redirect(
			//? >= 26.2 {
			/^method = "submitArmWithItem",
			^///?} < 26.2 {
			method = "renderArmWithItem",
			//?}
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"
			)
	)
	private boolean fletched$recognizeCrossbowItemsInstance(
			ItemStack instance, Object o
	) {
		return fletched$recognizeCrossbowItems(instance, (Item) o);
	}
	*///?}

	@Unique
	private static boolean fletched$recognizeCrossbowItems(
			ItemStack stack,
			Item expectedItem
	) {
		if (expectedItem == Items.CROSSBOW) {
			return stack.getItem() instanceof CrossbowItem;
		} else if (expectedItem == Items.BOW) {
			return stack.getItem() instanceof BowItem;
		}

		return stack.is(expectedItem);
	}
}
