package com.witherxee.nanocraft.capabilities;

import com.witherxee.nanocraft.Nanocraft;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import org.jspecify.annotations.Nullable;

public final class Capabilities {
    public static final class NEnergy {
        public static final BlockCapability<EnergyHandler, @Nullable Direction> BLOCK = BlockCapability.createSided(create("nanite_energy"), EnergyHandler.class);
        public static final EntityCapability<EnergyHandler, @Nullable Direction> ENTITY = EntityCapability.createSided(create("nanite_energy"), EnergyHandler.class);
        public static final ItemCapability<EnergyHandler, ItemAccess> ITEM = ItemCapability.create(create("nanite_energy"), EnergyHandler.class, ItemAccess.class);

        private NEnergy() {}
    }

    private static Identifier create(String path) {
        return Identifier.fromNamespaceAndPath(Nanocraft.MODID, path);
    }

    private Capabilities() {}
}
