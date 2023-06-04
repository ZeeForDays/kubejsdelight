package qinomed.kubejsdelight;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeTypesEvent;
import net.minecraft.resources.ResourceLocation;
import qinomed.kubejsdelight.block.custom.FeastBlockBuilder;
import qinomed.kubejsdelight.item.custom.KnifeItemBuilder;
import qinomed.kubejsdelight.block.custom.PieBlockBuilder;
import qinomed.kubejsdelight.recipe.CookingRecipeJS;
import qinomed.kubejsdelight.recipe.CuttingRecipeJS;

public class KubeJSDelightPlugin extends KubeJSPlugin {
    @Override
    public void init() {
        RegistryObjectBuilderTypes.ITEM.addType("knife", KnifeItemBuilder.class, KnifeItemBuilder::new);
        RegistryObjectBuilderTypes.BLOCK.addType("pie", PieBlockBuilder.class, PieBlockBuilder::new);
        RegistryObjectBuilderTypes.BLOCK.addType("feast", FeastBlockBuilder.class, FeastBlockBuilder::new);
    }

    @Override
    public void registerRecipeTypes(RegisterRecipeTypesEvent event) {
        event.register(new ResourceLocation("farmersdelight:cutting"), CuttingRecipeJS::new);
        event.register(new ResourceLocation("farmersdelight:cooking"), CookingRecipeJS::new);
    }
}
