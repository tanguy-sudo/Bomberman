package Models;

import java.util.Random;

import Models.agent.Agent;
import Utils.AgentAction;
import Utils.InfoAgent;

public class BirdStrategy extends Strategy{

	@Override
	public AgentAction generateAction(Agent agent, BombermanGame game) {
		for(Agent bombermanAgent : game.getListAgentBomberman()) {
			if(bombermanAgent.getAgent().getX() <= agent.getAgent().getX() + 2 
					&& bombermanAgent.getAgent().getY() >= agent.getAgent().getY() - 2 
					&& bombermanAgent.getAgent().getX() >= agent.getAgent().getX() - 2
					&& bombermanAgent.getAgent().getY() <= agent.getAgent().getY() + 2) {
				Random r = new Random();
				switch(r.nextInt(5)) {
					case 0:
						return AgentAction.MOVE_DOWN;
					case 1:
						return AgentAction.MOVE_LEFT;
					case 2:
						return AgentAction.MOVE_RIGHT;
					case 3:
						return AgentAction.MOVE_UP;
					case 4:
						return AgentAction.STOP;
				}	
				break;
				
			}
		}
		return AgentAction.STOP;
	}

}
