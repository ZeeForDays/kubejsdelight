package qinomed.kubejsdelight.block.custom;

import dev.latvian.mods.kubejs.block.BlockBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class FeastBlockBuilder extends BlockBuilder {
    public transient boolean hasLeftovers;
    public transient ResourceLocation servingItem;

    public FeastBlockBuilder(ResourceLocation i) {
        super(i);
        this.servingItem = new ResourceLocation("minecraft:air");
        this.hasLeftovers = false;
    }

    public FeastBlockBuilder servingItem(ResourceLocation s) {
        this.servingItem = s;
        return this;
    }

    public FeastBlockBuilder hasLeftovers(Boolean b) {
        this.hasLeftovers = b;
        return this;
    }

    @Override
    public Block createObject() {
        return new BasicFeastBlockJS(this);
    }
}
