package qinomed.kubejsdelight.block.custom;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Supplier;

public class BasicFeastBlockJS extends FeastBlock {
    public BasicFeastBlockJS(FeastBlockBuilder builder) {
        super(builder.createProperties(), () -> ForgeRegistries.ITEMS.getValue(builder.servingItem), builder.hasLeftovers);
    }

    public BasicFeastBlockJS(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
    }
}
