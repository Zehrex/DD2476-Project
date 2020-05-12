2
https://raw.githubusercontent.com/argoninc/Whitelist_Craft_Place/master/src/main/java/com/github/argoninc/Listener/WhitelistBlock.java
package com.github.argoninc.Listener;

import java.util.ArrayList;

import org.bukkit.Material;
import org.json.JSONArray;

import com.github.argoninc.Main.Principal;

public class WhitelistBlock {
	
	public static Material[] getItems(String typeItem) {
			
		JSONArray arrTemp = (JSONArray) Principal.CONFIG.get(typeItem);
		
	    ArrayList<Material> materiais = new ArrayList<Material>();
	    
		for (int i = 0; i < arrTemp.length(); i++) {
			materiais.add(Material.getMaterial((String) arrTemp.get(i)));
		}

		return materiais.toArray(new Material[0]);
	}
	
	public static boolean isValidCraft(Material item) {
		
		Material[] proib = getItems("craft");
		
		for(Material m: proib) {
			if(item.equals(m)) { 
				return false;
			}
		}

		return true;
	}
	
	public static boolean isValidPlace(Material item) {
		
		Material[] proib = getItems("place");
		
		for(Material m: proib) {
			if(item.equals(m)) { 
				return false;
			}
		}

		return true;
	}
	
	public static boolean isValidUse(Material item) {
		
		Material[] proib = getItems("use");
		
		for(Material m: proib) {
			if(item.equals(m)) { 
				return false;
			}
		}

		return true;
	}
	
}
