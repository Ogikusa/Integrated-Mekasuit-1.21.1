package com.ogikusa.mekanismintegrated;

import com.mojang.logging.LogUtils;
import com.ogikusa.mekanismintegrated.compat.CreateBacktankCompat;
import com.ogikusa.mekanismintegrated.registry.ModItems;
import com.ogikusa.mekanismintegrated.registry.ModMekModules;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MekanismIntegratedMod.MODID)
public class MekanismIntegratedMod {
    public static final String MODID = "ogikusa_mekanism_integrated";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MOD_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ogikusa_mekanism_integrated")) //The language key for the title of your CreativeModeTab
            .icon(() -> new ItemStack(Items.DIAMOND))
            .displayItems((parameters, output) -> ModItems.ITEMS.getEntries().forEach(entry -> output.accept(entry.get()))).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public MekanismIntegratedMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModMekModules.MODULES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        // add module to mekanism
        modEventBus.addListener(ModMekModules::sendMekanismIMC);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(CreateBacktankCompat::register);
    }
}
