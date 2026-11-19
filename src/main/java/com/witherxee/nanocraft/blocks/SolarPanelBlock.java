
package com.witherxee.nanocraft.blocks;

import com.witherxee.nanocraft.blockentities.SolarPanelBlockEntity;
import com.witherxee.nanocraft.capabilities.Capabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.NonNull;

public class SolarPanelBlock extends Block implements EntityBlock {

    public SolarPanelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SolarPanelBlockEntity(pos, state);
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        EnergyHandler energyHandler = level.getCapability(Capabilities.NEnergy.BLOCK, pos, hitResult.getDirection());
        if (energyHandler != null) {
            if (!level.isClientSide()) {
                try (Transaction transaction = Transaction.open(null)) {
                    int received = energyHandler.extract(100, transaction);
                    if (received > 0) {
                        transaction.commit();
                    }
                }
                long energy = energyHandler.getAmountAsLong();
                player.sendSystemMessage(Component.literal("Energy: " + energy));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
