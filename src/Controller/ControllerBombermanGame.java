package Controller;
import Models.*;
import Models.agent.Agent;
import Utils.AgentAction;
import Utils.ColorAgent;
import Utils.InfoAgent;
import View.PanelBomberman;
import View.ViewBombermanGame;
import View.ViewCommand;

import java.util.ArrayList;

public class ControllerBombermanGame extends AbstractController{
	private PanelBomberman pPanelBomberman;
	private ViewBombermanGame pViewBombermanGame;
	private InputMap pInputMap;
	private BombermanGame mBombermanGame;
	
	public ControllerBombermanGame(String layout) {
	
		try {

			this.pInputMap = new InputMap(layout);
			this.pGame = new BombermanGame(5, this.pInputMap);
			ViewCommand viewCommand = new ViewCommand(this);
			this.pGame.addPropertyChangeListener(viewCommand);
			this.pGame.init();
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
	
	public InputMap getInputMap() {
		return this.pInputMap;
	}
}
