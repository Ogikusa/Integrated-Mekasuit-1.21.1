package com.ogikusa.mekabridge.mekmodule;

import com.ogikusa.mekabridge.Config;
import com.ogikusa.mekabridge.compat.MekanismModuleHolders;
import com.simibubi.create.AllDataComponents;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.gear.IModuleContainer;
import mekanism.api.gear.IModuleHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jetbrains.annotations.NotNull;

public final class PressurizedAirModule
        implements ICustomModule<PressurizedAirModule> {

    @Override
    public void tickServer(
            IModule<PressurizedAirModule> module,
            @NotNull IModuleContainer moduleContainer,
            @NotNull ItemStack stack,
            @NotNull Player player
    ) {
        if (!module.isEnabled()) {
            return;
        }

        int currentAir = BacktankUtil.getAir(stack);
        int maximumAir = 600;

        if (currentAir >= maximumAir) {
            // 内部の Create の空気が満たされている場合は消費しません
            return;
        }

        // 水中かつ電気分解呼吸ユニットが無い場合は空気を補充することができません
        if (player.isEyeInFluidType(NeoForgeMod.WATER_TYPE.value()) && !hasElectrolyticBreathing(player)) {
            return;
        }

        long energyPerAir = Config.PRESSURIZED_AIR_MODULE_ENERGY_PER_AIR.get();
        // 電力を消費して内部的な Create の空気を満たします
        long energyUsed =
                module.useEnergy(player, stack, energyPerAir);

        if (energyUsed == energyPerAir) {
            stack.set(
                    AllDataComponents.BACKTANK_AIR,
                    Math.min(currentAir + 1, maximumAir)
            );
        }
    }

    private static boolean hasElectrolyticBreathing(
            LivingEntity entity
    ) {
        ItemStack helmet =
                entity.getItemBySlot(EquipmentSlot.HEAD);

        IModuleContainer container =
                IModuleHelper.INSTANCE.getModuleContainer(helmet);

        return container != null
                && container.hasEnabled(MekanismModuleHolders.ELECTROLYTIC_BREATHING_UNIT);
    }
}