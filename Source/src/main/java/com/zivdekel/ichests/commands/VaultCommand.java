package com.zivdekel.ichests.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.ArrayList;

public class VaultCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Checking if the sender is player. If it is, running the code.
        if(sender instanceof Player){
            Player player = (Player) sender;

            // Creating the inventory & ItemStacks.
            Inventory vault = Bukkit.createInventory(player, 26, ChatColor.GOLD + "Your iChest");
            ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemStack removeChest = new ItemStack(Material.BARRIER);


            // Adding lore and display names - remove iChest
            ItemMeta removeChest_Meta = removeChest.getItemMeta();
            if (removeChest_Meta != null) {
                removeChest_Meta.setDisplayName(ChatColor.DARK_RED + "REMOVE THIS CHEST");
            }
            ArrayList<String> removeChest_lore = new ArrayList<>();
            removeChest_lore.add(ChatColor.RED + "This will remove this iChest.");
            if (removeChest_Meta != null) {
                removeChest_Meta.setLore(removeChest_lore);
            }
            removeChest.setItemMeta(removeChest_Meta);

            // Adding lore and display names - Filler
            ItemMeta graystained1 = item1.getItemMeta();
            if (graystained1 != null) {
                graystained1.setDisplayName(ChatColor.GRAY + "");
            }
            ArrayList<String> graystained_lore = new ArrayList<>();
            graystained_lore.add("");
            if (graystained1 != null) {
                graystained1.setLore(graystained_lore);
            }
            item1.setItemMeta(graystained1);


            // Adding the ItemStacks to the GUI
            ItemStack[] items = {item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, item1, removeChest, item1, item1, item1, item1};
            vault.setContents(items);

            // Opening the inventory
            player.openInventory(vault);


            // If the sender ISN'T the player, exit.
        }else{
            System.out.println("You need to be a player to use that command!");
        }



        return true;
    }
}
