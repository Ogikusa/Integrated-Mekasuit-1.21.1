package com.ogikusa.mekanismintegrated.registry;

import com.ogikusa.mekanismintegrated.MekanismIntegratedMod;
import mekanism.api.gear.IModuleHelper;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MekanismIntegratedMod.MODID);

    public static final DeferredItem<Item> PRESSURIZED_AIR_MODULE_ITEM =
            ITEMS.register("create_pressurized_air_integration",
                    () -> IModuleHelper.INSTANCE.createModuleItem(ModMekModules::pressurizedAirModuleHolder, new Item.Properties()));

    private ModItems() {}
}
