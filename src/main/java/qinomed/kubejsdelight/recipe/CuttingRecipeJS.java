package qinomed.kubejsdelight.recipe;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface CuttingRecipeJS {

    RecipeKey<InputItem[]> INPUT = ItemComponents.INPUT.asArray().key("ingredients");

    RecipeKey<InputItem> TOOL = ItemComponents.INPUT.key("tool");

    RecipeKey<OutputItem[]> RESULTS = ItemComponents.OUTPUT_ARRAY.key("result");

    RecipeKey<String> SOUND = StringComponent.ANY.key("sound").optional("").allowEmpty();

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, TOOL, RESULTS, SOUND);
}