package com.ogikusa.mekanismintegrated.compat;

import com.ogikusa.mekanismintegrated.registry.ModMekModules;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import mekanism.api.gear.IModuleHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public final class CreateBacktankCompat {

    private static boolean registered;

    public static void register() {
        if (registered) {
            return;
        }

        registered = true;

        BacktankUtil.addBacktankSupplier(entity -> {
            ItemStack bodyarmor =
                    entity.getItemBySlot(EquipmentSlot.CHEST);

            // Only provide the chestplate when our module is installed
            // and enabled.
            if (IModuleHelper.INSTANCE.getIfEnabled(
                    bodyarmor,
                    ModMekModules.CREATE_PRESSURIZED_AIR_INTEGRATION
            ) == null) {
                return List.of();
            }

            return List.of(bodyarmor);
        });
    }

    private CreateBacktankCompat() {
    }
}