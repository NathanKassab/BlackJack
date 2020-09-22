package info.spicyclient.blackjack;

import java.util.Scanner;

import info.spicyclient.blackjack.player.Player;
import info.spicyclient.blackjack.player.type.Human;

public class Main {
	
	public static GameManager game;
	
	public static void main(String[] args) {
		
		Player.addBotNames();
		game = new GameManager();
		
		// Just use scanner for now and add startup gui later
		Scanner s = new Scanner(System.in);
		
		System.out.println("Type your name");
		game.Start(new Human(s.nextLine()));
		
	}
	
	public static GameManager getGameManager() {
		return game;
	}
	
}
