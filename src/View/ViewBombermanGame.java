package View;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewBombermanGame {
	
	public ViewBombermanGame(PanelBomberman panelBomberman) {
		JFrame window = new JFrame("Game");
		window.add(panelBomberman);
		window.setSize(panelBomberman.getSizeX() * 50, panelBomberman.getSizeY() * 50);
		window.setLocationRelativeTo(null);
		// affiche l'interface
		window.setVisible(true);
	}

}
