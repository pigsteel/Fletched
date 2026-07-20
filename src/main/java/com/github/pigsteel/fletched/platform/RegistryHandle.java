package com.github.pigsteel.fletched.platform;

import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

public interface RegistryHandle<T> extends Supplier<T> {
	Identifier id();
}
