package com.witherxee.nanocraft.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.witherxee.nanocraft.registry.ModBlockEntity.SOLARPANELENTITY;

public class SolarPanelBlockEntity extends BlockEntity {
    public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(SOLARPANELENTITY.get(), pos, state);
    }
}
