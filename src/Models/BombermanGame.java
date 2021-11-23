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
		
		for(InfoBomb bomb : this.pListBomb) {
			if(bomb.getStateBomb() == StateBomb.Boom) {
				this.pListBomb.remove(bomb);
			}
		}
		
		for(InfoBomb bomb : this.pListBomb) {
			bomb.setStateBomb(nextState(bomb.getStateBomb()));
		}
		
		Strategy strategy = new SimpleStrategy();
		for(Agent agent : this.pListBombermanAgent) {
			if(!strategy.isBlockOff(agent, this)) {
				AgentAction action = null;
				boolean next = true;
				while(next) {
					if(agent.getAgent().getType() == 'B') action = strategy.generateActionWithBomb();
					else action = strategy.generateAction();
					
					if(action == AgentAction.PUT_BOMB && !existBomb(agent.getAgent().getX(), agent.getAgent().getY())) {
						putBomb(agent.getAgent().getX(), agent.getAgent().getY());
						next = false;
					}
					else if(isLegalMove(agent, action)) {
						EnnemyIsHere(agent, action);
						moveAgent(agent, action);
						next = false;
					}
			
				}
			}
		}	
		
		for(Agent agent : this.pListBombermanEnemy) {
			if(!strategy.isBlockOff(agent, this)) {
				AgentAction action = null;
				boolean next = true;
				while(next) {
					action = strategy.generateAction();
					if(isLegalMove(agent, action)) {
						EnnemyIsHere(agent, action);
						moveAgent(agent, action);
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
	
	public InputMap getInputMap() {
		return this.pInputMap;
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
	
	public boolean isLegalMove(Agent agent, AgentAction action) {
		switch (action) {
			case MOVE_DOWN:
				if((agent.getAgent().getY() + 1) < (this.pInputMap.getSizeY() - 1)) {
					if(agent.getAgent().getType() == 'V') return true;
					else if(!this.pBreakable_walls[agent.getAgent().getX()][agent.getAgent().getY() + 1]) return true;
					else return false;
				}
				else
					return false;
			case MOVE_LEFT:
				if((agent.getAgent().getX() - 1) > 0) {
					if(agent.getAgent().getType() == 'V') return true;
					else if(!this.pBreakable_walls[agent.getAgent().getX() - 1][agent.getAgent().getY()]) return true;
					else return false;
				}
				else
					return false;
			case MOVE_RIGHT:
				if((agent.getAgent().getX() + 1) < (this.pInputMap.getSizeX() - 1)) {
					if(agent.getAgent().getType() == 'V') return true;
					else if(!this.pBreakable_walls[agent.getAgent().getX() + 1][agent.getAgent().getY()]) return true;
					else return false;
				}
				else
					return false;
			case MOVE_UP:
				if((agent.getAgent().getY() - 1) > 0) {
					if(agent.getAgent().getType() == 'V') return true;
					else if(!this.pBreakable_walls[agent.getAgent().getX()][agent.getAgent().getY() - 1]) return true;
					else return false;
				}
			case STOP:
				return true;
			default:
				return false;
			}
	}

	public void moveAgent(Agent agent, AgentAction action) {
		if(action != AgentAction.PUT_BOMB && action != AgentAction.STOP) {
			agent.setMove(action);
		}	
	}

	public void putBomb(int coordX, int coordY) {
		this.pListBomb.add(new InfoBomb(coordX, coordY, 2, StateBomb.Step0));
	}
	
	public void EnnemyIsHere(Agent monAgent, AgentAction action) {
		int coordX = monAgent.getAgent().getX();
		int coordY = monAgent.getAgent().getY();
		
		switch (action) {
			case MOVE_DOWN:
				coordY = monAgent.getAgent().getY() + 1;
				break;
			case MOVE_LEFT:
				coordX = monAgent.getAgent().getX() - 1;
				break;
			case MOVE_RIGHT:
				coordX = monAgent.getAgent().getX() + 1;
				break;
			case MOVE_UP:
				coordY = monAgent.getAgent().getY() - 1;
				break;
		}
		
		if(monAgent.getAgent().getType() != 'B') {
			for(Agent agent : this.pListBombermanAgent) {
				if(agent.getAgent().getX() == coordX && agent.getAgent().getY() == coordY) {
					this.pListBombermanAgent.remove(agent);
					break;
				}
			}
		} else if(monAgent.getAgent().getType() == 'B') {
			for(Agent agent : this.pListBombermanEnemy) {
				if(agent.getAgent().getX() == coordX && agent.getAgent().getY() == coordY) {
					this.pListBombermanAgent.remove(monAgent);
					break;
				}
			}
		}
	}
	
	public boolean existBomb(int coordX, int coordY) {
		for(InfoBomb bomb : this.pListBomb) {
			if(bomb.getX() == coordX && bomb.getY() == coordY) {
				return true;
			}
		}
		return false;
	}
	
	public StateBomb nextState(StateBomb state) {
		switch(state) {
			case Step0:
				return StateBomb.Step1;
			case Step1:
				return StateBomb.Step2;
			case Step2:
				return StateBomb.Step3;
			case Step3:
				return StateBomb.Boom;
			case Boom:
				return StateBomb.Boom;
		}
		return state;
	}

}
