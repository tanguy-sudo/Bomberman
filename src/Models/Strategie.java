package Models;

import Models.agent.Agent;
import Utils.AgentAction;

public interface Strategie {
	public boolean isLegalMove(Agent agent, AgentAction action);
	public void moveAgent(Agent agent, AgentAction action);
	public void putBomb(int coordX, int coordY);
}
