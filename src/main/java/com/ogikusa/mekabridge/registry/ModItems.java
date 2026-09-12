package com.ogikusa.mekabridge.registry;

import com.ogikusa.mekabridge.MekanismIntegratedMod;
import mekanism.api.gear.IModuleHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MekanismIntegratedMod.MODID);

    public static final DeferredItem<Item> PRESSURIZED_AIR_MODULE_ITEM =
            ITEMS.register("pressurized_air_module",
                    () -> IModuleHelper.INSTANCE.createModuleItem(ModMekModules::pressurizedAirModuleHolder, new Item.Properties().rarity(Rarity.RARE)));

    private ModItems() {
    }
}
