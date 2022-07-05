package com.softwaremongers.voidtotem;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager{
    public ItemManager(){ }

    public ItemStack VoidTotem(){
        List<String> lore = new ArrayList<String>();
        lore.add("Keep in your back pocket in case of a bad fall...");

        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(4546001);
        meta.setDisplayName("Void Totem");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING,1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

}
