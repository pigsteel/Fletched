package com.github.pigsteel.fletched.platform.neoforge;

//? neoforge {

/*import com.github.pigsteel.fletched.Fletched;
import com.github.pigsteel.fletched.world.item.CreativeModeTabModification;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import static com.github.pigsteel.fletched.world.item.Items.*;

@Mod(Fletched.MOD_ID)
public class NeoforgeEntrypoint {

	public NeoforgeEntrypoint(IEventBus eventBus) {
		Fletched.onInitialize();

		ITEMS.register(eventBus);

		//eventBus.register(NeoforgeEventSubscriber.class);
		eventBus.addListener(CreativeModeTabModification::modifyCreativeTabs);
	}
}
*///?}
