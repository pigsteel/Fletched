package com.github.pigsteel.fletched.world.item;

import com.github.pigsteel.fletched.Fletched;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ChargedProjectiles;
//? neoforge {
/*import net.neoforged.neoforge.registries.DeferredRegister;
*///?}

import java.util.function.Function;
import java.util.function.Supplier;

public class Items {
	//? neoforge {
	/*// OUR NEOFORGE REGISTRY
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Fletched.MOD_ID);
	*///?}

	public static final Supplier<Item> LONGBOW;
	public static final Supplier<Item> SHORTBOW;
	public static final Supplier<Item> HEAVY_CROSSBOW;
	public static final Supplier<Item> DUELING_CROSSBOW;

	// Replacing registryHandle with Supplier because I'm not sure what the id is used for
	public static <T extends Item> Supplier<T> registerItem(String name, Function<Item.Properties, T> itemFactory) {
		//? neoforge {
		/*return ITEMS.registerItem(
				name,
				itemFactory
		);
		*///?} fabric {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Fletched.id(name));
		Identifier id = key.identifier();
		T registered = Registry.register(BuiltInRegistries.ITEM, id, itemFactory.apply(new Item.Properties().setId(key)));
		return () -> registered;
		//?}
	}

	static {
		LONGBOW = registerItem(
				"longbow",
				properties -> new LongbowItem(properties.stacksTo(1).durability(384))
		);
		SHORTBOW = registerItem(
				"shortbow",
				properties -> new ShortbowItem(properties.stacksTo(1).durability(256))
		);
		HEAVY_CROSSBOW = registerItem(
				"heavy_crossbow",
				properties -> new HeavyCrossbowItem(properties.stacksTo(1).durability(600).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY))
		);
		DUELING_CROSSBOW = registerItem(
				"dueling_crossbow",
				properties ->
						new DuelingCrossbowItem(
								properties
								.stacksTo(1)
								.durability(200)
								.component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)
						)
		);
	}

	public static void init() {
		Fletched.LOGGER.debug("Initializing Fletched items!");
	}
}
