package com.softwaremongers.voidtotem.commands.subcommands;

import com.softwaremongers.voidtotem.Main;
import com.softwaremongers.voidtotem.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class SetWorldCommand extends SubCommand {

    @Override
    public String getName(){
        return "setworld";
    }

    @Override
    public String getDescription(){
        return "Set the spawn world. set specific coordinates if you feel so inclined.";
    }

    @Override
    public String getSyntax(){
        return "/totem setworld worldname <x,y,z>";
    }

    @Override
    public void perform(Player player, String[] args){
        if(args.length > 1){
            if(player.hasPermission("voidtotem.setworld")){
                if(args.length == 2){
                    //setworld with dynamic coords
                    Main.getPlugin(Main.class).getConfig().set("useSetLocation", false);
                    Main.getPlugin(Main.class).getConfig().set("totemworld", args[1]);
                    player.sendMessage(ChatColor.GREEN + "The world for totems to teleport players to has been set to: " + args[1]);
                }else if(args.length == 5){
                    //setworld with given coords
                    Main.getPlugin(Main.class).getConfig().set("useSetLocation", true);
                    Main.getPlugin(Main.class).getConfig().set("totemworld", args[1]);
                    player.sendMessage(ChatColor.GREEN + "The world for totems to teleport players to has been set to: " + args[1] + " at: " + args[2] + ", " + args[3] + ", " + args[4]);
                    //do this better lol
                    Main.getPlugin(Main.class).getConfig().set("spawnX", parseInt(args[2]));
                    Main.getPlugin(Main.class).getConfig().set("spawnY", parseInt(args[3]));
                    Main.getPlugin(Main.class).getConfig().set("spawnZ", parseInt(args[4]));
                }
                Main.getPlugin(Main.class).saveConfig();
            }else{
                player.sendMessage(ChatColor.RED + "You do not have permission to do that.");
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {

        if(args.length == 2){
            List<String> worldNames = new ArrayList<>();
            World[] worlds = new World[Bukkit.getServer().getWorlds().size()];
            Bukkit.getServer().getWorlds().toArray(worlds);
            //player.sendMessage("");
            for (int i = 0; i < worlds.length; i++){
                worldNames.add(worlds[i].getName());
            }
            return  worldNames;
        }else if(args.length == 3){
            List<String> worldNames = new ArrayList<>();
            World[] worlds = new World[Bukkit.getServer().getWorlds().size()];
            Bukkit.getServer().getWorlds().toArray(worlds);
            //player.sendMessage("");
            for (int i = 0; i < worlds.length; i++){
                worldNames.add(worlds[i].getName());
            }
            return  worldNames;
        }


        return null;
    }

}
