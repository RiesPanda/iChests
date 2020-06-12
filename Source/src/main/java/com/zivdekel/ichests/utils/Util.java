package com.zivdekel.ichests.utils;

import com.zivdekel.ichests.iChests;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Util {

    iChests main = iChests.getPlugin(iChests.class);
    File dataFile = new File("plugins//iChest//data.yml");
    YamlConfiguration data = YamlConfiguration.loadConfiguration(dataFile);

    public void openiChest(Player player, Location loc){




    }

    public String translate(String string){
        return ChatColor.translateAlternateColorCodes('&', string);

    }
    public ItemStack createItem(String root, String path){

        ItemStack item = null;

        item = new ItemStack(Material.getMaterial("Users." + root + "." + path + ".material"), 1, (byte) data.getInt("Users." + root + "." + path + ".damage"));
        ItemMeta meta = item.getItemMeta();
        String name = translate(data.getString("Users." + root + "." + path + ".name").replace("%item%", data.getString("Users." + root + "." + path + ".displayName")));
        ArrayList<String> lore = new ArrayList<String>();
        for (String string : data.getStringList("Users." + root + "." + path + ".lore")){
            lore.add(translate(string.replace("%amount%", String.valueOf(data.getInt("Users." + root + "." + path + ".amount")))));
        }
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);


        return item;
    }

    public void loadiChestInfo(){
        //if "Users" section exists
        if (data.isConfigurationSection("Users")){

            for (String string : data.getConfigurationSection("Users").getKeys(false)){

                UUID uuid = UUID.fromString(string);
                ArrayList<ItemStack> itemStackArrayList = new ArrayList<>();
                for (String items : data.getConfigurationSection("Users." + string).getKeys(false)){

                    itemStackArrayList.add(createItem(string, items));
                }
                main.iChests.put(uuid, itemStackArrayList);
                itemStackArrayList.clear();

            }

        }else{
            System.out.println("Error while loading iChest data.yml");
        }



    }
    public void saveiChestInfo(){

        for (UUID uuid : main.iChests.keySet()){
        for (int i = 0; i < main.iChests.get(uuid).size(); i++) {
            ItemStack item = main.iChests.get(uuid).get(i);
                ItemMeta meta = item.getItemMeta();
                String displayName = meta.getDisplayName();
                String material = item.getType().toString();
                int amount = 100;
                byte damage = 0;
                List<String> lore = meta.getLore();

            data.set("Users." + uuid.toString() + "." + i + ".displayName", displayName);
            data.set("Users." + uuid.toString() + "." + i + ".name", displayName);
            data.set("Users." + uuid.toString() + "." + i + ".lore", lore);
            data.set("Users." + uuid.toString() + "." + i + ".amount", amount);
            data.set("Users." + uuid.toString() + "." + i + ".material", material);
            data.set("Users." + uuid.toString() + "." + i + ".damage", damage);
        }
        }
    }
}
