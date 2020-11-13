package info.spicyclient.blackjack.player.types;

import info.spicyclient.blackjack.player.Player;

public class Bot extends Player {
	
	public boolean doneWithTurn = false;
	
	public Bot() {
		super(getRandomName() + " - [BOT]");
	}

}
