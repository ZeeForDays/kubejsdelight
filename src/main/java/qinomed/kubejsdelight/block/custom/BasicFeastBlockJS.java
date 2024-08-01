package qinomed.kubejsdelight.block.custom;

import dev.latvian.mods.kubejs.block.BlockBuilder;
import dev.latvian.mods.kubejs.block.KubeJSBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class BasicFeastBlockJS extends FeastBlock {
    private int servings;
    public List<ResourceLocation> servingsList;
    public VoxelShape shape;

    public BasicFeastBlockJS(FeastBlockBuilder builder, int servings, List<ResourceLocation> servingsList) {
        super(builder.createProperties(), () -> ForgeRegistries.ITEMS.getValue(builder.servingsList.get(0)), builder.hasLeftovers);
        this.servings = servings;
        this.servingsList = servingsList;
        shape = BlockBuilder.createShape(builder.customShape);
        if (this.properties instanceof KubeJSBlockProperties kjsProperties && kjsProperties.blockBuilder instanceof FeastBlockBuilder feastBlockBuilder) {
            this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(feastBlockBuilder.SERVINGS, servings));
        }
    }

    public BasicFeastBlockJS(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
    }

    @Override
    public IntegerProperty getServingsProperty() {
        if (properties instanceof KubeJSBlockProperties kjsProperties && kjsProperties.blockBuilder instanceof FeastBlockBuilder feastBuilder) {
            return feastBuilder.SERVINGS;
        }

        return null;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        if (properties instanceof KubeJSBlockProperties kp) {
            for (var property : kp.blockBuilder.blockStateProperties) {
                builder.add(property);
            }
            kp.blockBuilder.blockStateProperties = Collections.unmodifiableSet(kp.blockBuilder.blockStateProperties);
        }
    }

    @Override
    public int getMaxServings() {
        return servings;
    }

    public ItemStack getServingItem(BlockState state) {
        int serving = this.getMaxServings() - state.getValue(this.getServingsProperty());
        if (serving > this.servingsList.size() - 1)
            return new ItemStack(ForgeRegistries.ITEMS.getValue(this.servingsList.get(this.servingsList.size()-1)));
        else
            return new ItemStack(ForgeRegistries.ITEMS.getValue(this.servingsList.get(serving)));
    }
}
