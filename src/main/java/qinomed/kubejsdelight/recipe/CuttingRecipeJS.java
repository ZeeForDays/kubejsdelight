package qinomed.kubejsdelight.recipe;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

import java.util.Map;

public interface CuttingRecipeJS {

    RecipeKey<InputItem[]> INPUT = ItemComponents.INPUT.asArray().key("ingredients");

    RecipeKey<InputItem> TOOL = ItemComponents.INPUT.key("tool");

    RecipeComponent<OutputItem> CHANCE_RESULT = new RecipeComponentWithParent<>() {
        @Override
        public RecipeComponent<OutputItem> parentComponent() {
            return ItemComponents.OUTPUT_ID_WITH_COUNT;
        }

        @Override
        public void writeToJson(RecipeComponentValue<OutputItem> value, JsonObject json) {
            RecipeComponentWithParent.super.writeToJson(value, json);
            json.addProperty("chance", value.value.chance);
        }

        @Override
        public OutputItem readFromJson(RecipeJS recipe, RecipeKey<OutputItem> key, JsonObject json) {
            var item = RecipeComponentWithParent.super.readFromJson(recipe, key, json);

            if (item != null && json.has("chance")) {
                item = item.withChance(json.get("chance").getAsFloat());
            }

            return item;
        }

        @Override
        public String toString() {
            return parentComponent().toString();
        }
    };

    RecipeKey<OutputItem[]> RESULTS = CHANCE_RESULT.asArray().key("result");

    RecipeKey<String> SOUND = StringComponent.ANY.key("sound").optional("").allowEmpty();

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, TOOL, RESULTS, SOUND);
}