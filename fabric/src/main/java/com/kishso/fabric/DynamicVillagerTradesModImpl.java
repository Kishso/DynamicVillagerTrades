package com.kishso.fabric;

import com.mojang.datafixers.kinds.Monoid;
import net.fabricmc.api.ModInitializer;

import com.kishso.DynamicVillagerTradesMod;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.fabric.impl.resource.loader.ModNioResourcePack;
import net.fabricmc.fabric.impl.resource.loader.ModResourcePackCreator;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.KnownPack;
import net.minecraft.util.Tuple;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static com.kishso.DynamicVillagerTradesMod.MOD_ID;

public final class DynamicVillagerTradesModImpl implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        DynamicVillagerTradesMod.init();
    }

    public static void AddBuiltInDataPack(String dataPackName, ResourceLocation datapackLocation, boolean enabledByDefault)
    {
        ModContainer modContainer = FabricLoader.getInstance().getModContainer(MOD_ID).isPresent()? FabricLoader.getInstance().getModContainer(MOD_ID).get() : null;
        if(modContainer != null) {
            Component displayName = Component.literal(dataPackName);

            boolean success = false;
            if(enabledByDefault) {
                success = ResourceManagerHelper.registerBuiltinResourcePack(datapackLocation, modContainer, displayName, ResourcePackActivationType.DEFAULT_ENABLED);
            }
            else {
                success = ResourceManagerHelper.registerBuiltinResourcePack(datapackLocation, modContainer, displayName, ResourcePackActivationType.NORMAL);
            }

            if(!success)
            {
                throw new AssertionError();
            }
        }
    }
}
