package qinomed.kubejsdelight.block.custom;

import dev.latvian.mods.kubejs.block.BlockBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.List;

public class FeastBlockBuilder extends BlockBuilder {
    public transient boolean hasLeftovers;
    public transient List<ResourceLocation> servingsList;
    public transient int servings;

    public FeastBlockBuilder(ResourceLocation i) {
        super(i);
        this.servingsList = List.of(new ResourceLocation("minecraft:air"));
        this.servings = 4;
        this.hasLeftovers = false;
    }

    public FeastBlockBuilder servingsAmount(int amount) {
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
