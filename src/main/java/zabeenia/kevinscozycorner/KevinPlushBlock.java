package zabeenia.kevinscozycorner;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;


public class KevinPlushBlock extends Block {

    private static final VoxelShape SHAPE =
            Block.box(3, 0, 3, 13, 9, 13);

    @Override
    protected VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context) {
        return SHAPE;
    }


    public static final IntegerProperty ROTATION =
            IntegerProperty.create("rotation", 0, 7);
    public KevinPlushBlock(BlockBehaviour.Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any().setValue(ROTATION, 0)
        );
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            int currentRotation = state.getValue(ROTATION);
            int nextRotation = (currentRotation + 1) % 8;

            level.setBlock(pos, state.setValue(ROTATION, nextRotation), 3);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        int rotation = (
                RotationSegment.convertToSegment(context.getRotation()) + 1
        ) / 2 % 8;
        return this.defaultBlockState().setValue(ROTATION, rotation);
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }
}