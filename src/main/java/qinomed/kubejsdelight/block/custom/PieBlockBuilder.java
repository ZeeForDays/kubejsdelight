package qinomed.kubejsdelight.block.custom;

import dev.latvian.mods.kubejs.block.BlockBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class PieBlockBuilder extends BlockBuilder {
    public transient ResourceLocation sliceItem;

    public PieBlockBuilder(ResourceLocation i) {
        super(i);
        sliceItem = new ResourceLocation("minecraft:air");
    }

    public PieBlockBuilder sliceItem(ResourceLocation s) {
        this.sliceItem = s;
        return this;
    }

    @Override
    public Block createObject() {
        return new BasicPieBlockJS(this);
    }
}
