package Controller;
import Models.*;
import View.PanelBomberman;
import View.ViewBombermanGame;

public class ControllerBombermanGame {
	private PanelBomberman pPanelBomberman;
	private ViewBombermanGame pViewBombermanGame;
	private InputMap pInputMap;
	
	public ControllerBombermanGame(String layout) {
	
		try {
			this.pInputMap = new InputMap(layout);
			this.pPanelBomberman = new PanelBomberman(this.pInputMap.getSizeX(), 
													  this.pInputMap.getSizeY(), 
													  this.pInputMap.get_walls(), 
													  this.pInputMap.getStart_breakable_walls(), 
													  this.pInputMap.getStart_agents());
			this.pViewBombermanGame = new ViewBombermanGame(this.pPanelBomberman);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
