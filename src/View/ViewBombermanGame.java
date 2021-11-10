package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewBombermanGame {
	private PanelBomberman pPanelBomberman;
	
	public ViewBombermanGame(PanelBomberman panelBomberman) {
		this.pPanelBomberman = panelBomberman;
		JFrame window = new JFrame("Game");
		window.add(this.pPanelBomberman);
		window.setSize(this.pPanelBomberman.getSizeX() * 50, this.pPanelBomberman.getSizeY() * 50);
		window.setLocationRelativeTo(null);
		// affiche l'interface
		window.setVisible(true);
	}

}
