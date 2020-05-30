package com.zivdekel.ichests.events;

import com.zivdekel.ichests.commands.VaultCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class guiClickEvent implements Listener {

    private VaultCommand vault;

    public guiClickEvent(VaultCommand vault) {
        this.vault = vault;
    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        

        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Your iChest")) {
            event.setCancelled(true);
            if(event.getCurrentItem() != null) {
                switch(event.getCurrentItem().getType()){
                    case BARRIER:

                        break;
                    case OAK_SIGN:

                        break;
                    default:
                        return;
                }
                player.closeInventory();
            }
        }
    }

}
