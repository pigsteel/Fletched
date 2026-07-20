package com.github.pigsteel.fletched.world.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ShortbowItem extends BowItem {
	public static final int MAX_DRAW_DURATION = 10;

	public ShortbowItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean releaseUsing(final ItemStack itemStack, final Level level, final LivingEntity entity, final int remainingTime) {
		if (entity instanceof Player player) {
			ItemStack projectile = player.getProjectile(itemStack);
			if (projectile.isEmpty()) {
				return false;
			} else {
				int timeHeld = this.getUseDuration(itemStack, entity) - remainingTime;
				float pow = getPowerForTime(timeHeld);
				if ((double)pow < 0.05) {
					return false;
				} else {
					List<ItemStack> firedProjectiles = draw(itemStack, projectile, player);
					if (level instanceof ServerLevel) {
						ServerLevel serverLevel = (ServerLevel)level;
						if (!firedProjectiles.isEmpty()) {
							this.shoot(serverLevel, player, player.getUsedItemHand(), itemStack, firedProjectiles, pow * 2F, 1F, pow >= 0.5F, (LivingEntity)null);
						}
					}

					level.playSound((Entity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 2.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + pow * 0.5F);
					player.awardStat(Stats.ITEM_USED.get(this));
					return true;
				}
			}
		} else {
			return false;
		}
	}

	public static float getPowerForTime(final int timeHeld) {
		float pow = (float)timeHeld / MAX_DRAW_DURATION;
		pow = (pow * pow + pow * 2.0F) / 3.0F;
		if (pow > 1F) {
			pow = 1F;
		}

		return pow;
	}
}
