package com.witherxee.nanocraft.registry;

import com.witherxee.nanocraft.Nanocraft;
import com.witherxee.nanocraft.blockentities.SolarPanelBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE_DEFERRED_REGISTER =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Nanocraft.MODID);

    public static final Supplier<BlockEntityType<SolarPanelBlockEntity>> SOLARPANELENTITY =
            BLOCK_ENTITY_TYPE_DEFERRED_REGISTER.register("solarpanel",
                    () -> BlockEntityType.Builder.of(
                            SolarPanelBlockEntity :: new,
                            ModBlocks.SOLARPANEL.get()
                    ).build(null));
}
