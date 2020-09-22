package info.spicyclient.blackjack;

import info.spicyclient.blackjack.player.Player;

public class Main {
	
	public static GameManager game;
	
	public static void main(String[] args) {
		
		Player.addBotNames();
		game = new GameManager();
		game.Start();
		
	}
	
	public static GameManager getGameManager() {
		return game;
	}
	
}
