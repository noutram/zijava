package org.outram.OOInfection;

import java.util.Set;
import java.util.Vector;

import org.bukkit.entity.Player;

public class GameState implements GameStateInterface {
	private Set<Player> humans;				//All humans
	private Set<Player> zombies;				//All zombies
	private Set<Player> originalZombies;		//Original zombies
	
	private EventListnerInterface eventListner;
		
	GameState(EventListnerInterface el) {
		//Constructor
		this.eventListner = el;
	}
	
	
	
}
