package com.github.pigsteel.fletched.world.item;
//? neoforge {
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
//?} fabric {
/*import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.Event;
*///?}

import static net.minecraft.world.item.Items.*;
import static com.github.pigsteel.fletched.world.item.Items.*;
import static net.minecraft.world.item.CreativeModeTabs.*;

public final class CreativeModeTabModification {
	//? neoforge {
	@SubscribeEvent
	public static void modifyCreativeTabs(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() != COMBAT) {
			return;
		}

		event.insertBefore(
				BOW.getDefaultInstance(),
				SHORTBOW.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
		);

		event.insertAfter(
				BOW.getDefaultInstance(),
				LONGBOW.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
		);

		event.insertAfter(
				CROSSBOW.getDefaultInstance(),
				HEAVY_CROSSBOW.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
		);
	}
	//?} fabric {
	/*public static void modifyCreativeTabs() {
		Event<CreativeModeTabEvents.ModifyOutput> event = CreativeModeTabEvents.modifyOutputEvent(net.minecraft.world.item.CreativeModeTabs.COMBAT);
		event.register(
				entries -> {
					entries.insertAfter(BOW, LONGBOW::get);
					entries.insertBefore(BOW, SHORTBOW::get);
					entries.insertAfter(CROSSBOW, HEAVY_CROSSBOW.get());
				});
	}
	*///?}
}
