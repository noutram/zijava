package org.outram.OOInfection;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


// https://www.spigotmc.org/wiki/how-to-learn-the-spigot-api/
// https://hub.spigotmc.org/javadocs/spigot/
// https://docs.google.com/document/d/1RABwEM8FNPHwREOtH3AjTKsPt0jNsvGqb0okPn5-5TY/edit#

interface PluginUtils {
	public void log(String m);
}

public class Main extends JavaPlugin implements PluginUtils {
	
	//Event handlers
	public void onEnable() {
		getLogger().info("OOInfection Plugin is Enabled!");
		
		//Register listeners
		getServer().getPluginManager().registerEvents(new EventListner(this), this);
	}	

	public void onDisable() {
		getLogger().info("OOInfection Plugin is disabled!");
	}	
	
	public void onLoad() {
		getLogger().info("OOInfection Plugin is loaded");
	}	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("hello")) {
			if (sender instanceof Player) {
				sender.sendMessage(ChatColor.GREEN + "W" + ChatColor.RED + ChatColor.ITALIC + "orl§dd");	
			} else {
				sender.sendMessage("Hello Console");
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("whereami")) {
			if (sender instanceof Player) {
				Player p = (Player)sender;
				Location loc = p.getLocation();
				p.sendMessage("Your location: x=" + String.format("%3.1f", loc.getX())  + " y=" + String.format("%3.1f", loc.getY()) + " z=" + String.format("%3.1f", loc.getZ()) );
			}
		}
	    return false;
	}

	public void log(String m) {
		getLogger().info(m);
	}
	
}



