package com.kishso;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.TickEvent;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.ReloadListenerRegistry;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.entity.EntityTypeTest;

import javax.swing.text.html.parser.Entity;
import java.util.Iterator;
import java.util.List;


public final class DynamicVillagerTradesMod {
    public static final String MOD_ID = "dynamic_villager_trades";

    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static void init() {
        // Write common init code here.

        AddBuiltInDataPack("Vanilla Villager Trades", ResourceLocation.fromNamespaceAndPath(MOD_ID, "vanilla_villager_trades"), true);

        ReloadListenerRegistry.register(PackType.SERVER_DATA, new VillagerTradesReloadListener());

        TickEvent.SERVER_LEVEL_POST.register((ServerLevel serverLevel) -> {
            if(serverLevel.getDayTime() == 0)
            {
                List<? extends Villager> entities = serverLevel.getEntities(EntityType.VILLAGER, (Villager villager) -> {
                    return serverLevel.isLoaded(villager.getOnPos());
                });

                for(Villager villager : entities)
                {

                    villager.setOffers(villager.);
                }

            }
        });

    }

    @ExpectPlatform
    public static void AddBuiltInDataPack(String dataPackName, ResourceLocation dataPackLocation, boolean enabledByDefault)
    {
        throw new AssertionError();
    }
}

