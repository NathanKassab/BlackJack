package info.spicyclient.blackjack.gui.eventListeners;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

import info.spicyclient.blackjack.GameManager;
import info.spicyclient.blackjack.player.Player;
import info.spicyclient.blackjack.player.types.Human;

public class ButtonStand implements Action {

	@Override
	public void actionPerformed(ActionEvent e) {
		GameManager gm = GameManager.getGameManager();
		Player player = (Player) gm.getCurrentPlayer();
		gm.cyclePlayers();
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}
