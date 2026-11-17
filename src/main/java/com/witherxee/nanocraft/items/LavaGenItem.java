package com.witherxee.nanocraft.items;

import com.witherxee.nanocraft.registry.ModBlocks;
import net.minecraft.world.item.BlockItem;

public class LavaGenItem extends BlockItem {
    public LavaGenItem(Properties properties){
        super(ModBlocks.LAVAGEN.get(), properties);
    }
}
