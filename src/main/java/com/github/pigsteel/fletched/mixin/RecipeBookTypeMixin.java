package com.github.pigsteel.fletched.mixin;

import com.github.pigsteel.fletched.Fletched;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;

//? fabric {
@Mixin(RecipeBookType.class)
enum RecipeBookTypeMixin {
	FLETCHED_FLETCHING
}
//?} neoforge {
/*// Fakeout
@Mixin(Fletched.class)
public class RecipeBookTypeMixin {

}
*///?}
