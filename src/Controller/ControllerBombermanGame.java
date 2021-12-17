package Controller;
import Models.*;
import Models.agent.Agent;
import Utils.AgentAction;
import Utils.ColorAgent;
import Utils.InfoAgent;
import View.PanelBomberman;
import View.ViewBombermanGame;
import View.ViewCommand;
import View.ViewEnd;
import View.ViewStart;

import java.util.ArrayList;

public class ControllerBombermanGame extends AbstractController{
	private PanelBomberman pPanelBomberman;
	private ViewBombermanGame pViewBombermanGame;
	private InputMap pInputMap;
	private ViewEnd pViewEnd;
	
	public ControllerBombermanGame() {
		this.pMapName = "";
		this.pInputMap = null;
		this.pPanelBomberman = null;
		this.pViewBombermanGame = null;
		this.pGame = null;
		this.pViewEnd = null;
		this.lunchViewStart();
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
			
			this.pGame = new BombermanGame(10000, this.pInputMap, Integer.parseInt(niveau), this);
			this.pGame.addPropertyChangeListener(viewCommand);
			this.pGame.init();
			this.pGame.addPropertyChangeListener(this.pViewBombermanGame);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	public void lunchViewEnd(int result, ArrayList<Agent> listAgentEnemy, ArrayList<Agent> listAgentAlly) {
		int countAgent = 0;
		int countEnemy = 0;
		
		for(Agent agent : listAgentAlly) {
			if(agent.getLiving()) countAgent = countAgent + 1;
		}
		for(Agent agent : listAgentEnemy) {
			if(agent.getLiving()) countEnemy = countEnemy + 1;
		}	
		if(pViewEnd == null) {
			this.pViewEnd = new ViewEnd(
					result, 
					listAgentEnemy.size() - countEnemy, 
					countEnemy,
					listAgentAlly.size() - countAgent, 
					countAgent, 
					this);
		}else {
			this.pViewEnd.setInformation(					
					result, 
					listAgentEnemy.size() - countEnemy, 
					countEnemy,
					listAgentAlly.size() - countAgent, 
					countAgent);
		}
	}
	
	public void lunchViewStart() {
		ViewStart viewStart = new ViewStart(this);
	}
}
