package qinomed.kubejsdelight.block.custom;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.List;
import java.util.function.Supplier;

public class BasicFeastBlockJS extends FeastBlock {
    private int servings;
    public List<ResourceLocation> servingsList;

    public BasicFeastBlockJS(FeastBlockBuilder builder, int servings, List<ResourceLocation> servingsList) {
        super(builder.createProperties(), () -> ForgeRegistries.ITEMS.getValue(builder.servingsList.get(0)), builder.hasLeftovers);
        this.servings = servings;
        this.servingsList = servingsList;
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(this.getServingsProperty(), getMaxServings()));
    }

    public BasicFeastBlockJS(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
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
