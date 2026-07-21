package com.github.pigsteel.fletched.world.item;

import com.github.pigsteel.fletched.Fletched;
//? < 26.2 {
/*import net.minecraft.advancements.CriteriaTriggers;
*///?} >= 26.2 {
import net.minecraft.advancements.triggers.CriteriaTriggers;
//?}
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

// Mojang statically codes crossbow, makes local programmer duplicate 100 methods
public class HeavyCrossbowItem extends CrossbowItem implements AbstractCrossbowMethods {
	public HeavyCrossbowItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		ChargedProjectiles chargedProjectiles = (ChargedProjectiles)itemStack.get(DataComponents.CHARGED_PROJECTILES);
		if (chargedProjectiles != null && !chargedProjectiles.isEmpty()) {
			this.performShooting(level, player, hand, itemStack, getShootingPower(chargedProjectiles), 1.0F, (LivingEntity)null);
			Fletched.LOGGER.info(String.valueOf(itemStack.getComponentsPatch()));
			return InteractionResult.CONSUME;
		} else if (!player.getProjectile(itemStack).isEmpty()) {
			this.startSoundPlayed = false;
			this.midLoadSoundPlayed = false;
			player.startUsingItem(hand);
			return InteractionResult.CONSUME;
		} else {
			return InteractionResult.FAIL;
		}
	}

	@Override
	public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int remainingTime) {
		int timeHeld = this.getUseDuration(itemStack, entity) - remainingTime;
		return getPowerForTime(timeHeld, itemStack, entity) >= 1.0F && isCharged(itemStack);
	}

	public int fletched$getChargeDuration(final ItemStack crossbow, final LivingEntity user) {
		float duration = EnchantmentHelper.modifyCrossbowChargingTime(crossbow, user, 2.5F);
		return Mth.floor(duration * 20.0F);
	}

	private float getPowerForTime(int timeHeld, ItemStack itemStack, LivingEntity holder) {
		float pow = (float)timeHeld / (float)fletched$getChargeDuration(itemStack, holder);
		if (pow > 1.0F) {
			pow = 1.0F;
		}

		return pow;
	}

	private static float getShootingPower(ChargedProjectiles projectiles) {
		return 2.0F * (projectiles.contains(Items.FIREWORK_ROCKET) ? 1.6F : 3.15F);
	}

	@Override
	public void performShooting(final Level level, final LivingEntity shooter, final InteractionHand hand, final ItemStack weapon, final float power, final float uncertainty, final @Nullable LivingEntity targetOverride) {
		if (level instanceof ServerLevel serverLevel) {
			ChargedProjectiles charged = (ChargedProjectiles)weapon.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
			if (charged != null && !charged.isEmpty()) {
				this.shoot(serverLevel, shooter, hand, weapon, charged.itemCopies(), power, uncertainty, shooter instanceof Player, targetOverride);
				if (shooter instanceof ServerPlayer) {
					ServerPlayer player = (ServerPlayer)shooter;
					CriteriaTriggers.SHOT_CROSSBOW.trigger(player, weapon);
					player.awardStat(Stats.ITEM_USED.get(weapon.getItem()));
				}

			}
		}
	}

	@Override
	public void onUseTick(final Level level, final LivingEntity entity, final ItemStack itemStack, final int ticksRemaining) {
		if (!level.isClientSide()) {
			CrossbowItem.ChargingSounds sounds = getChargingSounds(itemStack);
			float tickPercent = (float)(itemStack.getUseDuration(entity) - ticksRemaining) / fletched$getChargeDuration(itemStack, entity);
			if (tickPercent < 0.2F) {
				this.startSoundPlayed = false;
				this.midLoadSoundPlayed = false;
			}

			if (tickPercent >= 0.2F && !this.startSoundPlayed) {
				this.startSoundPlayed = true;
				sounds.start()
						.ifPresent(
								sound -> level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), (SoundEvent)sound.value(), SoundSource.PLAYERS, 0.5F, 0.5F)
						);
			}

			if (tickPercent >= 0.5F && !this.midLoadSoundPlayed) {
				this.midLoadSoundPlayed = true;
				sounds.mid()
						.ifPresent(
								sound -> level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), (SoundEvent)sound.value(), SoundSource.PLAYERS, 0.5F, 0.5F)
						);
			}

			if (tickPercent >= 1.0F && !isCharged(itemStack) && tryLoadProjectiles(entity, itemStack)) {
				sounds.end()
						.ifPresent(
								sound -> level.playSound(
										null,
										entity.getX(),
										entity.getY(),
										entity.getZ(),
										(SoundEvent)sound.value(),
										entity.getSoundSource(),
										1.0F,
										0.5F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F
								)
						);
			}
		}
	}
}
