package com.zivdekel.ichests.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ChestPlace implements Listener {

    public void onChestPlace(BlockPlaceEvent event) {
        ArrayList<Location> locations = new ArrayList<>();

        ItemStack iChest = new ItemStack(Material.CHEST);
        ItemMeta ichest_meta = iChest.getItemMeta();
        if (ichest_meta != null) {
            ichest_meta.setDisplayName(ChatColor.GOLD + "iChest");
        }
        ArrayList<String> ichest_lore = new ArrayList<>();
        ichest_lore.add(ChatColor.RED + "This is an iChest");
        if (ichest_meta != null) {
            ichest_meta.setLore(ichest_lore);
        }
        iChest.setItemMeta(ichest_meta);

        Player player = event.getPlayer();

        if (event.getBlock().getType().equals(Material.CHEST)) {
            if (event.getBlock().hasMetadata(iChest.toString())){
                Location location = event.getBlockPlaced().getLocation();
                locations.add(location);

            }
        }
    }
}
