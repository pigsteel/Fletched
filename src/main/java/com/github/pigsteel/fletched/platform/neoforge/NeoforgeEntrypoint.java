package com.github.pigsteel.fletched.platform.neoforge;

//? neoforge {

import com.github.pigsteel.fletched.ModTemplate;
import net.neoforged.fml.common.Mod;

@Mod(ModTemplate.MOD_ID)
public class NeoforgeEntrypoint {

	public NeoforgeEntrypoint() {
		ModTemplate.onInitialize();
	}
}
//?}
