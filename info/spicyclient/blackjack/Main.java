package info.spicyclient.blackjack;

import info.spicyclient.blackjack.player.Player;
import info.spicyclient.blackjack.player.types.Human;

public class Main {
	
	public static GameManager game;
	
	public static void main(String[] args) {
		
		Player.addBotNames();
		game = new GameManager();
		
		// Just use string for now and add startup gui later
		game.Start(10, new Human("Nathan"));
		
	}
	
}
