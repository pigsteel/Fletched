package com.github.pigsteel.fletched.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface DrawDuration {
	float fletched$getDrawDuration(ItemStack stack, LivingEntity user);
}
