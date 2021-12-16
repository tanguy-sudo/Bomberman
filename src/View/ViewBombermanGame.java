package View;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Models.BombermanGame;
import Models.InputMap;
import Utils.InfoAgent;
import Utils.InfoBomb;
import Utils.InfoItem;

public class ViewBombermanGame  implements PropertyChangeListener, WindowListener  {
	private PanelBomberman pPanelBomberman;
	private JFrame window;
	
	public ViewBombermanGame(PanelBomberman panelBomberman) {
		this.pPanelBomberman = panelBomberman;
		window = new JFrame("Game");
		window.add(this.pPanelBomberman);
		window.setSize(this.pPanelBomberman.getSizeX() * 50, this.pPanelBomberman.getSizeY() * 50);
		window.setLocation(0, 0);
		// affiche l'interface
		window.setVisible(true);
		window.addWindowListener(this);

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName() == "pGame") {
			BombermanGame game = (BombermanGame) evt.getNewValue();	
			this.pPanelBomberman.updateInfoGame(game.getBreakable_walls(), game.getListAgent(), game.getListItems(), game.getListBomb());
			this.pPanelBomberman.repaint();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.gc();
		for (Window w : Window.getWindows()) {
		    w.dispose();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
