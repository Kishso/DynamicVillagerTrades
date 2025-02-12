package com.kishso.neoforge;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import com.kishso.DynamicVillagerTradesMod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;

import java.util.ArrayList;


@Mod(DynamicVillagerTradesMod.MOD_ID)
public final class DynamicVillagerTradesModImpl {


    private static ArrayList<PendingDataPack> pendingDataPacks = new ArrayList<>();

    public DynamicVillagerTradesModImpl(IEventBus modEventBus, ModContainer modContainer) {
        // Run our common setup.
        DynamicVillagerTradesMod.init();

        modEventBus.addListener(this::onAddPackFinders);
    }

    public static void AddBuiltInDataPack(String dataPackName, ResourceLocation datapackLocation, boolean enabledByDefault)
    {
        // Fabric expects datapacks to be underneath "resourcepacks/" so for data packs, we append this to our path
        ResourceLocation alteredLocation =
                ResourceLocation.fromNamespaceAndPath(datapackLocation.getNamespace(), "resourcepacks/"+ datapackLocation.getPath());
        pendingDataPacks.add(new PendingDataPack(dataPackName, alteredLocation, enabledByDefault));
    }

    public void onAddPackFinders(AddPackFindersEvent event)
    {
        for(PendingDataPack pack : pendingDataPacks) {
            if(pack.location != null) {
                event.addPackFinders(pack.location, PackType.SERVER_DATA,
                        Component.literal(pack.displayName),
                        PackSource.DEFAULT, false, Pack.Position.BOTTOM);
            }
        }
    }
}
