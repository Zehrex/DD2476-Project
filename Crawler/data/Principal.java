2
https://raw.githubusercontent.com/argoninc/Whitelist_Craft_Place/master/src/main/java/com/github/argoninc/Main/Principal.java
package com.github.argoninc.Main;

import java.io.File;
import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONArray;

import com.github.argoninc.Listener.ListenerCraft;
import com.github.rillis.Files.FileUtils;
import com.rillis.config.Config;

public class Principal extends JavaPlugin {

	public static File ARQUIVO;
	public static Config CONFIG;
	
	
	@Override
	public void onEnable() {
		
		ARQUIVO = newFile("argoninc/whitelist_items.cfg", "{}");
		CONFIG = new Config(ARQUIVO);
	
		if(!CONFIG.has("craft")){
			CONFIG.set("craft", new JSONArray());
		}
		if(!CONFIG.has("place")){
			CONFIG.set("place", new JSONArray());
		}
		if(!CONFIG.has("use")){
			CONFIG.set("use", new JSONArray());
		}
		
		getServer().getPluginManager().registerEvents(new ListenerCraft(), this);
		System.out.println("***************** ENABLE ******************");
	}

	@Override
	public void onDisable() {
		System.out.println("***************** DISABLE ******************");
	}

	private File newFile(String url, String text) {
        File file = new File(url);
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if(!file.exists()) {
            try {
                file.createNewFile();

                if(text!=null) {
                    FileUtils.write(file, text);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
