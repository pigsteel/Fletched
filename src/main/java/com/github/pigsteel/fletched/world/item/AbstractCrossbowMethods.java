package com.github.pigsteel.fletched.world.item;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public interface AbstractCrossbowMethods {
	int fletched$getChargeDuration(final ItemStack crossbow, final LivingEntity user);
}
