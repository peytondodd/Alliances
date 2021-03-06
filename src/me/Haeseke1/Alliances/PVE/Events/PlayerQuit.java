package me.Haeseke1.Alliances.PVE.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Haeseke1.Alliances.PVE.ArenaManager;
import me.Haeseke1.Alliances.PVE.Group;
import me.Haeseke1.Alliances.PVE.GroupManager;

public class PlayerQuit implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	private void playerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		if(GroupManager.hasGroup(player)){
			Group group = GroupManager.getGroup(player);
			if(ArenaManager.hasArena(group)){
				ArenaManager.getArena(group).stopArena(false);
			}else{
				GroupManager.getGroup(player).disband();
			}
		}
	}
}
