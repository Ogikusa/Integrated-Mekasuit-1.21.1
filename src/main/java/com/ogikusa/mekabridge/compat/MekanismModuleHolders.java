package com.ogikusa.mekabridge.compat;

import mekanism.api.MekanismAPI;
import mekanism.api.gear.ModuleData;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MekanismModuleHolders {
    public static final DeferredHolder<ModuleData<?>, ModuleData<?>>
            ELECTROLYTIC_BREATHING_UNIT = DeferredHolder.create(
            MekanismAPI.MODULE_REGISTRY_NAME,
            ResourceLocation.fromNamespaceAndPath(
                    "mekanism",
                    "electrolytic_breathing_unit"
            )
    );
}
