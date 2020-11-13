package info.spicyclient.blackjack.player.types;

import java.util.concurrent.CopyOnWriteArrayList;

import info.spicyclient.blackjack.cards.Card;
import info.spicyclient.blackjack.player.Player;

public class Dealer extends Bot {
	
	public CopyOnWriteArrayList<Card> dealersHand = new CopyOnWriteArrayList<>();
	
	public CopyOnWriteArrayList<Card> getDealersHand() {
		return dealersHand;
	}

	public void setDealersHand(CopyOnWriteArrayList<Card> dealersHand) {
		this.dealersHand = dealersHand;
	}

	public Dealer() {
		super();
	}
	
	public void hit(Player player) {
		
		if (player instanceof Dealer) {
			((Dealer)player).dealersHand.add(this.hand.get(0));
			this.hand.remove(0);
			return;
		}
		
		player.giveCard(this.hand.get(0));
		this.hand.remove(0);
		
	}
	
}
