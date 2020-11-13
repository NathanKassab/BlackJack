package info.spicyclient.blackjack.botUtils;

import info.spicyclient.blackjack.player.Player;
import info.spicyclient.blackjack.player.types.Bot;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import info.spicyclient.blackjack.GameManager;
import info.spicyclient.blackjack.cards.Card;
import info.spicyclient.blackjack.gui.Fonts;
import info.spicyclient.blackjack.gui.eventListeners.*;

public class Utils {
	
	public static PlayerGui playerGui = null;
	
	public void fakePlayerScreen(Player player) {
		
		playerGui = new PlayerGui(player);
		playerGui.refreshGui();
		GameManager.getGameManager().fakeBotWindow = playerGui.window;
		
	}
	
	public static void makeBotMoves(Bot bot) {
		
		GameManager gm = GameManager.getGameManager();
		
		CopyOnWriteArrayList<Card> dealersHand = gm.dealer.dealersHand;
		int dealersHandValue = dealersHand.get(0).getValue() + 10;
		
		Random random = new Random();
		
		// These are out of 100
		int hitChance = 50, risk = 0;
		
		if (bot.getValueOfHand() > 18) {
			risk += 95;
		}
		else if (bot.getValueOfHand() > 16) {
			risk += 70;
		}
		else if (bot.getValueOfHand() > 11) {
			risk += 45;
		}
		else if (bot.getValueOfHand() <= 11) {
			risk += 0;
		}
		
		if (dealersHandValue <= 15) {
			hitChance -= 45;
		}
		
		if (bot.getValueOfHand() < dealersHandValue) {
			hitChance += 60;
		}
		
		if (risk <= 25) {
			hitChance += 20;
		}
		else if (risk <= 10) {
			hitChance += 40;
		}
		
		if (bot.getValueOfHand() == 21) {
			botStand(bot);
		}
		else if (risk == 0) {
			botHit(bot);
		}
		else if (hitChance >= 100){
			botHit(bot);
		}else {
			
			int willingRiskLevel = 50 + random.nextInt(50);
			
			if (risk <= willingRiskLevel) {
				
				if (random.nextInt(100) <= hitChance) {
					botHit(bot);
				}else {
					botStand(bot);
				}
				
			}else {
				botStand(bot);
			}
			
		}
		
	}
	
	private static void botHit(Bot bot) {
		GameManager.getGameManager().dealer.hit(bot);
		makeBotMoves(bot);
		playerGui.refreshGui();
	}
	
	private static void botStand(Bot bot) {
		bot.doneWithTurn = true;
		playerGui.refreshGui();
	}
	
	public class PlayerGui {
		
		private JLabel yourHand = new JLabel("Bot's hand:"), dealersHand = new JLabel("Dealer's hand:");
		
		public PlayerGui(Player player) {
			
			window.setSize(750, 600);
			window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			panelLayout = new GridLayout(5 + player.hand.size() + player.hand.size(), 1);
			panel = new JPanel(panelLayout);
			window.setAlwaysOnTop(true);
			
			this.player = player;
			stringName = player.name;
			this.name.setText(stringName);
			this.name.setHorizontalAlignment(0);
			this.name.setFont(Fonts.subTitle1);
			
			this.bal.setText("$" + player.balance);
			this.bal.setHorizontalAlignment(0);
			this.bal.setFont(Fonts.subTitle1);
			
			stand_button = new JButton();
			stand_button.addActionListener(new ButtonStand());
			stand_button.setText("Stand");
			stand_button.setEnabled(true);
			stand_button.setFont(Fonts.subTitle1);
			
			addStuffTOGui();
			
			window.add(panel);
			
		}
		
		public void addStuffTOGui() {
			
			try {
				panelLayout = new GridLayout(3 + player.hand.size(), 2);
			} catch (NullPointerException e) {
				panelLayout = new GridLayout(6 + player.hand.size(), 1);
			}
			panel.setLayout(panelLayout);
			
			panel.add(this.name);
			panel.add(this.bal);
			panel.add(stand_button);
			panel.add(new JLabel(""));
			panel.add(yourHand);
			panel.add(dealersHand);
			
			yourHand.setHorizontalAlignment(0);
			yourHand.setFont(Fonts.subTitle1);
			
			dealersHand.setHorizontalAlignment(0);
			dealersHand.setFont(Fonts.subTitle1);
			
			boolean displayDealerCard = true, displayNothing = false;
			
			for (Card c: player.hand) {
				
				JLabel card = new JLabel();
				card.setText(c.getName());
				card.setFont(Fonts.subTitle1);
				card.setHorizontalAlignment(0);
				
				panel.add(card);
				card = new JLabel();
				if (displayDealerCard) {
					card.setText(GameManager.getGameManager().dealer.dealersHand.get(0).getName());
					displayDealerCard = false;
				}
				else if (!displayNothing) {
					Random random = new Random();
					String noLook = "";
					int r = random.nextInt(10);
					for (int i = 0; i < r; i++) {
						noLook += "*";
					}
					try {
						card.setText(GameManager.getGameManager().dealer.dealersHand.get(player.hand.indexOf(c)).getName().replaceAll(".", "*") + noLook);
					} catch (IndexOutOfBoundsException e) {
						card.setText("");
					}
					displayNothing = true;
				}else {
					card.setText("");
				}
				card.setFont(Fonts.subTitle1);
				card.setHorizontalAlignment(0);
				
				panel.add(card);
				
			}
			
		}
		
		// Refreshes the playerGui
		public void refreshGui() {
			
			window.setVisible(false);
			window.remove(panel);
			window.setSize(750, 600);
			window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			panelLayout = new GridLayout(5 + player.hand.size() + player.hand.size(), 1);
			panel = new JPanel(panelLayout);
			window.setAlwaysOnTop(true);
			
			stringName = player.name;
			this.name.setText(stringName);
			this.name.setHorizontalAlignment(0);
			this.name.setFont(Fonts.subTitle1);
			
			this.bal.setText("$" + player.balance);
			this.bal.setHorizontalAlignment(0);
			this.bal.setFont(Fonts.subTitle1);
			
			stand_button = new JButton();
			stand_button.addActionListener(new ButtonListener());
			
			if (((Bot)player).doneWithTurn) {
				stand_button.setText("Continue");
			}else {
				stand_button.setText("Take turn");
			}
			
			stand_button.setEnabled(true);
			stand_button.setFont(Fonts.subTitle1);
			
			addStuffTOGui();
			
			if (player.isBusted()) {
				name.setText("BUSTED!");
			}
			
			window.add(panel);
			window.setVisible(true);
			
		}
		
		public JFrame window = new JFrame("Blackjack");
		public JPanel panel = new JPanel(new GridLayout(1, 2));
		
		public GridLayout panelLayout = new GridLayout(5, 1);
		
		public JButton stand_button = new JButton();
		
		public JLabel name = new JLabel();
		public JLabel bal = new JLabel();
		
		public Player player;
		
		public String stringName = "Sample Name";
		
	}
	
	private class ButtonListener implements Action {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (GameManager.getGameManager().getCurrentPlayer() instanceof Bot) {
				
				Bot bot = (Bot) GameManager.getGameManager().getCurrentPlayer();
				
				if (bot.doneWithTurn) {
					bot.doneWithTurn = false;
					GameManager.getGameManager().cyclePlayers();
				}else {
					makeBotMoves(bot);
				}
				
			}
			
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
	
}
