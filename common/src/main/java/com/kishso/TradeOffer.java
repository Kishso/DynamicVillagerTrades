package com.kishso;

import com.google.common.collect.Range;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class TradeOffer
{

    private static class TradeItem
    {
        private Item tradeItem;
        private IntRange quantityRange;

        private TradeItem(String itemId, Integer min, Integer max)
        {
            ResourceLocation itemLocation = ResourceLocation.parse(itemId);
            if(itemLocation != null)
            {
                tradeItem = BuiltInRegistries.ITEM.getValue(itemLocation);
            }
            quantityRange = new IntRange(min,max);
        }

        static TradeItem fromJson(JsonObject jsonObject)
        {
            return new TradeItem("minecraft:dirt", 1, 1);
        }
    }

    private TradeItem offerItem;
    private TradeItem wantItem;

    private TradeOffer(TradeItem offer, TradeItem want)
    {
        this.offerItem = offer;
        this.wantItem = want;
    }

    static TradeOffer fromJson(JsonObject jsonObject)
    {

        return new TradeOffer(TradeItem.fromJson(jsonObject), TradeItem.fromJson(jsonObject));
    }

}
