package de.cure53.anton.itemdrop;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemDrop extends JavaPlugin implements Listener {

	/**
	 * Hier registrieren wir, dass das Plugin lädt
	 * 
	 * @param e
	 */
	@Override
	public void onEnable() {
		getLogger().info("ItemDrop wurde gestartet");
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onPlayerLogin(final PlayerLoginEvent event) {

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			public void run() {
				// Finde den aktuellen Spieler
				Player player = event.getPlayer();

				if(!player.isDead()) {
				
					// Finde das Inventar
					PlayerInventory inventory = player.getInventory();
	
					// Finde alle Materialien
					Material[] matlist = Material.values();
					
					// Wähle zufälliges Material aus
					int random = new Random().nextInt(matlist.length);
					Material material = matlist[random];
					
					// Setze Material ins Inventar
					ItemStack item = new ItemStack(material);
					inventory.addItem(item);
					player.sendMessage( 
							ChatColor.YELLOW + "You were given " +
							ChatColor.GREEN + material.name().replace("_", " ").toLowerCase() + 
							ChatColor.YELLOW +", whoa!" 
					);
				} else {
					player.sendMessage( 
						ChatColor.YELLOW + "You are dead and cannot receive items" 
					);
				}
			}

		}, 0L, 300L);
	}

	/**
	 * Hier registrieren wir, dass das Plugin entfernt wird
	 * 
	 * @param e
	 */
	@Override
	public void onDisable() {
		getLogger().info("ItemDrop wurde beendet");
	}

}
