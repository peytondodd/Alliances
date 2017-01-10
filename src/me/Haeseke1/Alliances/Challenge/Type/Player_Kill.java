package me.Haeseke1.Alliances.Challenge.Type;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.Haeseke1.Alliances.Challenge.Challenge;
import me.Haeseke1.Alliances.Challenge.challengeType;
import me.Haeseke1.Alliances.Economy.Coins;
import me.Haeseke1.Alliances.Utils.MessageManager;

public class Player_Kill implements Listener{
	
	@EventHandler
	private void onEntityKill(EntityDeathEvent event){
		for(Challenge challenge : Challenge.challenges){
			if(challenge.type.equals(challengeType.Player_Kill)){
				EntityDamageEvent devent = event.getEntity().getLastDamageCause();
				if(devent.getCause().equals(DamageCause.ENTITY_ATTACK)){
					if(event.getEntity().getKiller() != null && event.getEntity().getType().equals(EntityType.PLAYER)){
						Player player = event.getEntity().getKiller();
						if(!challenge.done.contains(player.getUniqueId())){
							if(challenge.points.containsKey(player.getUniqueId())){
								if(challenge.points.get(player.getUniqueId()) >= challenge.max_Points){
									Coins.addPlayerCoins(player, challenge.reward);
									MessageManager.sendInfoMessage(player, "Challenge " + challenge.name + " is completed, your reward is " + challenge.reward + " coins!");
									challenge.done.add(player.getUniqueId());
									return;
								}
								challenge.points.replace(player.getUniqueId(), challenge.points.get(player.getUniqueId()) + 1);
							}else{
								challenge.points.put(player.getUniqueId(), (double) 1);
							}
						}
					}
				}
			}
		}
	}

}