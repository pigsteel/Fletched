package com.github.pigsteel.fletched.world.level.block;

import com.github.pigsteel.fletched.world.inventory.FletchingMenu;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.SmithingTableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FletchingTableBlock extends CraftingTableBlock {
	public static final MapCodec<SmithingTableBlock> CODEC = simpleCodec(SmithingTableBlock::new);
	private static final Component CONTAINER_TITLE = Component.translatable("container.upgrade");

	public FletchingTableBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MenuProvider getMenuProvider(final BlockState state, final Level level, final BlockPos pos) {
		return new SimpleMenuProvider(
				(containerId, inventory, player) -> new FletchingMenu(containerId, inventory, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE
		);
	}
}
