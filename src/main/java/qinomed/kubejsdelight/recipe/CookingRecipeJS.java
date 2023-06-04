package qinomed.kubejsdelight.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.core.ItemStackKJS;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;

import java.util.List;

public class CookingRecipeJS extends RecipeJS {
    public static RecipeSerializer<CookingPotRecipe> serializer = (RecipeSerializer<CookingPotRecipe>) ModRecipeSerializers.COOKING.get();
    private CookingPotRecipe recipe = null;

    @Override
    public void create(RecipeArguments args) {
        recipe = new CookingPotRecipe(
                getOrCreateId(),
                "",
                null,
                parseItemInputListNN(args.get(0)),
                parseItemOutput(args.get(1)),
                parseItemOutput(args.get(2)),
                args.getFloat(3, 0),
                args.getInt(4, 1)
        );
    }

    public NonNullList<Ingredient> parseItemInputListNN(Object obj) {
        NonNullList<Ingredient> nlist = NonNullList.create();
        nlist.addAll(parseItemInputList(obj));
        return nlist;
    }

    @Override
    public void deserialize() {
        recipe = serializer.fromJson(getOrCreateId(), json);
    }

    @Override
    public void serialize() {
        JsonArray arrayIngredients = new JsonArray();

        for (Ingredient ingredient : recipe.getIngredients()) {
            arrayIngredients.add(ingredient.toJson());
        }
        json.add("ingredients", arrayIngredients);

        JsonObject objectResult = new JsonObject();
        var result = recipe.getResultItem();
        objectResult.addProperty("item", ((ItemStackKJS)(Object)(result)).kjs$getId().toString());
        if (result.getCount() > 1) {
            objectResult.addProperty("count", result.getCount());
        }
        json.add("result", objectResult);

        var container = recipe.getOutputContainer();
        if (container != null) {
            JsonObject objectContainer = new JsonObject();
            objectContainer.addProperty("item", ((ItemStackKJS)(Object)(container)).kjs$getId().toString());
            json.add("container", objectContainer);
        }
        if (recipe.getExperience() > 0) {
            json.addProperty("experience", recipe.getExperience());
        }
        json.addProperty("cookingtime", recipe.getCookTime());
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        var ings = recipe.getIngredients();
        for (Ingredient ing : ings) {
            if (match.contains(ing)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        var ings = recipe.getIngredients();
        var changed = false;
        for (int i = 0; i < ings.size(); i++) {
            var ing = ings.get(i);
            if (match.contains(ing)) {
                ings.set(i, transformer.transform(this, match, ing, with));
                changed = true;
            }
        }
        if (changed) {
            recipe = new CookingPotRecipe(
                    getOrCreateId(),
                    "",
                    null,
                    ings,
                    recipe.getResultItem(),
                    recipe.getOutputContainer(),
                    recipe.getExperience(),
                    recipe.getCookTime()
            );
        }
        return changed;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return match.contains(recipe.getResultItem()) || match.contains(recipe.getOutputContainer());
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        var changed = false;
        ItemStack result = recipe.getResultItem();
        if (match.contains(result)) {
            result = transformer.transform(this, match, result, with);
            changed = true;
        }
        ItemStack container = recipe.getOutputContainer();
        if (match.contains(container)) {
            container = transformer.transform(this, match, container, with);
            changed = true;
        }
        if (changed) {
            recipe = new CookingPotRecipe(
                    getOrCreateId(),
                    "",
                    null,
                    recipe.getIngredients(),
                    result,
                    container,
                    recipe.getExperience(),
                    recipe.getCookTime()
            );
        }
        return changed;
    }
}
