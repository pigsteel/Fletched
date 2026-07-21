package com.github.pigsteel.fletched.world.inventory;

import com.github.pigsteel.fletched.core.MenuTypeRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public class FletchingMenu extends RecipeBookMenu {
	public FletchingMenu(int containerId, Inventory inventory) {
		this(containerId, inventory, ContainerLevelAccess.NULL);
	}

	public FletchingMenu(int containerId, Inventory inventory, final ContainerLevelAccess access) {
		super(MenuTypeRegistry.FLETCHING_TABLE.get(), containerId);
	}

	@Override
	public PostPlaceAction handlePlacement(boolean b, boolean b1, RecipeHolder<?> recipeHolder, ServerLevel serverLevel, Inventory inventory) {
		return null;
	}

	@Override
	public void fillCraftSlotsStackedContents(StackedItemContents stackedItemContents) {

	}

	@Override
	public RecipeBookType getRecipeBookType() {
		//? neoforge {
		return RecipeBookType.valueOf("FLETCHED_FLETCHING");
		//?} fabric {
		/*return RecipeBookType.FLETCHED_FLETCHING;
		*///?}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int i) {
		return null;
	}

	@Override
	public boolean stillValid(Player player) {
		return false;
	}
}
