package com.zivdekel.ichests.events;

import com.zivdekel.ichests.commands.VaultCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Chestopen implements Listener {
    private VaultCommand vault;

    public Chestopen(VaultCommand vault) {
        this.vault = vault;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();



        Material block = event.getClickedBlock().getType();
        if (block.equals(Material.CHEST)) {
            if (block.data.getName().equalsIgnoreCase(ChatColor.GOLD + "Your iChest")){
                player.openInventory(vault.vault);

            }
        }
    }
}
