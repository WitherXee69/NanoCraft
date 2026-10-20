package com.witherxee.nanocraft;

import com.mojang.logging.LogUtils;
import com.witherxee.nanocraft.registry.ModBlocks;
import com.witherxee.nanocraft.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Nanocraft.MODID)
public class Nanocraft {
    public static final String MODID = "nanocraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Nanocraft(IEventBus modEventBus, ModContainer modContainer) {

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        LOGGER.info("NanoCraft loaded!");
    }
}
