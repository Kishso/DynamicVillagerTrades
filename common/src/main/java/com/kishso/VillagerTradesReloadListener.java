package com.kishso;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class VillagerTradesReloadListener implements PreparableReloadListener
{

    @Override
    public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, Executor executor, Executor executor2) {
        return CompletableFuture.runAsync(() ->
        {
            for(ResourceLocation resource : resourceManager.listResourceStacks("trades", path -> path.getPath().endsWith(".json")).keySet())
            {
                try {
                    Optional<Resource> digsiteResource = resourceManager.getResource(resource);
                    if(digsiteResource.isPresent()){
                        Resource digsiteRes = digsiteResource.get();
                        InputStream jsonStream = digsiteRes.open();

                        InputStreamReader jsonReader = new InputStreamReader(jsonStream);
                        JsonElement jsonData = JsonParser.parseReader(jsonReader);
                        if(jsonData instanceof JsonObject)
                        {

                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
