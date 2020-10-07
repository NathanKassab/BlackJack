package info.spicyclient.blackjack.gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import info.spicyclient.blackjack.gui.eventListeners.ButtonHit;
import info.spicyclient.blackjack.player.type.Human;

public class PlayerGui {
	
	public PlayerGui(Human player) {
		
		window.setSize(450, 600);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Panel = new JPanel(new GridLayout(10, 4));
		window.setAlwaysOnTop(true);
		
		this.player = player;
		stringName = player.name;
		this.name.setText(stringName);
		this.name.setHorizontalAlignment(0);
		this.name.setFont(Fonts.title1);
		
		hit = new JButton();
		hit.addActionListener(new ButtonHit());
		hit.setText("hit");
		hit.setEnabled(false);
		
		Panel.add(this.name);
		Panel.add(hit);
		
		window.add(Panel);
		
	}
	
	public JFrame window = new JFrame("Blackjack");
	public JPanel Panel = new JPanel(new GridLayout(1, 2));
	
	public JButton hit = new JButton();
	public JLabel name = new JLabel();
	
	public Human player;
	
	public String stringName = "Sample Name";
	
}
