package me.Haeseke1.Alliances.Mounts;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.inventory.ItemStack;

import me.Haeseke1.Alliances.Mounts.Commands.MountCommand;

public class Mount {

	public Player owner;
	public EntityType type;
	public String name;
	public String mountType;
    public Horse horse;
	
	public Mount(Player owner, String mountType){
		this.owner = owner;
		this.type = EntityType.HORSE;
		this.mountType = mountType;
	}
	
	public void spawnMount(){
		World world = owner.getWorld();
		Entity entity = world.spawnEntity(owner.getLocation(), type);
    	Horse horse = (Horse) entity;
    	horse.setAdult();
    	horse.setTamed(true);
    	horse.setPassenger(owner);
	    switch(this.mountType){
	    case "diamond":
	    	this.name = ChatColor.AQUA + this.owner.getName() + "'s mount";
	    	horse.getInventory().setArmor(new ItemStack(Material.DIAMOND_BARDING));
	    	break;
	    case "gold":
	    	this.name = ChatColor.GOLD + this.owner.getName() + "'s mount";
	    	horse.getInventory().setArmor(new ItemStack(Material.GOLD_BARDING));
	    	break;
	    case "donkey":
	    	this.name = ChatColor.GRAY + this.owner.getName() + "'s mount";
	    	horse.getInventory().setArmor(new ItemStack(Material.CHEST));
	    	horse.setVariant(Variant.DONKEY);
	    	horse.setCarryingChest(true);
	    	break;
	    default:
	    	this.name = null;
	    	break;
	    }
    	horse.setOwner(owner);
	    horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
	    horse.setCustomName(this.name);
	    horse.setCustomNameVisible(true);
	    this.horse = horse;
	}
	
	public void removeMount(){
		this.horse.setHealth(0);
		this.horse = null;
		MountCommand.mounts.remove(owner);
	}
	
	public boolean mobIsSpawned(){
		if(this.horse == null) return false;
		return true;
	}

}