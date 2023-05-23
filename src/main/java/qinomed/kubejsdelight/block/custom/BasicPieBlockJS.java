package qinomed.kubejsdelight.block.custom;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.function.Supplier;

public class BasicPieBlockJS extends PieBlock {
    public BasicPieBlockJS(PieBlockBuilder builder) {
        super(builder.createProperties(), () -> ForgeRegistries.ITEMS.getValue(builder.sliceItem));
    }

    public BasicPieBlockJS(Properties properties, Supplier<Item> pieSlice) {
        super(properties, pieSlice);
    }
}
