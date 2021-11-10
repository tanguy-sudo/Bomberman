package Models;

import java.util.ArrayList;

import Controller.AbstractController;
import Controller.ControllerBombermanGame;
import Models.agent.Agent;
import Models.agent.BombermanAgent;
import Models.agent.FabriqueBomberman;
import Models.agent.FabriqueEnemy;
import Utils.AgentAction;
import Utils.ColorAgent;
import Utils.InfoAgent;
import Utils.InfoBomb;
import View.ViewCommand;

public class BombermanGame extends Game{
	private ArrayList<Agent> pListBombermanAgent;
	private ArrayList<Agent> pListBombermanEnemy;
	private ArrayList<InfoBomb> pListBomb;
	private InputMap pInputMap;

	public BombermanGame(int maxturn, InputMap inputMap) {
		super(maxturn);
		this.pInputMap = inputMap;
		this.pListBombermanAgent = new ArrayList<Agent>();
		this.pListBombermanEnemy = new ArrayList<Agent>();
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initializeGame() {
		FabriqueEnemy fabriqueEnemy = new FabriqueEnemy();
		FabriqueBomberman fabriqueBomberman = new FabriqueBomberman();
		
		for(InfoAgent infoAgent : this.pInputMap.getStart_agents()) {
			if(infoAgent.getType() == 'B'){
				System.out.println(infoAgent.getType() + " " + infoAgent.getColor());
				this.pListBombermanAgent.add(fabriqueBomberman.createAgent(infoAgent));
			}else {
				System.out.println(infoAgent.getType() + " " + infoAgent.getColor());
				this.pListBombermanEnemy.add(fabriqueEnemy.createAgent(infoAgent));		
			}
		}
	}

	@Override
	public boolean gameContinue() {
		// TODO Auto-generated method stub
		return true;
	}

}
