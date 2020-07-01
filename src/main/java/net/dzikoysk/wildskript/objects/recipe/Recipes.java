package net.dzikoysk.wildskript.objects.recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.Iterator;

public class Recipes {

    public static ArrayList<Recipes> list = new ArrayList<>();

    String id;
    ItemStack result;
    ItemStack[] ingredients;
    ShapedRecipe recipe;
    boolean register;

    private Recipes(String id) {
        this.id = id;
        this.ingredients = new ItemStack[9];
        this.register = false;
        list.add(this);
    }

    public static Recipes get(String id) {
        for (Recipes recipe : list) if (recipe.getID().equals(id)) return recipe;
        return new Recipes(id);
    }

    public void register() {
        if (this.result == null || this.ingredients == null) return;
        while (this.register) this.unregister();
        ShapedRecipe recipe = new ShapedRecipe(this.result);
        recipe.shape("123", "456", "789");
        for (int i = 1; i < 10; i++) {
            if (this.ingredients[i - 1] != null && !(this.ingredients[i - 1].getType() == Material.AIR)) {
                recipe.setIngredient((Integer.toString(i)).charAt(0), this.ingredients[i - 1].getType());
            }
        }
        this.recipe = recipe;
        Bukkit.addRecipe(recipe);
        this.register = true;
    }

    public void unregister() {
        Iterator<Recipe> iter = Bukkit.recipeIterator();
        while (iter.hasNext()) {
            Recipe r = iter.next();
            if (r.getResult().equals(this.result)) {
                iter.remove();
                this.register = false;
            }
        }
    }

    public void delete() {
        list.remove(this);
        this.unregister();
    }

    public void setResult(ItemStack result) {
        this.result = result;
    }

    public void setIngredient(int i, ItemStack ingredient) {
        this.ingredients[i] = ingredient;
    }

    public String getID() {
        return this.id;
    }

    public ItemStack getResult() {
        return this.result;
    }

    public ItemStack getIngredient(int i) {
        return this.ingredients[i];
    }

    public boolean isRegistered() {
        return this.register;
    }

    public ItemStack[] getIngredients() {
        return this.ingredients;
    }
}
