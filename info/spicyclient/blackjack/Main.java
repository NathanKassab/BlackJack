package info.spicyclient.blackjack;

import java.util.Scanner;

import info.spicyclient.blackjack.player.Player;
import info.spicyclient.blackjack.player.type.Human;

public class Main {
	
	public static GameManager game;
	
	public static void main(String[] args) {
		
		Player.addBotNames();
		game = new GameManager();
		
		// Just use string for now and add startup gui later
		game.Start(new Human("Nathan"));
		
	}
	
	public static GameManager getGameManager() {
		return game;
	}
	
}
