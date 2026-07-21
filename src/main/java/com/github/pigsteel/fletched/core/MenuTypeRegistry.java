package com.github.pigsteel.fletched.core;

import com.github.pigsteel.fletched.Fletched;
import com.github.pigsteel.fletched.world.inventory.FletchingMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
//? neoforge {
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
//?} fabric {

//?}

import java.util.function.Supplier;

public class MenuTypeRegistry {
	//? neoforge {
	public static final DeferredRegister<MenuType<? extends AbstractContainerMenu>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, Fletched.MOD_ID);
	//?}

	public static final Supplier<MenuType<FletchingMenu>> FLETCHING_TABLE;


	static {
		FLETCHING_TABLE =
		//? neoforge {
				MENUS.register("fletching", () -> new MenuType<>(FletchingMenu::new, FeatureFlags.DEFAULT_FLAGS));
		//?} fabric {
				/*() -> Registry.register(
						BuiltInRegistries.MENU,
						Identifier.fromNamespaceAndPath(Fletched.MOD_ID, "fletching"),
						new MenuType<>(FletchingMenu::new, FeatureFlags.DEFAULT_FLAGS)
				);
		*///?}
	}
}
