package com.witherxee.nanocraft.registry;

import com.witherxee.nanocraft.Nanocraft;
import com.witherxee.nanocraft.items.LavaGenItem;
import com.witherxee.nanocraft.items.SolarPanelItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Nanocraft.MODID);

    public static final DeferredItem<SolarPanelItem> SOLARPANEL =
            ITEMS.register("solarpanel",
                    () -> new SolarPanelItem(new Item.Properties()));

    public static final DeferredItem<LavaGenItem> LAVAGEN =
            ITEMS.register("lavapowergen",
                    () -> new LavaGenItem(new Item.Properties()));

    public static void register(IEventBus iEventBus) {
        ITEMS.register(iEventBus);
    }
}
