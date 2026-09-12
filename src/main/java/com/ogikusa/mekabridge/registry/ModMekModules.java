package com.ogikusa.mekabridge.registry;

import com.ogikusa.mekabridge.mekmodule.PressurizedAirModule;
import mekanism.api.MekanismAPI;
import mekanism.api.MekanismIMC;
import mekanism.api.gear.ModuleData;
import net.minecraft.core.Holder;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMekModules {
    public static final DeferredRegister<ModuleData<?>> MODULES =
            DeferredRegister.create(
                    MekanismAPI.MODULE_REGISTRY_NAME,
                    "mekabridge"
            );

    public static final DeferredHolder<
            ModuleData<?>,
            ModuleData<PressurizedAirModule>
            > CREATE_PRESSURIZED_AIR_INTEGRATION = MODULES.register(
            "pressurized_air_module",
            () -> new ModuleData<>(ModuleData.ModuleDataBuilder.customInstanced(PressurizedAirModule::new, ModItems.PRESSURIZED_AIR_MODULE_ITEM))
    );

    public static void sendMekanismIMC(InterModEnqueueEvent _event) {
        MekanismIMC.addMekaSuitBodyarmorModules(CREATE_PRESSURIZED_AIR_INTEGRATION);
    }

    public static Holder<ModuleData<?>> pressurizedAirModuleHolder() {
        return CREATE_PRESSURIZED_AIR_INTEGRATION;
    }

    private ModMekModules() {
    }
}
