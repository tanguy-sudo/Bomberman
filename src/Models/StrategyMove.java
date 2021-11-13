package Models;

import Models.agent.Agent;
import Utils.AgentAction;

public class StrategyMove implements Strategie{
	private BombermanGame bombermanGame;
	
	public StrategyMove(BombermanGame bombermanGame) {
		this.bombermanGame = bombermanGame;
	}
	
	@Override
	public boolean isLegalMove(Agent agent, AgentAction action) {
		boolean[][] wall = this.bombermanGame.getBreakable_walls();
		switch (action) {
		case MOVE_DOWN:
			if((agent.getAgent().getY() + 1) < (this.bombermanGame.getInputMap().getSizeY() - 1)) {
				if(agent.getAgent().getType() == 'V') return true;
				else if(agent.getAgent().getType() == 'B' && !wall[agent.getAgent().getX()][agent.getAgent().getY() + 1]) return true;
				else return false;
			}
			else
				return false;
		case MOVE_LEFT:
			if((agent.getAgent().getX() - 1) > 0) {
				if(agent.getAgent().getType() == 'V') return true;
				else if(agent.getAgent().getType() == 'B' && !wall[agent.getAgent().getX() - 1][agent.getAgent().getY()]) return true;
				else return false;
			}
			else
				return false;
		case MOVE_RIGHT:
			if((agent.getAgent().getX() + 1) < (this.bombermanGame.getInputMap().getSizeX() - 1)) {
				if(agent.getAgent().getType() == 'V') return true;
				else if(agent.getAgent().getType() == 'B' && !wall[agent.getAgent().getX() + 1][agent.getAgent().getY()]) return true;
				else return false;
			}
			else
				return false;
		case MOVE_UP:
			if((agent.getAgent().getY() - 1) > 0) {
				if(agent.getAgent().getType() == 'V') return true;
				else if(agent.getAgent().getType() == 'B' && !wall[agent.getAgent().getX()][agent.getAgent().getY() - 1]) return true;
				else return false;
			}
		default:
			return false;
		}
	}

	@Override
	public void moveAgent(Agent agent, AgentAction action) {
		if(action == AgentAction.MOVE_DOWN || action == AgentAction.MOVE_LEFT  || action == AgentAction.MOVE_RIGHT  || action == AgentAction.MOVE_UP) {
			agent.setMove(action);
		}
		
	}

	@Override
	public void putBomb(int coordX, int coordY) {
		this.bombermanGame.addBomb(coordX, coordY);
	}

}
