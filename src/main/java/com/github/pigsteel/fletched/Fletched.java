package com.github.pigsteel.fletched;

import com.github.pigsteel.fletched.platform.Platform;

import com.github.pigsteel.fletched.world.item.Items;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
/*import com.github.pigsteel.fletched.platform.fabric.FabricPlatform;
*///?} neoforge {
import com.github.pigsteel.fletched.platform.neoforge.NeoforgePlatform;
 //?}

@SuppressWarnings("LoggingSimilarMessage")
public class Fletched {

	public static final String MOD_ID = /*$ mod_id*/ "fletched";
	public static final String MOD_VERSION = /*$ mod_version*/ "0.1.0-alpha";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Fletched!";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
		LOGGER.info("Initializing {} on {}", MOD_ID, Fletched.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
		Items.init();
	}

	public static void onInitializeClient() {
		LOGGER.info("Initializing {} Client on {}", MOD_ID, Fletched.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		/*return new FabricPlatform();
		*///?} neoforge {
		return new NeoforgePlatform();
		//?}
	}

	public static Identifier id(String path) {
		//? > 1.19.2 {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
		 //?} <= 1.19.2 {
		/*return new Identifier(MOD_ID, path);
		*///?}
	}

	private static Identifier id(String namespace, String path) {
		//? > 1.19.2 {
		return Identifier.fromNamespaceAndPath(namespace, path);
		 //?} <= 1.19.2 {
		/*return new Identifier(namespace, path);
		*///?}
	}
}
