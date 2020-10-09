package info.spicyclient.blackjack;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import info.spicyclient.blackjack.cards.Card;
import info.spicyclient.blackjack.cards.Suit;
import info.spicyclient.blackjack.cards.Type;
import info.spicyclient.blackjack.gui.Gui;
import info.spicyclient.blackjack.player.*;
import info.spicyclient.blackjack.player.type.*;

public class GameManager {
	
	public static GameManager gameManager;
	
	public ArrayList<Player> players = new ArrayList<>();
	
	public Dealer dealer;
	
	public int currentPlayer = -1;
	
	public boolean gameStarted = false;
	
	public void Start(int decks, Player... humans) {
		
		gameManager = this;
		
		for (Player h : humans) {
			
			players.add(h);
			
		}
		
		for (int i = 0; i < 3 - humans.length; i++) {
			
			players.add(new Bot());
			
		}
		
		dealer = new Dealer();
		players.add(dealer);
		
		for (int i = players.size(); i < 4; i++) {
			
			
			
		}
		
		GenerateDeck(decks);
		ShuffleCards();
		StartRound();
		
		Gui.showStartScreen();
		
	}
	
	public static GameManager getGameManager() {
		return gameManager;
	}

	public static void setGameManager(GameManager gameManager) {
		GameManager.gameManager = gameManager;
	}

	private void StartRound() {
		
		
		
	}
	
	public Player getCurrentPlayer() {
		
		return this.players.get(currentPlayer);
		
	}
	
	public void ShuffleCards() {
		
		if (dealer.hand.size() == 0) {
			return;
		}
		
		for (int i = 0; i < dealer.hand.size() * 5; i++) {
			
			Random random = new Random();
			
			Card c = dealer.hand.get(random.nextInt(dealer.hand.size() - 1));
			
			dealer.hand.remove(dealer.hand.indexOf(c));
			dealer.hand.add(c);
			
		}
		
	}
	
	public void GenerateDeck(int amountOfDecks) {
		
		dealer.hand.clear();
		
		for (int i = 0; i < amountOfDecks; i++) {
			
			for (Type t : Type.values()) {
				
				for (Suit s : Suit.values()) {
					
					dealer.hand.add(new Card(s, t));
					
				}
				
			}
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void cyclePlayers() {
		
		currentPlayer++;
		
		if (currentPlayer > players.size() - 1) {
			
			
			
		}else {
			
			if (players.get(currentPlayer) instanceof Human) {
				
				Human player = (Human) players.get(currentPlayer);
				
				dealer.hit(player);
				dealer.hit(player);
				
				player.gui.addStuffTOGui();
				
				Gui.window.setVisible(false);
				int x = Gui.window.getX(), y = Gui.window.getY(), fullscreen = Gui.window.getExtendedState();
				Dimension size = Gui.window.size();
				Gui.window = player.gui.window;
				Gui.window.setLocation(x, y);
				Gui.window.setExtendedState(fullscreen);
				Gui.window.setSize(size);
				Gui.window.setVisible(true);
				
			}else {
				
				
				
			}
			
		}
		
	}
	
}
