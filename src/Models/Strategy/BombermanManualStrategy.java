package Models.Strategy;

import Models.BombermanGame;
import Models.agent.Agent;
import Models.agent.BombermanAgent;
import Utils.AgentAction;
/**
 * Mode manuel, l'utilisateur bouge avec les claviers la Bomberman
 * @author tanguy
 *
 */
public class BombermanManualStrategy extends Strategy{

	@Override
	public AgentAction generateAction(Agent agent, BombermanGame game) {
		return ((BombermanAgent)agent).getAtion(); 
	}

}
