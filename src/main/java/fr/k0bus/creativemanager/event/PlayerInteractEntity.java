package fr.k0bus.creativemanager.event;

import fr.k0bus.creativemanager.settings.Protections;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import fr.k0bus.creativemanager.CreativeManager;

public class PlayerInteractEntity implements Listener {

	CreativeManager plugin;

	public PlayerInteractEntity(CreativeManager instance) {
		plugin = instance;
	}

	@EventHandler(ignoreCancelled = true)
	public void onUse(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if(plugin.getSettings().getProtection(Protections.ENTITY) && p.getGameMode().equals(GameMode.CREATIVE) && !p.hasPermission("creativemanager.bypass.entity"))
		{
			if (e.getRightClicked() instanceof ItemFrame && p.getGameMode().equals(GameMode.CREATIVE)) {
				if (!p.hasPermission("creativemanager.bypass.entity")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getSettings().getTag() + plugin.getLang().getString("permission.entity")));
					e.setCancelled(true);
				}
			}
		}
	}
}
