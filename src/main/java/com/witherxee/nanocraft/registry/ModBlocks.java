package com.witherxee.nanocraft.registry;

import com.witherxee.nanocraft.Nanocraft;
import com.witherxee.nanocraft.blocks.LavaGenBlock;
import com.witherxee.nanocraft.blocks.SolarPanelBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Nanocraft.MODID);

    public static final DeferredBlock<SolarPanelBlock> SOLARPANEL =
            BLOCKS.register("solarpanel",
                    () -> new SolarPanelBlock(BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<LavaGenBlock> LAVAGEN =
            BLOCKS.register("lavapowergen",
                    () -> new LavaGenBlock(BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static void register(IEventBus iEventBus) {
        BLOCKS.register(iEventBus);
    }
}
