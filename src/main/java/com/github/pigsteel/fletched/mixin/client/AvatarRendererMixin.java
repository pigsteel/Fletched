package com.github.pigsteel.fletched.mixin.client;

import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AvatarRenderer.class)
public class AvatarRendererMixin {
	@Redirect(
			method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"
			)
	)
	private static boolean fletched$recognizeCrossbowItemsStatic(ItemStack instance, Object o) {
		return fletched$recognizeCrossbowItems(instance, (Item) o);
	}

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
