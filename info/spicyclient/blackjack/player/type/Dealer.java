package info.spicyclient.blackjack.player.type;

import info.spicyclient.blackjack.player.Player;

public class Dealer extends Bot {

	public Dealer() {
		super();
	}
	
	public void hit(Player player) {
		
		player.giveCard(this.hand.get(0));
		this.hand.remove(0);
		
	}
	
}
