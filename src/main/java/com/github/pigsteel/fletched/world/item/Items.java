package com.github.pigsteel.fletched.world.item;

import com.github.pigsteel.fletched.Fletched;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class Items {
	//? neoforge {
	// OUR NEOFORGE REGISTRY
	public static DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, "fletched");
	//?}
	public static Supplier<Item> LONGBOW = registerItem(
		ResourceKey.create(BuiltInRegistries.ITEM.key(), Fletched.id("longbow")),
			LongbowItem::new,
		new Properties().stacksTo(1).durability(384)
	);

	private static Supplier<Item> registerItem(final ResourceKey<Item> id, final Function<Properties, Item> itemFactory, final Properties properties) {
		Item item = (Item)itemFactory.apply(properties.setId(id));
		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, item);
		}

		return Registry.register(BuiltInRegistries.ITEM, id, item);
	}

	private static void method() {
		ITEMS.register("longbow", LONGBOW);
	}
}
