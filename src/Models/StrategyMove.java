package Models;

import Models.agent.Agent;
import Utils.AgentAction;

public class StrategyMove extends Strategie{
	@Override
	public boolean isLegalMove(Agent agent, AgentAction action) {
		
		return false;
	}

	@Override
	public void moveAgent(Agent agent, AgentAction action) {
		if(action == AgentAction.MOVE_DOWN || action == AgentAction.MOVE_LEFT  || action == AgentAction.MOVE_RIGHT  || action == AgentAction.MOVE_UP) {
			agent.setMove(action);
		}
		
	}

	@Override
	public void putBomb(int coordX, int coordY) {
		
		
	}

}
