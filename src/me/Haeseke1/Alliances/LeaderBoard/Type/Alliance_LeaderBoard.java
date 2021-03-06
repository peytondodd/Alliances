package me.Haeseke1.Alliances.LeaderBoard.Type;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;

import me.Haeseke1.Alliances.Alliance.Alliance;
import me.Haeseke1.Alliances.LeaderBoard.Head_Board;
import me.Haeseke1.Alliances.LeaderBoard.LeaderBoard;
import me.Haeseke1.Alliances.LeaderBoard.Place;
import me.Haeseke1.Alliances.Main.Main;
import net.md_5.bungee.api.ChatColor;

public class Alliance_LeaderBoard extends LeaderBoard{
	
	public static Alliance_LeaderBoard alli_Leaderboard;
	
	public Alliance_LeaderBoard(List<Place> places) {
		super(places);
		alli_Leaderboard = this;
	}
	
	public void addPlace(int location, Head_Board hb){
		places.add(new Place(ChatColor.RED + "---Alliance---", null, ChatColor.AQUA + "0", hb, location));
	}
	
	public void update(){
		for(Place place : this.places){
			place.setKeeper(null);
			place.setScore(ChatColor.AQUA + "0");
		}
		Collections.sort(Main.alliances, new Alliance());
		Collections.reverse(Main.alliances);
		for(Place place : this.places){
			if(Main.alliances.size() >= place.location){
				place.setKeeper(Main.alliances.get(place.location - 1).getName());
				place.setScore(ChatColor.AQUA + "" + Main.alliances.get(place.location - 1).getScore());
				place.head_board.setOwner(Bukkit.getOfflinePlayer(Main.alliances.get(place.location - 1).getOwner()).getName());
			}
		}
	}
	
	
}
