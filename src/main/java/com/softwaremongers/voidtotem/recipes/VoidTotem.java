package com.softwaremongers.voidtotem.recipes;

import com.softwaremongers.voidtotem.ItemManager;
import com.softwaremongers.voidtotem.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class VoidTotem {
    private Logger logger;
    private RecipesManager data;
    private final String totemName = "Void";

    public VoidTotem(Main plugin, RecipesManager data){
        this.logger = plugin.getLogger();
        this.data = data;

        logger.info(totemName + " totem recipe loading...");

        if(data.getRecipes().contains("Totems." + totemName + ".Enabled") && data.getRecipes().getBoolean("Totems." + totemName + ".Enabled")){
            if(data.getRecipes().contains("Totems." + totemName + ".Shapeless") && !data.getRecipes().getBoolean("Totems." + totemName + ".Shapeless"))
                Bukkit.addRecipe(Shaped());
            else if(data.getRecipes().contains("Totems." + totemName + ".Shapeless" ) && data.getRecipes().getBoolean("Totems." + totemName + ".Shapeless"))
                Bukkit.addRecipe(Shapeless());

            logger.info(totemName + " totem recipe enabled!");
        }

    }
    public ShapedRecipe Shaped(){
        ItemStack item = new ItemManager().VoidTotem();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "void_totem");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        String top = data.getRecipes().getString("Totems." + totemName + ".Crafting" + ".Top");
        String mid = data.getRecipes().getString("Totems." + totemName + ".Crafting" + ".Mid");
        String bot = data.getRecipes().getString("Totems." + totemName + ".Crafting" + ".Bot");
        recipe.shape(top, mid, bot);


        for (String i : data.getRecipes().getConfigurationSection("Totems." + totemName + ".Materials").getKeys(true)) {
            recipe.setIngredient(i.charAt(0), Material.valueOf(data.getRecipes().getString("Totems."+totemName+".Materials." + i).toUpperCase()));
        }

        return recipe;
    }

    public ShapelessRecipe Shapeless(){
        ItemStack item = new ItemManager().VoidTotem();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "void_totem");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);

        for (String i: data.getRecipes().getConfigurationSection("Totems." + totemName + ".Materials").getKeys(true)) {
            recipe.addIngredient(Material.valueOf(data.getRecipes().getString("Totems."+totemName+".Materials." + i).toUpperCase()));
        }

        return recipe;
    }
}
