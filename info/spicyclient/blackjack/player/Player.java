package info.spicyclient.blackjack.player;

import java.util.ArrayList;
import java.util.Random;
import info.spicyclient.blackjack.cards.Card;
import info.spicyclient.blackjack.cards.Type;

public class Player {
	
	public Player(String name) {
		
		this.name = name;
		
	}
	
	public String name;
	public ArrayList<Card> hand = new ArrayList<>();
	
	public int getValueOfHand() {
		
		int number = 0;
		
		for (Card c : hand) {
			
			if (c.type == Type.ACE) {
				// Will check again later
			}else {
				number += c.getValue();
			}
			
		}
		
		// This is when we check again
		for (Card c : hand) {
			
			if (c.type == Type.ACE) {
				
				if (number + 10 > 21) {
					number += 1;
				}else {
					number += 10;
				}
				
			}
			
		}
		
		return number;
		
	}
	
	public void giveCard(Card card) {
		
		this.hand.add(card);
		
	}
	
	public boolean ifBusted() {
		return (getValueOfHand() > 21);
	}
	
	// Each bot or dealer will have one of these names
	public static ArrayList<String> botNames = new ArrayList<>();
	
	// Populates the botNames list
	public static void addBotNames() {
		
		botNames.clear();
		botNames.add("Jake");
		botNames.add("Mike");
		botNames.add("Bill");
		botNames.add("Ben");
		botNames.add("Dover");
		botNames.add("Donald");
		botNames.add("Richard");
		botNames.add("Dick");
		botNames.add("Ronald");
		botNames.add("Rick");
		botNames.add("Jason");
		botNames.add("Mally");
		botNames.add("Joe");
		botNames.add("Hughmunus");
		botNames.add("Mike Oxlong");
		botNames.add("Willy Wilickers");
		botNames.add("Ben Dover");
		botNames.add("Greg");
		
	}
	
	public static String getRandomName() {
		
		if (botNames.size() == 0) {
			
			return "Donald J Trump";
			
		}
		
		Random random = new Random();
		String name = Player.botNames.get(random.nextInt(Player.botNames.size() - 1));
		return name;
		
	}
	
}
