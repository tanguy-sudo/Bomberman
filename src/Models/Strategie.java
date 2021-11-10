package Models;

import Models.agent.Agent;
import Utils.AgentAction;

public abstract class Strategie {
	public abstract boolean isLegalMove(Agent agent, AgentAction action);
	public abstract void moveAgent(Agent agent, AgentAction action);
	public abstract void putBomb(int coordX, int coordY);
}
