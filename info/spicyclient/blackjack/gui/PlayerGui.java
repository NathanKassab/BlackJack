package info.spicyclient.blackjack.gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import info.spicyclient.blackjack.cards.Card;
import info.spicyclient.blackjack.gui.eventListeners.*;
import info.spicyclient.blackjack.player.type.Human;

public class PlayerGui {
	
	public PlayerGui(Human player) {
		
		window.setSize(450, 600);
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
		
		window.add(panel);
		
	}
	
	public void addStuffTOGui() {
		
		panelLayout = new GridLayout(6 + player.hand.size(), 1);
		panel.setLayout(panelLayout);
		
		panel.add(this.name);
		panel.add(this.bal);
		panel.add(hit_button);
		panel.add(stand_button);
		panel.add(double_button);
		panel.add(split_button);
		
		for (Card c: player.hand) {
			
			JLabel card = new JLabel();
			card.setText(c.getName());
			card.setFont(Fonts.subTitle1);
			card.setHorizontalAlignment(0);
			
			panel.add(card);
			
		}
		
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
