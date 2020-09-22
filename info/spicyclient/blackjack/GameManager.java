package info.spicyclient.blackjack;

import java.util.ArrayList;
import java.util.Random;

import info.spicyclient.blackjack.cards.Card;
import info.spicyclient.blackjack.cards.Suit;
import info.spicyclient.blackjack.cards.Type;
import info.spicyclient.blackjack.player.Player;
import info.spicyclient.blackjack.player.type.*;

public class GameManager {
	
	public ArrayList<Player> players = new ArrayList<>();
	
	public Dealer dealer;
	
	public int currentPLayer = 0;
	
	public void Start(Player... humans) {
		
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
		
		GenerateDeck(5);
		ShuffleCards();
		
		for (Player p : this.players) {
			
			System.out.println(p.name);
			
		}
		
	}
	
	public void hit(Player player) {
		
		if (player.equals(this.players.get(currentPLayer))) {
			
			dealer.hit(player);
			
		}
		
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
	
}
