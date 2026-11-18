package com.witherxee.nanocraft.registry;

import com.witherxee.nanocraft.capabilities.Capabilities;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class ModCapabilities {

    public static void register(IEventBus eventBus) {
        eventBus.addListener(ModCapabilities::registerCapabilities);
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.NEnergy.BLOCK,
                ModBlockEntity.SOLARPANELENTITY.get(),
                (blockEntity, side) -> blockEntity.getEnergyHandler()
        );
    }
}