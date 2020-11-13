package info.spicyclient.blackjack.gui;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import info.spicyclient.blackjack.GameManager;
import info.spicyclient.blackjack.cards.Card;
import info.spicyclient.blackjack.gui.eventListeners.*;
import info.spicyclient.blackjack.player.types.Human;

public class PlayerGui {
	
	private JLabel yourHand = new JLabel("Your hand:"), dealersHand = new JLabel("Dealer's hand:");
	
	public PlayerGui(Human player) {
		
		window.setSize(750, 600);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panelLayout = new GridLayout(5 + player.hand.size() + player.hand.size(), 1);
		panel = new JPanel(panelLayout);
		window.setAlwaysOnTop(true);
		
		this.player = player;
		stringName = player.name;
		this.name.setText(stringName);
		this.name.setHorizontalAlignment(0);
		this.name.setFont(Fonts.title1);
		
		this.bal.setText("$" + player.balance);
		this.bal.setHorizontalAlignment(0);
		this.bal.setFont(Fonts.subTitle1);
		
		hit_button = new JButton();
		hit_button.addActionListener(new ButtonHit());
		hit_button.setText("Hit");
		hit_button.setEnabled(true);
		hit_button.setFont(Fonts.subTitle1);
		
		stand_button = new JButton();
		stand_button.addActionListener(new ButtonStand());
		stand_button.setText("Stand");
		stand_button.setEnabled(true);
		stand_button.setFont(Fonts.subTitle1);
		
		double_button = new JButton();
		double_button.addActionListener(new ButtonDouble());
		double_button.setText("Double");
		double_button.setEnabled((player.bet * 2) <= player.balance);
		double_button.setFont(Fonts.subTitle1);
		
		split_button = new JButton();
		split_button.addActionListener(new ButtonSplit());
		split_button.setText("Split");
		split_button.setEnabled(false);
		split_button.setFont(Fonts.subTitle1);
		
		addStuffTOGui();
		
		if (player.hand.size() == 2 && player.getValueOfHand() == 20 && player.hand.get(0).type == player.hand.get(1).type) {
			split_button.setEnabled(true);
		}
		
		window.add(panel);
		
	}
	
	public void addStuffTOGui() {
		
		try {
			panelLayout = new GridLayout((16 + player.hand.size() + GameManager.getGameManager().dealer.dealersHand.size()) / 2, 2);
		} catch (NullPointerException e) {
			panelLayout = new GridLayout(6 + player.hand.size(), 1);
		}
		panel.setLayout(panelLayout);
		
		panel.add(this.name);
		panel.add(this.bal);
		panel.add(hit_button);
		panel.add(stand_button);
		panel.add(double_button);
		panel.add(split_button);
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
		this.name.setFont(Fonts.title1);
		
		this.bal.setText("$" + player.balance);
		this.bal.setHorizontalAlignment(0);
		this.bal.setFont(Fonts.subTitle1);
		
		hit_button = new JButton();
		hit_button.addActionListener(new ButtonHit());
		hit_button.setText("Hit");
		hit_button.setEnabled(true);
		hit_button.setFont(Fonts.subTitle1);
		
		stand_button = new JButton();
		stand_button.addActionListener(new ButtonStand());
		stand_button.setText("Stand");
		stand_button.setEnabled(true);
		stand_button.setFont(Fonts.subTitle1);
		
		double_button = new JButton();
		double_button.addActionListener(new ButtonDouble());
		double_button.setText("Double");
		double_button.setEnabled((player.bet * 2) <= player.balance);
		double_button.setFont(Fonts.subTitle1);
		
		split_button = new JButton();
		split_button.addActionListener(new ButtonSplit());
		split_button.setText("Split");
		split_button.setEnabled(false);
		split_button.setFont(Fonts.subTitle1);
		
		addStuffTOGui();
		
		if (player.isBusted()) {
			name.setText("BUSTED!");
			panel.remove(hit_button);
			panel.remove(stand_button);
			panel.remove(double_button);
			panel.remove(split_button);
		}
		
		if (player.hand.size() == 2 && player.getValueOfHand() == 20) {
			split_button.setEnabled(true);
		}
		
		window.add(panel);
		window.setVisible(true);
		
	}
	
	public JFrame window = new JFrame("Blackjack");
	public JPanel panel = new JPanel(new GridLayout(1, 2));
	
	public GridLayout panelLayout = new GridLayout(5, 1);
	
	public JButton hit_button = new JButton();
	public JButton stand_button = new JButton();
	public JButton double_button = new JButton();
	public JButton split_button = new JButton();
	
	public JLabel name = new JLabel();
	public JLabel bal = new JLabel();
	
	public Human player;
	
	public String stringName = "Sample Name";
	
}
