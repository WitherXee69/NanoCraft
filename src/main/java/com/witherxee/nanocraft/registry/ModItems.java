
package com.witherxee.nanocraft.registry;

import com.witherxee.nanocraft.Nanocraft;
import com.witherxee.nanocraft.items.LavaGenItem;
import com.witherxee.nanocraft.items.SolarPanelItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Nanocraft.MODID);

    public static final DeferredItem<SolarPanelItem> SOLARPANEL =
            ITEMS.registerItem("solarpanel", SolarPanelItem::new);

    public static final DeferredItem<LavaGenItem> LAVAGEN =
            ITEMS.registerItem("lavapowergen", LavaGenItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
