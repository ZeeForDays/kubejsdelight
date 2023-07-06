package qinomed.kubejsdelight;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.minecraft.resources.ResourceLocation;
import qinomed.kubejsdelight.block.custom.FeastBlockBuilder;
import qinomed.kubejsdelight.item.custom.KnifeItemBuilder;
import qinomed.kubejsdelight.block.custom.PieBlockBuilder;
import qinomed.kubejsdelight.recipe.CookingRecipeJS;
import qinomed.kubejsdelight.recipe.CuttingRecipeJS;

public class KubeJSDelightPlugin extends KubeJSPlugin {
    @Override
    public void init() {
        RegistryInfo.ITEM.addType("knife", KnifeItemBuilder.class, KnifeItemBuilder::new);
        RegistryInfo.BLOCK.addType("pie", PieBlockBuilder.class, PieBlockBuilder::new);
        RegistryInfo.BLOCK.addType("feast", FeastBlockBuilder.class, FeastBlockBuilder::new);
    }

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.register(new ResourceLocation("farmersdelight:cutting"), CuttingRecipeJS.SCHEMA);
        event.register(new ResourceLocation("farmersdelight:cooking"), CookingRecipeJS.SCHEMA);
    }
}
