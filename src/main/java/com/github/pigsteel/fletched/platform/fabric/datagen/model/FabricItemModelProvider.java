package com.github.pigsteel.fletched.platform.fabric.datagen.model;

//? fabric {
/*import com.github.pigsteel.fletched.world.item.Items;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class FabricItemModelProvider extends FabricModelProvider {
	public FabricItemModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerators) {
		itemModelGenerators.generateBow(Items.SHORTBOW.get());
		itemModelGenerators.generateBow(Items.LONGBOW.get());
		itemModelGenerators.generateCrossbow(Items.HEAVY_CROSSBOW.get());
	}
}

*///?}
