package com.zivdekel.ichests.utils;

import com.zivdekel.ichests.iChests;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomYaml {

    //get the instance of our main class
    iChests main = iChests.getPlugin(iChests.class);


        //Load our custom yml file
        public void loadData() {
            File dataFile = new File("plugins//iChest//data.yml");
            YamlConfiguration data = YamlConfiguration.loadConfiguration(dataFile);
            //Check if custom yml file exists
            if (dataFile.exists()) {
                //if it does then save the custom yml
                this.saveCustomYaml(dataFile, data);

            }else {
                try {
                    //otherwise create a new custom yml, with a "Users" section
                    dataFile.createNewFile();
                    data.createSection("Users");
                    this.saveCustomYaml(dataFile, data);
                }catch (IOException exception) {

                }
            }
        }

        public void saveCustomYaml(File file, YamlConfiguration yamlConfiguration) {
            try {
                yamlConfiguration.save(file);

            }catch (IOException exception) {

            }
        }

}
