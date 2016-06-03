package org.outram.OOInfection;

import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.*;
import org.bukkit.event.player.*;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;



public final class EventListner implements Listener, EventListnerInterface {
	private PluginUtils plugin;
	private GameStateInterface gameState;
	
	//Constructor
	EventListner(PluginUtils pg) {
		this.plugin = pg;
		this.plugin.log("LoginListner constructor called");
		
		this.gameState = new GameState(this);
		this.gameState.reset();
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
		Player player = evt.getPlayer();
		this.gameState.playerJoined(player);
		
	    player.sendMessage("Welcome " + player.getName() + ": you have joined the game");  
	    this.plugin.log(player.getName() + " Just joined the game");
	}	
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent evt) {
		Player player = evt.getPlayer();
		this.gameState.playerLeft(player);
		
		this.plugin.log(player.getName() + " Just quit the game");
	}
	
//	@EventHandler
//	public void onPlayerDies(PlayerDeathEvent evt) {
//		if (evt.getEntityType()==EntityType.PLAYER) {
//			Player player = evt.getEntity();
//			this.plugin.log(player.getName() + " just died");
//			
//		} else {
//			this.plugin.log("A non-player just died");
//		}
//	}
	
	//Death handler
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeath(final EntityDeathEvent e) {
	    Entity ent = e.getEntity();							//entity being killed
	    EntityDamageEvent ede = ent.getLastDamageCause();	//cause
	    DamageCause dc = ede.getCause();
	    
	    
	    //Was this an entity damage event?
	    if (ede instanceof EntityDamageByEntityEvent) {
		    if (ent instanceof Player && dc == DamageCause.ENTITY_ATTACK) {
		        Player p = (Player)ent;
			    Entity damager = ((EntityDamageByEntityEvent)ede).getDamager();
			    if (damager instanceof Player) {
			    	this.gameState.playerWasKilledByPlayer(p, (Player)damager);
			    	
			    	this.plugin.log(damager.getName() + " just killed " + p.getDisplayName());
			    	damager.sendMessage("You killed " + p.getDisplayName());
			    } else {
			    	this.gameState.playerWasKilledByNonPlayer(p, damager);
			    }
		    } else if (ent instanceof Player) {
		    	this.gameState.playerWasKilledByNonEntity((Player)ent);
		    }
	    }

	}
	
		
	//Damage handler - used to decide what can damage a player
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent evt) {
		this.plugin.log("Entity Damaged an Entity");
		if (evt.getCause() == DamageCause.ENTITY_ATTACK) {
			
			Entity damager = evt.getDamager();
			Entity damaged = evt.getEntity();
			if ((damager instanceof Player) && (damaged instanceof Player)) {
				Player hitter = (Player)damager;
				Player hit    = (Player)damaged;
				if (!(this.gameState.playerCanDamagePlayer(hitter, hit))) {
					evt.setCancelled(true);	
				}
				
				hitter.sendMessage("You just hit " + hit.getDisplayName());
				hit.sendMessage("You were hit by " + hitter.getDisplayName());					
			}
			
		}
	}
}
