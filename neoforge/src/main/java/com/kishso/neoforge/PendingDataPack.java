package com.kishso.neoforge;

import net.minecraft.resources.ResourceLocation;

public class PendingDataPack
{
    public final String displayName;
    public final ResourceLocation location;
    public final boolean enabledByDefault;

    PendingDataPack(String name, ResourceLocation loc, boolean enabledByDefault)
    {
        this.displayName = name;
        this.location = loc;
        this.enabledByDefault = enabledByDefault;
    }

}
