
package com.witherxee.nanocraft.registry;

import com.witherxee.nanocraft.Nanocraft;
import com.witherxee.nanocraft.blocks.LavaGenBlock;
import com.witherxee.nanocraft.blocks.SolarPanelBlock;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Nanocraft.MODID);

    public static final DeferredBlock<SolarPanelBlock> SOLARPANEL =
            BLOCKS.registerBlock("solarpanel",
                    SolarPanelBlock::new,
                    properties -> properties
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)
                            .noOcclusion()
            );

    public static final DeferredBlock<LavaGenBlock> LAVAGEN =
            BLOCKS.registerBlock("lavapowergen",
                    LavaGenBlock::new,
                    properties -> properties
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)
            );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
