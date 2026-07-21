package com.github.pigsteel.fletched.platform.fabric.datagen;

//? fabric {
import com.github.pigsteel.fletched.platform.fabric.datagen.model.ENUSLangProvider;
import com.github.pigsteel.fletched.platform.fabric.datagen.model.FabricItemModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
//? != 1.19.2 {
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
//?}

public class FabricDataGeneratorEntrypoint implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		//? != 1.19.2 {
		final FabricDataGenerator.Pack pack = generator.createPack();
		pack.addProvider((FabricPackOutput output) -> new ModRecipeProvider(output, generator.getRegistries()));
		pack.addProvider(FabricItemModelProvider::new);
		pack.addProvider((i, j) -> new ENUSLangProvider(i, j, "en_us"));
		pack.addProvider((i, j) -> new ENUSLangProvider(i, j, "en_gb"));
		pack.addProvider((i, j) -> new ENUSLangProvider(i, j, "en_ca"));
		pack.addProvider((i, j) -> new ENUSLangProvider(i, j, "en_nz"));
		pack.addProvider((i, j) -> new ENUSLangProvider(i, j, "en_au"));
		//?}
	}

}
//?}
