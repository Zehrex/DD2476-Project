2
https://raw.githubusercontent.com/argoninc/Whitelist_Craft_Place/master/src/main/java/com/github/argoninc/Listener/ListenerCraft.java
package com.github.argoninc.Listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.argoninc.Messages.MensagensUsuario;

import net.md_5.bungee.api.ChatColor;

public class ListenerCraft implements Listener {

	@EventHandler

	// Método para fazer listener em todos os eventos de crafting
	public void onCraftItem(CraftItemEvent event) {

		// Pega o player que tentou realizar o crafiting
		Player player = (Player) event.getWhoClicked();
		// Pega o item que houve a tentativa de crafiting
		Material item = event.getInventory().getResult().getType();
		
		//Só funciona no Survival
		if (player.getGameMode().equals(GameMode.SURVIVAL)) {
			// If para comparar se é um crafting válido
			if (!WhitelistBlock.isValidCraft(item)) {
				MensagensUsuario.blockCraftMessageToUser(player, "Você não pode craftar " + item);
				// Canecela a ação de crafting para o case de ser um item inválido
				event.setCancelled(true);
			}
		}

	}

	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent event) {
		Player player = event.getPlayer();

		Material item = event.getBlockPlaced().getType();

		//Só funciona no Survival
		if (player.getGameMode().equals(GameMode.SURVIVAL)) {
			// If para comparar se é um crafting válido
			if (!WhitelistBlock.isValidPlace(item)) {
				MensagensUsuario.blockPlaceMessageToUser(player, "Você não pode colocar " + item);
				// Canecela a ação de crafting para o case de ser um item inválido
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		Material itemMainHand = player.getInventory().getItemInMainHand().getType();

		Material itemSecondHand = player.getInventory().getItemInOffHand().getType();

		//Só funciona no Survival
		if (player.getGameMode().equals(GameMode.SURVIVAL)) {
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				//Compara nas duas mãos os itens
				if (!WhitelistBlock.isValidUse(itemMainHand) || !WhitelistBlock.isValidUse(itemSecondHand)) {
					MensagensUsuario.blockInteractMessageToUser(player,
							"Você não pode usar esse item, recomenda-se descartá-lo");
					event.setCancelled(true);
				}
			}
		}

	}

}
