package com.softwaremongers.voidtotem.recipes;

import com.softwaremongers.voidtotem.ItemManager;
import com.softwaremongers.voidtotem.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipesManager {
    private Logger logger;
    private Main plugin;
    private FileConfiguration recipeConfig = null;
    private File recipeConfigFile = null;

    public RecipesManager(Main plugin){
        this.logger = plugin.getLogger();
        this.plugin = plugin;
        saveDefaultRecipes();
        LoadRecipes();
    }

    public void ReloadConfig(){
        if(this.recipeConfigFile == null)
            this.recipeConfigFile = new File(this.plugin.getDataFolder(), "recipes.yml");

        this.recipeConfig = YamlConfiguration.loadConfiguration(this.recipeConfigFile);

        InputStream defaultStream = this.plugin.getResource("recipes.yml");
        if (defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.recipeConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getRecipes(){
        if(this.recipeConfig == null)
            ReloadConfig();

        return this.recipeConfig;
    }

    public void saveRecipes(){
        if(this.recipeConfig == null || this.recipeConfigFile == null)
            return;
        try {
            this.getRecipes().save(this.recipeConfigFile);
        }catch (IOException e){
            plugin.getLogger().log(Level.SEVERE, "Can't save to " + this.recipeConfigFile, e);
        }
    }

    public void saveDefaultRecipes(){
        if(this.recipeConfigFile == null)
            this.recipeConfigFile = new File(this.plugin.getDataFolder(), "recipes.yml");

        if(!this.recipeConfigFile.exists()){
            this.plugin.saveResource("recipes.yml", false);
        }
    }

    public void LoadRecipes(){
        new VoidTotem(this.plugin, this);
    }
}
