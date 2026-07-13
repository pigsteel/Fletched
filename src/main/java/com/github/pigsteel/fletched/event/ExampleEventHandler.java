package com.github.pigsteel.fletched.event;

import com.github.pigsteel.fletched.Fletched;
import net.minecraft.server.level.ServerPlayer;

import java.util.Objects;

public class ExampleEventHandler {

	public static void onPlayerHurt(ServerPlayer player) {
		//? if > 1.19.2 {
		// MinecraftServer.pvp is private... only here to test ATs/AWs
		//? if < 26.1.2
		//boolean pvp = Objects.requireNonNull(player.getServer()).pvp;
		//? if >= 26.1.2
		boolean pvp = Objects.requireNonNull(player.level()).isPvpAllowed();
		if (pvp) {
			Fletched.LOGGER.info("{} took damage. PVP is allowed.", player.getDisplayName());
		} else {
			Fletched.LOGGER.info("{} took damage. PVP is disallowed.", player.getDisplayName());
		}
		//?}
	}
}
