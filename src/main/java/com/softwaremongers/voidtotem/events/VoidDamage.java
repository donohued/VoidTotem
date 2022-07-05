package com.softwaremongers.voidtotem.events;

import com.softwaremongers.voidtotem.ItemManager;
import com.softwaremongers.voidtotem.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class VoidDamage {




    String teleMessage = "You have been returned to the mortal realm by the dark power of the Void Totem!";
    Location teleLocation;

    public VoidDamage(EntityDamageEvent e){
        Player player = (Player) e.getEntity();
        ItemStack totem = new ItemManager().VoidTotem();
        String world = Main.getPlugin(Main.class).getConfig().getString("totemworld");
        boolean useCoords = Main.getPlugin(Main.class).getConfig().getBoolean("useSetLocation");

        //use "world"
        if (world != null && player.getInventory().contains(totem)){
            if(useCoords){
                int spawnX = Main.getPlugin(Main.class).getConfig().getInt("spawnX");
                int spawnY = Main.getPlugin(Main.class).getConfig().getInt("spawnY");
                int spawnZ = Main.getPlugin(Main.class).getConfig().getInt("spawnZ");
                player.sendMessage("Coords are " + spawnX + ", " + spawnY + ", " + spawnZ);
                teleLocation = new Location(Bukkit.getWorld(world), spawnX, spawnY, spawnZ);
            }
            else
                teleLocation = new Location(Bukkit.getWorld(world), player.getLocation().getBlockX(), 320, player.getLocation().getBlockZ());

            e.setCancelled(true);
            player.teleport(teleLocation);
            player.getInventory().removeItem(totem);
            player.sendMessage(ChatColor.DARK_GRAY + teleMessage);
        }
        else if(player.getInventory().contains(totem)){
            player.sendMessage(ChatColor.BLUE + "The config is not set properly!");
            teleLocation = new Location(Bukkit.getWorld("world"), player.getLocation().getBlockX(), 320, player.getLocation().getBlockZ());
            player.teleport(teleLocation);
            player.getInventory().removeItem(totem);
            player.sendMessage(ChatColor.DARK_GRAY + teleMessage);
        }
    }
}
