package com.witherxee.nanocraft.blocks;

import com.witherxee.nanocraft.blockentities.SolarPanelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SolarPanelBlock extends Block implements EntityBlock {

    /*
     * Approximation of the Blockbench model's physical geometry.
     *
     * Minecraft VoxelShapes are made from axis-aligned boxes, so the
     * inclined panel is represented by short stepped sections.
     * Coordinates use model pixels: 16 pixels = 1 block.
     *
     * The upper panel extends to approximately 21 pixels, matching
     * the model's height.
     */
    private static final VoxelShape SOLAR_PANEL_SHAPE = Shapes.or(
            // Bottom foot / base
            Block.box(4, 0, 5, 12, 1, 15),

            // Rear mounting frame and lower supports
            Block.box(5, 1, 9, 11, 5, 14),
            Block.box(6, 5, 13, 7, 9, 14),
            Block.box(9, 5, 13, 10, 9, 14),
            Block.box(6, 7, 11, 7, 8, 13),
            Block.box(9, 7, 11, 10, 8, 13),
            Block.box(7, 2, 14, 9, 4, 15),
            Block.box(5.5, 0.5, 15, 10.5, 5.5, 16),

            // Central upright support
            Block.box(7, 5, 10, 9, 9, 12),
            Block.box(7, 8, 8.5, 9, 12, 11),

            // Angled braces beneath the panel (axis-aligned approximations)
            Block.box(6, 9, 8, 7, 12, 10),
            Block.box(9, 9, 8, 10, 12, 10),
            Block.box(6.5, 10, 7, 9.5, 13, 9),

            // Panel surface: stepped approximation of the 22.5-degree incline
            Block.box(0, 7, 4.5, 16, 8.5, 5.5),
            Block.box(0, 8.5, 5.5, 16, 10, 6.5),
            Block.box(0, 10, 6.5, 16, 11.5, 7.5),
            Block.box(0, 11.5, 7.5, 16, 13, 8.5),
            Block.box(0, 13, 8.5, 16, 14.5, 9.5),
            Block.box(0, 14.5, 9.5, 16, 16, 10.5),
            Block.box(0, 16, 10.5, 16, 17.5, 11.5),
            Block.box(0, 17.5, 11.5, 16, 19, 12.5),
            Block.box(0, 19, 12.5, 16, 20.5, 13.5)
    );

    public SolarPanelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return SOLAR_PANEL_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return SOLAR_PANEL_SHAPE;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SolarPanelBlockEntity(pos, state);
    }
}
