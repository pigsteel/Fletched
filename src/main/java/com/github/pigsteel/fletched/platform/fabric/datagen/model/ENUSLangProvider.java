package com.github.pigsteel.fletched.platform.fabric.datagen.model;

//? fabric {
/*import com.github.pigsteel.fletched.world.item.Items;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ENUSLangProvider extends FabricLanguageProvider {
	public ENUSLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup, String code) {
		super(packOutput, code, registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
		translationBuilder.add(Items.LONGBOW.get(), "Longbow");
		translationBuilder.add(Items.SHORTBOW.get(), "Shortbow");
		translationBuilder.add(Items.HEAVY_CROSSBOW.get(), "Heavy Crossbow");
	}
}
*///?}
