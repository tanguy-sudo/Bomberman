package Models;

import java.util.ArrayList;
import java.util.Random;
import Models.agent.Agent;
import Models.agent.FabriqueBomberman;
import Models.agent.FabriqueEnemy;
import Utils.AgentAction;
import Utils.InfoAgent;
import Utils.InfoBomb;
import Utils.InfoItem;
import Utils.StateBomb;
import View.PanelBomberman;

public class BombermanGame extends Game{
	
	private InputMap pInputMap;
	private boolean pBreakable_walls[][];
	private ArrayList<Agent> pListBombermanAgent;
	private ArrayList<Agent> pListBombermanEnemy;
	private ArrayList<InfoBomb> pListBomb;
	private ArrayList<InfoItem> pListItems;

	public BombermanGame(int maxturn, InputMap inputMap) {
		super(maxturn);
		this.pInputMap = inputMap;
		this.pBreakable_walls = inputMap.getStart_breakable_walls();
		this.pListBombermanAgent = new ArrayList<Agent>();
		this.pListBombermanEnemy = new ArrayList<Agent>();
		this.pListBomb = new ArrayList<InfoBomb>();
		this.pListItems = new ArrayList<InfoItem>();
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
	}

	@Override
	public void takeTurn(){
		StrategyMove strategyMove = new StrategyMove(this);
		for(Agent agent : this.pListBombermanAgent) {
			if(!isBlockOff(agent, strategyMove)) {
				AgentAction action = null;
				boolean next = true;
				while(next) {
					action = generateAction();
					if(strategyMove.isLegalMove(agent, action)) {
						strategyMove.moveAgent(agent, action);
						next = false;
					}
			
				}
			}
		}	
		for(Agent agent : this.pListBombermanEnemy) {
			if(!isBlockOff(agent, strategyMove)) {
				AgentAction action = null;
				boolean next = true;
				while(next) {
					action = generateAction();
					if(strategyMove.isLegalMove(agent, action)) {
						strategyMove.moveAgent(agent, action);
						next = false;
					}
			
				}
			}
		}	
		pSupport.firePropertyChange("pGame", null, this);
	}

	@Override
	public void initializeGame() {
		FabriqueEnemy fabriqueEnemy = new FabriqueEnemy();
		FabriqueBomberman fabriqueBomberman = new FabriqueBomberman();
		
		for(InfoAgent infoAgent : this.pInputMap.getStart_agents()) {
			if(infoAgent.getType() == 'B'){
				this.pListBombermanAgent.add(fabriqueBomberman.createAgent(infoAgent));
			}else {
				this.pListBombermanEnemy.add(fabriqueEnemy.createAgent(infoAgent));		
			}
		}
	}

	@Override
	public boolean gameContinue() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void addBomb(int x, int y) {
		this.pListBomb.add(new InfoBomb(x, y, 1, StateBomb.Step0));
	}
	
	public InputMap getInputMap() {
		return this.pInputMap;
	}
	
	private AgentAction generateAction() {
		Random r = new Random();
		switch(r.nextInt(4)) {
			case 0:
				return AgentAction.MOVE_DOWN;
			case 1:
				return AgentAction.MOVE_LEFT;
			case 2:
				return AgentAction.MOVE_RIGHT;
			case 3:
				return AgentAction.MOVE_UP;
		}
		return null;
	}
	
	public boolean[][] getBreakable_walls() {
		return this.pBreakable_walls;
	}
	
	public ArrayList<InfoAgent> getListAgent(){
		ArrayList<InfoAgent> listAgent = new ArrayList<InfoAgent>();		
		for(Agent agent : this.pListBombermanAgent) {
			listAgent.add(agent.getAgent());
		}
		for(Agent agent : this.pListBombermanEnemy) {
			listAgent.add(agent.getAgent());
		}
		return listAgent;
	}
	
	public ArrayList<InfoBomb> getListBomb() {
		return this.pListBomb;
	}
	
	public ArrayList<InfoItem> getListItems() {
		return this.pListItems;
	}
	
	public boolean isBlockOff(Agent agent, StrategyMove strategyMove) {
		if(strategyMove.isLegalMove(agent, AgentAction.MOVE_DOWN)
				|| strategyMove.isLegalMove(agent, AgentAction.MOVE_LEFT)
				|| strategyMove.isLegalMove(agent, AgentAction.MOVE_UP)
				|| strategyMove.isLegalMove(agent, AgentAction.MOVE_RIGHT)) return false;
		else return true;
	}

}
