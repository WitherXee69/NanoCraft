package com.witherxee.nanocraft.blockentities;

import com.witherxee.nanocraft.transfer.energy.NaniteEnergyHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import static com.witherxee.nanocraft.registry.ModBlockEntity.SOLARPANELENTITY;

public class SolarPanelBlockEntity extends BlockEntity {
    public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(SOLARPANELENTITY.get(), pos, state);
    }

    private final NaniteEnergyHandler solarEnergyHandler = new NaniteEnergyHandler(10000, 100);

    public NaniteEnergyHandler getEnergyHandler() {
        return solarEnergyHandler;
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        solarEnergyHandler.deserialize(input);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        solarEnergyHandler.serialize(output);
    }
}
