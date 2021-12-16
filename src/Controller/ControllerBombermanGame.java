package Controller;
import Models.*;
import Models.agent.Agent;
import Utils.AgentAction;
import Utils.ColorAgent;
import Utils.InfoAgent;
import View.PanelBomberman;
import View.ViewBombermanGame;
import View.ViewCommand;
import View.ViewStart;

import java.util.ArrayList;

public class ControllerBombermanGame extends AbstractController{
	private PanelBomberman pPanelBomberman;
	private ViewBombermanGame pViewBombermanGame;
	private InputMap pInputMap;
	
	public ControllerBombermanGame() {
		this.pMapName = "";
		this.pInputMap = null;
		this.pPanelBomberman = null;
		this.pViewBombermanGame = null;
		this.pGame = null;
		ViewStart viewstart = new ViewStart(this);
	}	
	
	public void lunchGame(String pathLayout, String niveau, String fileName) {
		try {
			this.pMapName = pathLayout;
			this.pInputMap = new InputMap(pathLayout);
			this.pPanelBomberman = new PanelBomberman(this.pInputMap.getSizeX(), 
													  this.pInputMap.getSizeY(), 
													  this.pInputMap.get_walls(), 
													  this.pInputMap.getStart_breakable_walls(), 
													  this.pInputMap.getStart_agents());
			this.pViewBombermanGame = new ViewBombermanGame(this.pPanelBomberman);
			
			ViewCommand viewCommand = new ViewCommand(this, fileName, niveau);
			
			this.pGame = new BombermanGame(10000, this.pInputMap, Integer.parseInt(niveau));
			this.pGame.addPropertyChangeListener(viewCommand);
			this.pGame.init();
			this.pGame.addPropertyChangeListener(this.pViewBombermanGame);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
