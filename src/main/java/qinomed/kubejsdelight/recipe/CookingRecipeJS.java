package qinomed.kubejsdelight.recipe;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.TimeComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface CookingRecipeJS {
    RecipeKey<InputItem[]> INGREDIENTS = ItemComponents.INPUT.asArray().key("ingredients");
    RecipeKey<OutputItem> ITEM = ItemComponents.OUTPUT.key("result");
    RecipeKey<InputItem> CONTAINER = ItemComponents.INPUT.key("container").optional(InputItem.EMPTY).allowEmpty();
    RecipeKey<Integer> EXPERIENCE = NumberComponent.INT.key("experience").alt("exp", "xp");
    RecipeKey<Long> TIME = TimeComponent.TICKS.key("cookingtime").alt("time");

    RecipeSchema SCHEMA = new RecipeSchema(INGREDIENTS, ITEM, EXPERIENCE, TIME, CONTAINER);
}