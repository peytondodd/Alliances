package me.Haeseke1.Alliances.regionSelect;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Haeseke1.Alliances.Utils.MessageManager;


public class regionSelect implements Listener{
	
	public static HashMap<Player,Location> leftClick = new HashMap<Player,Location>();
	public static HashMap<Player,Location> rightClick = new HashMap<Player,Location>();
	
	
	@EventHandler
	private void playerInteract(PlayerInteractEvent event){
		if(!event.hasItem()){
			return;
		}
		if(!event.hasBlock()){
			return;
		}
		if(event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName() && 
				event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Region selector")){
			if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
				Location loc = event.getClickedBlock().getLocation();
				leftClick.put(event.getPlayer(), loc);
				MessageManager.sendRemarkMessage(event.getPlayer(), "1st position set! Coordinates: X" + loc.getBlockX() + " Y" + loc.getBlockY() + " Z" + loc.getBlockZ());
				return;
			}
			if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
				Location loc = event.getClickedBlock().getLocation();
				rightClick.put(event.getPlayer(), loc);
				MessageManager.sendRemarkMessage(event.getPlayer(), "2nd position set! Coordinates: X" + loc.getBlockX() + " Y" + loc.getBlockY() + " Z" + loc.getBlockZ());
				return;
			}
		}
	}
	
	
	public static ItemStack createItem(){
		ItemStack i = new ItemStack(Material.WOOD_HOE, 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Region Selector");
		i.setItemMeta(im);
		return i;
	}
	
	
	
}