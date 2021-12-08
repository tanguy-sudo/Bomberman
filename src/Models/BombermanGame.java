package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import Models.agent.Agent;
import Models.agent.FabriqueBomberman;
import Models.agent.FabriqueEnemy;
import Utils.AgentAction;
import Utils.InfoAgent;
import Utils.InfoBomb;
import Utils.InfoItem;
import Utils.ItemType;
import Utils.StateBomb;

public class BombermanGame extends Game {

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
		// Créer un panel pour afficher la fin de partie avec les statistiques
	}

	@Override
	public void restart(String mapName) {
		try {
			this.pInputMap = new InputMap(mapName);
			this.pBreakable_walls = this.pInputMap.getStart_breakable_walls();
			this.pListBombermanAgent.clear();
			this.pListBombermanEnemy.clear();
			this.pListBomb.clear();
			this.pListItems.clear();
			pSupport.firePropertyChange("pGame", null, this);
		}catch(Exception e) {
			
		}
	}

	@Override
	public void takeTurn() {

		for (Iterator<InfoBomb> iterator = this.pListBomb.iterator(); iterator.hasNext();) {
			InfoBomb bomb = iterator.next();
			if (bomb.getStateBomb() == StateBomb.Boom) {
				iterator.remove();
				pSupport.firePropertyChange("pGame", null, this);
				break;
			}
			bomb.setStateBomb(nextState(bomb.getStateBomb()));
			pSupport.firePropertyChange("pGame", null, this);
			if (bomb.getStateBomb() == StateBomb.Boom) {
				pSupport.firePropertyChange("pGame", null, this);
				destroyWall(bomb.getX(), bomb.getY(), bomb.getRange());
				for (Iterator<Agent> it = this.pListBombermanEnemy.iterator(); it.hasNext();) {
					Agent agent = it.next();
					int posXagent = agent.getAgent().getX();
					int posYagent = agent.getAgent().getY();
					for (int i = 0; i <= agent.getRange(); i++) {
						if (posXagent == bomb.getX()) {
							if ((posYagent == (bomb.getY() + i)) || (posYagent == (bomb.getY() - i))) {
								it.remove();
								pSupport.firePropertyChange("pGame", null, this);
							}
						} else if (posYagent == bomb.getY()) {
							if ((posXagent == (bomb.getX() + i)) || (posXagent == (bomb.getX() - i))) {
								it.remove();
								pSupport.firePropertyChange("pGame", null, this);
							}
						}
					}
				}
			}
		}

		for (Iterator<Agent> iterator = this.pListBombermanAgent.iterator(); iterator.hasNext();) {
			Agent agent = iterator.next();
			updateEtatAgent(agent);
			if (agent.getStrategy().isBlockOff(agent, this) && !BombHere(agent.getAgent().getX(), agent.getAgent().getY()))
				putBomb(agent.getAgent().getX(), agent.getAgent().getY(), agent);
			else {
				AgentAction action = null;
				boolean next = true;
				while (next) {
					action = agent.getStrategy().generateAction(agent, this);
					if (action == AgentAction.PUT_BOMB && !BombHere(agent.getAgent().getX(), agent.getAgent().getY())) {
						putBomb(agent.getAgent().getX(), agent.getAgent().getY(), agent);
						next = false;
					} else if (isLegalMove(agent, action)) {
						moveAgent(agent, action);
						AgentWalksOnItem(agent);
						next = false;
					}
				}
				pSupport.firePropertyChange("pGame", null, this);
			}
		}

		for (Iterator<Agent> iterator = this.pListBombermanEnemy.iterator(); iterator.hasNext();) {
			Agent agent = iterator.next();
			if (!agent.getStrategy().isBlockOff(agent, this)) {
				AgentAction action = null;
				boolean next = true;
				while (next) {
					action = agent.getStrategy().generateAction(agent, this);
					if (isLegalMove(agent, action)) {
						moveAgent(agent, action);
						EnnemyOrAgentIsHereThenKill(agent, action, iterator);
						next = false;
					}

				}
				pSupport.firePropertyChange("pGame", null, this);
			}
		}

	}

	@Override
	public void initializeGame() {
		FabriqueEnemy fabriqueEnemy = new FabriqueEnemy();
		FabriqueBomberman fabriqueBomberman = new FabriqueBomberman();

		for (InfoAgent infoAgent : this.pInputMap.getStart_agents()) {
			if (infoAgent.getType() == 'B') {
				this.pListBombermanAgent.add(fabriqueBomberman.createAgent(infoAgent));
			} else {
				this.pListBombermanEnemy.add(fabriqueEnemy.createAgent(infoAgent));
			}
		}
		pSupport.firePropertyChange("pGame", null, this);
	}

	@Override
	public boolean gameContinue() {
		if(this.pListBombermanAgent.size() == 0) {
			System.out.println("you lose");
			return false;
		}else if(this.pListBombermanEnemy.size() == 0) {
			System.out.println("you win");
			return false;
		}else if(this.pListBombermanAgent.size() == 0 && this.pListBombermanEnemy.size() == 0) {
			System.out.println("equality");
			return false;
		}
		return true;
	}

	public InputMap getInputMap() {
		return this.pInputMap;
	}

	public boolean[][] getBreakable_walls() {
		return this.pBreakable_walls;
	}

	public ArrayList<InfoAgent> getListAgent() {
		ArrayList<InfoAgent> listAgent = new ArrayList<InfoAgent>();
		for (Agent agent : this.pListBombermanAgent) {
			listAgent.add(agent.getAgent());
		}
		for (Agent agent : this.pListBombermanEnemy) {
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
		int coordX = agent.getAgent().getX();
		int coordY = agent.getAgent().getY();
		switch (action) {
		case MOVE_DOWN:
			if ((coordY + 1) < (this.pInputMap.getSizeY() - 1)) {
				if (agent.getAgent().getType() == 'V')
					return true;
				else if(this.pInputMap.get_walls()[coordX][coordY + 1])
					return false;
				else if(agent.getAgent().getType() == 'R')
					return true;
				else if(EnnemyHere(coordX, coordY + 1))
					return false; 
				else if(!this.pBreakable_walls[coordX][coordY + 1])
					return true;
				else
					return false;
			} else
				return false;
		case MOVE_LEFT:
			if ((coordX - 1) > 0) {
				if (agent.getAgent().getType() == 'V')
					return true;
				else if(this.pInputMap.get_walls()[coordX - 1][coordY])
					return false;
				else if(agent.getAgent().getType() == 'R')
					return true;
				else if(EnnemyHere(coordX - 1, coordY))
					return false; 
				else if (!this.pBreakable_walls[coordX - 1][coordY])
					return true;
				else
					return false;
			} else
				return false;
		case MOVE_RIGHT:
			if ((coordX + 1) < (this.pInputMap.getSizeX() - 1)) {
				if (agent.getAgent().getType() == 'V')
					return true;
				else if(this.pInputMap.get_walls()[coordX + 1][coordY])
					return false;
				else if(agent.getAgent().getType() == 'R')
					return true;
				else if(EnnemyHere(coordX + 1, coordY))
					return false; 
				else if (!this.pBreakable_walls[coordX + 1][coordY])
					return true;
				else
					return false;
			} else
				return false;
		case MOVE_UP:
			if ((coordY - 1) > 0) {
				if (agent.getAgent().getType() == 'V')
					return true;
				else if(this.pInputMap.get_walls()[coordX][coordY - 1])
					return false;
				else if(agent.getAgent().getType() == 'R')
					return true;
				else if(EnnemyHere(coordX, coordY - 1))
					return false; 
				else if (!this.pBreakable_walls[coordX][coordY - 1])
					return true;
				else
					return false;
			} else
				return false;
		case STOP:
			return true;
		default:
			return false;
		}
	}

	public void moveAgent(Agent agent, AgentAction action) {
		if (action != AgentAction.PUT_BOMB && action != AgentAction.STOP) {
			agent.setMove(action);
		}
	}

	public void putBomb(int coordX, int coordY, Agent agent) {
		if(agent.getSkullFor() <= 0) this.pListBomb.add(new InfoBomb(coordX, coordY, agent.getRange(), StateBomb.Step0));
	}

	public void EnnemyOrAgentIsHereThenKill(Agent AgentEnnemy, AgentAction action, Iterator<Agent> itAgent) {
		int coordX = AgentEnnemy.getAgent().getX();
		int coordY = AgentEnnemy.getAgent().getY();

		switch (action) {
		case MOVE_DOWN:
			coordY = AgentEnnemy.getAgent().getY() + 1;
			break;
		case MOVE_LEFT:
			coordX = AgentEnnemy.getAgent().getX() - 1;
			break;
		case MOVE_RIGHT:
			coordX = AgentEnnemy.getAgent().getX() + 1;
			break;
		case MOVE_UP:
			coordY = AgentEnnemy.getAgent().getY() - 1;
			break;
		default:
			break;
		}

			for (Iterator<Agent> iterator = this.pListBombermanAgent.iterator(); iterator.hasNext();) {
				Agent agent = iterator.next();
				if (agent.getAgent().getX() == coordX && agent.getAgent().getY() == coordY && agent.getInvincibleFor() <= 0) {
					iterator.remove();
					pSupport.firePropertyChange("pGame", null, this);
					break;
				}
			}
				for (Iterator<Agent> iterator = this.pListBombermanEnemy.iterator(); iterator.hasNext();) {
					Agent agent = iterator.next();
					if (agent.getAgent().getX() == coordX && agent.getAgent().getY() == coordY && agent.getInvincibleFor() <= 0) {
						itAgent.remove();
						pSupport.firePropertyChange("pGame", null, this);
						break;
					}
				}	
	}

	public StateBomb nextState(StateBomb state) {
		switch (state) {
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

	public void destroyWall(int coordX, int coordY, int range) {
		for (int i = 0; i <= range; i++) {
			if (coordY + i < this.pBreakable_walls[coordX].length && this.pBreakable_walls[coordX][coordY + i]) {
				this.pBreakable_walls[coordX][coordY + i] = false;
				generateItem(coordX, coordY + i);
			}
			if (coordY - i > 0 && this.pBreakable_walls[coordX][coordY - i]) {
				this.pBreakable_walls[coordX][coordY - i] = false;
				generateItem(coordX, coordY - i);
			}
		}
		for (int i = 0; i <= range; i++) {
			if (coordX + i < this.pBreakable_walls.length && this.pBreakable_walls[coordX + i][coordY]) {
				this.pBreakable_walls[coordX + i][coordY] = false;
				generateItem(coordX + i, coordY);
			}
			if (coordX - i > 0 && this.pBreakable_walls[coordX - i][coordY]) {
				this.pBreakable_walls[coordX - i][coordY] = false;
				generateItem(coordX - i, coordY);
			}
		}
	}

	public void generateItem(int coordX, int coordY) {
		Random r = new Random();
		switch (r.nextInt(2)) {
		case 1:
			this.pListItems.add(new InfoItem(coordX, coordY, ramdomItem()));
			break;
		}

	}

	public ItemType ramdomItem() {
		Random r = new Random();
		switch (r.nextInt(4)) {
		case 0:
			return ItemType.FIRE_DOWN;
		case 1:
			return ItemType.FIRE_SUIT;
		case 2:
			return ItemType.FIRE_UP;
		case 3:
			return ItemType.SKULL;
		}
		return null;
	}

	public void AgentWalksOnItem(Agent agent) {
		for (Iterator<InfoItem> iterator = this.pListItems.iterator(); iterator.hasNext();) {
			InfoItem item = iterator.next();
			if (item.getX() == agent.getAgent().getX() && item.getY() == agent.getAgent().getY()) {
				switch (item.getType()) {
				case FIRE_UP:
					agent.setRange(agent.getRange() + 1);
					break;
				case FIRE_DOWN:
					if (agent.getRange() > 1)
						agent.setRange(agent.getRange() - 1);
					break;
				case FIRE_SUIT:
					agent.getEtat().invincible();
					break;
				case SKULL:
					agent.getEtat().skull();
					break;
				}
				iterator.remove();
				break;
			}
		}
	}

	public void updateEtatAgent(Agent agent) {
		agent.setInvincibleFor(agent.getInvincibleFor() - 1);
		agent.setSkullFor(agent.getSkullFor() - 1);
		if (agent.getInvincibleFor() <= 0 && agent.getSkullFor() <= 0)
			agent.getEtat().withoutEffects();
	}
	
	public ArrayList<Agent> getListAgentEnnemy(){
		return this.pListBombermanEnemy;
	}
	public ArrayList<Agent> getListAgentBomberman(){
		return this.pListBombermanAgent;
	}
	
	public boolean EnnemyHere(int coordX, int coordY) {
		for(Agent agent : this.pListBombermanEnemy) {
			if(agent.getAgent().getX() == coordX && agent.getAgent().getY() == coordY) {
				return true;
			}
		}
		return false;
	}
	
	public boolean BombHere(int coordX, int coordY) {
		for(InfoBomb bomb : this.pListBomb) {
			if(bomb.getX() == coordX && bomb.getY() == coordY) {
				return true;
			}
		}
		return false;
	}

}
