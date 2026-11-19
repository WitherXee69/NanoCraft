package com.witherxee.nanocraft.transfer.energy;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import net.neoforged.neoforge.transfer.TransferPreconditions;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import org.jspecify.annotations.NonNull;

import java.util.function.BiConsumer;

public class NaniteEnergyHandler implements EnergyHandler, ValueIOSerializable {
    protected int nEnergy;
    protected int capacity;
    protected int maxInsert;
    protected int maxExtract;

    protected final BiConsumer<Integer, Integer> energyChangedCallback;

    private final NaniteEnergySnapshot snapshotJournal = new NaniteEnergySnapshot();

    //Unlimited transfer, starts empty
    public NaniteEnergyHandler(int capacity) {
        this(capacity, capacity);
    }

    //Symmetrical transfer, starts empty
    public NaniteEnergyHandler(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer);
    }

    //Asymmetrical transfer, starts empty (machines/generators)
    public NaniteEnergyHandler(int capacity, int maxInsert, int maxExtract) {
        this(capacity, maxInsert, maxExtract, 0);
    }

    //Asymmetrical transfer, starts with energy (machines/generators that have energy on placement)
    public NaniteEnergyHandler(int capacity, int maxInsert, int maxExtract, int nEnergy) {
        this(capacity, maxInsert, maxExtract, nEnergy, null);
    }

    //Master constructor with energy change callback
    public NaniteEnergyHandler(int capacity, int maxInsert, int maxExtract, int nEnergy, BiConsumer<Integer, Integer> energyChangedCallback) {
        TransferPreconditions.checkNonNegative(capacity);
        TransferPreconditions.checkNonNegative(maxInsert);
        TransferPreconditions.checkNonNegative(maxExtract);
        TransferPreconditions.checkNonNegative(nEnergy);

        this.capacity = capacity;
        this.maxInsert = maxInsert;
        this.maxExtract = maxExtract;
        this.nEnergy = nEnergy;
        this.energyChangedCallback = energyChangedCallback != null
                ? energyChangedCallback
                : (previousAmount, newAmount) -> {
                    // Default implementation does nothing.
                };
    }


    @Override
    public void serialize(ValueOutput output) {
        output.putInt("nEnergy", nEnergy);
    }

    @Override
    public void deserialize(ValueInput input) {
        nEnergy = Math.max(0, input.getIntOr("nEnergy", 0));
    }

    @Override
    public long getAmountAsLong() {
        return this.nEnergy;
    }

    @Override
    public long getCapacityAsLong() {
        return this.capacity;
    }

    @Override
    public int insert(int amount, @NonNull TransactionContext transaction) {
        TransferPreconditions.checkNonNegative(amount);
        int inserted = Math.min(amount, Math.min(this.maxInsert, this.capacity - this.nEnergy));
        if (inserted > 0) {
            snapshotJournal.updateSnapshots(transaction);
            this.nEnergy += inserted;
            return inserted;
        }
        return 0;
    }

    @Override
    public int extract(int amount, @NonNull TransactionContext transaction) {
        snapshotJournal.updateSnapshots(transaction);
        int extracted = Math.min(amount, this.nEnergy);
        if (extracted > 0) {
            this.nEnergy -= extracted;
            return extracted;
        }
        return 0;
    }

    protected void onNEnergyChanged(int previousAmount, int newAmount) {
        // Notify listeners of the change in energy amount
        energyChangedCallback.accept(previousAmount, newAmount);
    }

    private class NaniteEnergySnapshot extends SnapshotJournal<Integer> {

        @Override
        protected Integer createSnapshot() {
            return nEnergy;
        }

        @Override
        protected void revertToSnapshot(Integer snapshot) {
            nEnergy = snapshot;
        }

        @Override
        protected void onRootCommit(Integer originalState) {
            int previousAmount = originalState;
            if (previousAmount != nEnergy) {
                onNEnergyChanged(previousAmount, nEnergy);
            }
        }
    }
}
