package com.zivdekel.ichests;

import com.zivdekel.ichests.commands.VaultCommand;
import com.zivdekel.ichests.events.guiClickEvent;
import net.brcdev.shopgui.ShopGuiPlugin;
import net.brcdev.shopgui.ShopGuiPlusApi;
import net.brcdev.shopgui.provider.economy.EconomyProvider;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public final class iChests extends JavaPlugin {

    private static Economy econ = null;
    private VaultCommand vault;

    public iChests(VaultCommand vault) {
        this.vault = vault;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupEconomy() ) {
            System.out.println("No currency plugin found, disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


        System.out.println("iChests by RiesPanda has been enabled!");
        getCommand("ichest").setExecutor(new VaultCommand());
        getServer().getPluginManager().registerEvents(new guiClickEvent(vault), this);
        loadConfig();
    }




    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }


    private void hookIntoShopGUI() {
            ShopGuiPlusApi.registerEconomyProvider((EconomyProvider) econ);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
