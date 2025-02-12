package com.kishso;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.ReloadListenerRegistry;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;


public final class DynamicVillagerTradesMod {
    public static final String MOD_ID = "dynamic_villager_trades";

    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static void init() {
        // Write common init code here.

        AddBuiltInDataPack("Vanilla Villager Trades", ResourceLocation.fromNamespaceAndPath(MOD_ID, "vanilla_villager_trades"), true);

        ReloadListenerRegistry.register(PackType.SERVER_DATA, new VillagerTradesReloadListener());

    }

    @ExpectPlatform
    public static void AddBuiltInDataPack(String dataPackName, ResourceLocation dataPackLocation, boolean enabledByDefault)
    {
        throw new AssertionError();
    }
}

