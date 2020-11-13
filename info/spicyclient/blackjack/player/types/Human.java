package info.spicyclient.blackjack.player.types;

import info.spicyclient.blackjack.gui.PlayerGui;
import info.spicyclient.blackjack.player.Player;

public class Human extends Player {

	public Human(String name) {
		super(name);
	}
	
	public PlayerGui gui = new PlayerGui(this);
	
}
