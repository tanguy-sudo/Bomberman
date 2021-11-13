package View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Models.BombermanGame;
import Models.InputMap;
import Utils.InfoAgent;
import Utils.InfoBomb;
import Utils.InfoItem;

public class ViewBombermanGame  implements PropertyChangeListener {
	private PanelBomberman pPanelBomberman;
	private JFrame window;
	
	public ViewBombermanGame(PanelBomberman panelBomberman) {
		this.pPanelBomberman = panelBomberman;
		window = new JFrame("Game");
		window.add(this.pPanelBomberman);
		window.setSize(this.pPanelBomberman.getSizeX() * 50, this.pPanelBomberman.getSizeY() * 50);
		window.setLocationRelativeTo(null);
		// affiche l'interface
		window.setVisible(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName() == "pGame") {
			BombermanGame game = (BombermanGame) evt.getNewValue();	
			this.pPanelBomberman.updateInfoGame(game.getBreakable_walls(), game.getListAgent(), game.getListItems(), game.getListBomb());
			window.setContentPane(pPanelBomberman);
		}
	}

}
