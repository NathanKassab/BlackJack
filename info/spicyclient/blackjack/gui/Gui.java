package info.spicyclient.blackjack.gui;

import java.awt.GridLayout;

import javax.swing.*;

import info.spicyclient.blackjack.gui.eventListeners.ButtonStart;

public class Gui {
	
	public static JFrame window = new JFrame("Blackjack");
	
	public static void showStartScreen() {
		
		window.setSize(450, 600);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(1, 2));
		window.setAlwaysOnTop(true);
		
		JButton start = new JButton("Start");
		start.addActionListener(new ButtonStart());
		panel.add(start);
		
		window.add(panel);
		window.setVisible(true);
		
	}
	
}
