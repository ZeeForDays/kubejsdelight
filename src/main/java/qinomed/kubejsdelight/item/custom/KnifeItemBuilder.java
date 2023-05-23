package qinomed.kubejsdelight.item.custom;

import dev.latvian.mods.kubejs.item.custom.HandheldItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.tag.ModTags;

public class KnifeItemBuilder extends HandheldItemBuilder {
    public KnifeItemBuilder(ResourceLocation i) {
        super(i, 0.5f, -2f);
        tag(ModTags.KNIVES.location());
    }

    @Override
    public Item createObject() {
        return new KnifeItem(toolTier, attackDamageBaseline, speedBaseline, createItemProperties());
    }
}
