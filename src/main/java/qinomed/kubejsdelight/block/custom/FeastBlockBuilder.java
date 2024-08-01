package qinomed.kubejsdelight.block.custom;

import dev.latvian.mods.kubejs.block.BlockBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.Arrays;
import java.util.List;

public class FeastBlockBuilder extends BlockBuilder {
    public transient boolean hasLeftovers;
    public transient List<ResourceLocation> servingsList;
    public transient int servings;
    public transient IntegerProperty SERVINGS;

    public FeastBlockBuilder(ResourceLocation i) {
        super(i);
        this.servingsList = List.of(new ResourceLocation("minecraft:air"));
        this.servings = 4;
        this.hasLeftovers = false;
        blockStateProperties.add(FeastBlock.FACING);
    }

    public FeastBlockBuilder servingsAmount(int amount) {
        SERVINGS = IntegerProperty.create("servings", 0, amount);
        blockStateProperties.add(SERVINGS);
        this.servings = amount;
        return this;
    }

    public FeastBlockBuilder servingItems(ResourceLocation[] locations) {
        this.servingsList = Arrays.asList(locations);
        return this;
    }

    public FeastBlockBuilder hasLeftovers(Boolean b) {
        this.hasLeftovers = b;
        return this;
    }

    @Override
    public Block createObject() {
        return new BasicFeastBlockJS(this, servings, servingsList);
    }
}
