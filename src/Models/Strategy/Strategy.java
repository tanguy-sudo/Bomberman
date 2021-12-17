package Models.Strategy;

import Models.BombermanGame;
import Models.agent.Agent;
import Utils.AgentAction;
/**
 * Interface des stratégies
 * @author tanguy
 *
 */

public abstract class Strategy {

	
	public abstract AgentAction generateAction(Agent agent, BombermanGame game);
	
	public boolean isBlockOff(Agent agent, BombermanGame game) {
		if(game.isLegalMove(agent, AgentAction.MOVE_DOWN)
				|| game.isLegalMove(agent, AgentAction.MOVE_LEFT)
				|| game.isLegalMove(agent, AgentAction.MOVE_UP)
				|| game.isLegalMove(agent, AgentAction.MOVE_RIGHT)) return false;
		else return true;
	}
}
