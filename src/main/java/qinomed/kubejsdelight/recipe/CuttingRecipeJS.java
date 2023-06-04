package qinomed.kubejsdelight.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.core.ItemStackKJS;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.util.ListJS;
import dev.latvian.mods.kubejs.util.MapJS;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;

public class CuttingRecipeJS extends RecipeJS {
    public RecipeSerializer<CuttingBoardRecipe> serializer = (RecipeSerializer<CuttingBoardRecipe>) ModRecipeSerializers.CUTTING.get();
    private CuttingBoardRecipe recipe = null;
    @Override
    public void create(RecipeArguments args) {
        recipe = new CuttingBoardRecipe(
                new ResourceLocation("kubejs:dummy"),
                "",
                parseItemInput(args.get(0)),
                parseItemInput(args.get(1)),
                parseItemOutputListWithChance(args.get(2)),
                args.getString(3, "")
        );
    }

    public NonNullList<ChanceResult> parseItemOutputListWithChance(Object obj) {
        NonNullList<ChanceResult> list = NonNullList.create();

        for (var ele : ListJS.orSelf(obj)) {
            JsonObject map = MapJS.json(ele);
            if (map != null && map.has("chance")) {
                list.add(new ChanceResult(ItemStackJS.of(ele), map.get("chance").getAsFloat()));
            } else {
                list.add(new ChanceResult(ItemStackJS.of(ele), 1F));
            }
        }

        return list;
    }

    @Override
    public void deserialize() {
        recipe = serializer.fromJson(getOrCreateId(), json);
    }

    @Override
    public void serialize() {
        JsonArray arrayIngredients = new JsonArray();
        arrayIngredients.add(recipe.getIngredients().get(0).toJson());
        json.add("ingredients", arrayIngredients);

        json.add("tool", recipe.getTool().toJson());

        JsonArray arrayResults = new JsonArray();
        for (ChanceResult result : recipe.getRollableResults()) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", ((ItemStackKJS)(Object)(result.getStack())).kjs$getId().toString());
            if (result.getStack().getCount() > 1) {
                jsonobject.addProperty("count", result.getStack().getCount());
            }
            if (result.getChance() < 1) {
                jsonobject.addProperty("chance", result.getChance());
            }
            arrayResults.add(jsonobject);
        }
        json.add("result", arrayResults);
        if (!recipe.getSoundEventID().isEmpty()) {
            json.addProperty("sound", recipe.getSoundEventID());
        }
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(recipe.getIngredients().get(0));
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
        var tool = recipe.getTool();
        if (match.contains(tool)) {
            tool = transformer.transform(this, match, tool, with);
            changed = true;
        }
        if (changed) {
            recipe = new CuttingBoardRecipe(
                    getOrCreateId(),
                    "",
                    ings.get(0),
                    tool,
                    recipe.getRollableResults(),
                    recipe.getSoundEventID()
            );
        }
        return changed;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        var results = recipe.getResults();
        for (var res : results) {
            if (match.contains(res)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        var changed = false;
        var results = recipe.getRollableResults();
        for (int i = 0; i < results.size(); i++) {
            var result = results.get(i);
            if (match.contains(result.getStack())) {
                results.set(i, new ChanceResult(transformer.transform(this, match, result.getStack(), with), result.getChance()));
                changed = true;
            }
        }
        if (changed) {
            recipe = new CuttingBoardRecipe(
                    getOrCreateId(),
                    "",
                    recipe.getIngredients().get(0),
                    recipe.getTool(),
                    results,
                    recipe.getSoundEventID()
            );
        }
        return changed;
    }
}
