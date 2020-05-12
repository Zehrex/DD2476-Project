2
https://raw.githubusercontent.com/argoninc/Whitelist_Craft_Place/master/src/main/java/com/github/argoninc/Messages/MensagensUsuario.java
package com.github.argoninc.Messages;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class MensagensUsuario {
	
	
	public static void blockCraftMessageToUser(Player target, String message) {
		target.sendMessage(ChatColor.RED + message);
	}
	
	public static void blockPlaceMessageToUser(Player target, String message) {
		target.sendMessage(ChatColor.RED + message);
	}
	
	public static void blockInteractMessageToUser(Player target, String message) {
		target.sendMessage(ChatColor.RED + message);
	}


}
