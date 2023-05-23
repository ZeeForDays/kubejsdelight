package qinomed.kubejsdelight;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import qinomed.kubejsdelight.block.custom.FeastBlockBuilder;
import qinomed.kubejsdelight.item.custom.KnifeItemBuilder;
import qinomed.kubejsdelight.block.custom.PieBlockBuilder;

public class KubeJSDelightPlugin extends KubeJSPlugin {
    @Override
    public void init() {
        RegistryObjectBuilderTypes.ITEM.addType("knife", KnifeItemBuilder.class, KnifeItemBuilder::new);
        RegistryObjectBuilderTypes.BLOCK.addType("pie", PieBlockBuilder.class, PieBlockBuilder::new);
        RegistryObjectBuilderTypes.BLOCK.addType("feast", FeastBlockBuilder.class, FeastBlockBuilder::new);
    }
}
