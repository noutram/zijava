package org.outram.OOInfection;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface GameStateInterface {
	void reset();										//Everyone is a zombie
	void assignOriginalZombies();						//zombies at the start of game
	void playerJoined(Player p);						//Add player to game
	void playerLeft(Player p);							//Remove player from game
	void playerWasKilledByPlayer(Player killed, Player killer);			//PVP Kill
	void playerWasKilledByNonPlayer(Player killed, Entity killer);		//NPC Kill
	void playerWasKilledByNonEntity(Player killed);						//Other cause of death
	boolean playerCanDamagePlayer(Player hitter, Player hit);			//PVP hit
}
