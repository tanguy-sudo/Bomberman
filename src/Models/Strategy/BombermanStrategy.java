package Models.Strategy;

import java.util.Random;

import Models.BombermanGame;
import Models.agent.Agent;
import Utils.AgentAction;

public class BombermanStrategy extends Strategy{

	@Override
	public AgentAction generateAction(Agent agent, BombermanGame game) {
		Agent enemy = searchEnemy(agent, game);
		if(enemy != null) {
			System.out.println("cas 1");
			if(!game.BombHere(agent.getAgent().getX(), agent.getAgent().getY())) return AgentAction.PUT_BOMB;
			else return AgentAction.MOVE_UP;

		}
		else if(searchBreakable_walls(agent, game.getBreakable_walls(), game)) {
			System.out.println("cas 2");
			if(!game.BombHere(agent.getAgent().getX(), agent.getAgent().getY())) return AgentAction.PUT_BOMB;
			else return AgentAction.MOVE_DOWN;
		}
		else {
			System.out.println("cas 3");
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
		}
		return AgentAction.STOP;
	}
	
	private Agent searchEnemy(Agent agent, BombermanGame game) {
		for(Agent bombermanAgent : game.getListAgentEnemy()) {
			if(bombermanAgent.getLiving()) {
				if(bombermanAgent.getAgent().getX() <= agent.getAgent().getX() + 2 
						&& bombermanAgent.getAgent().getY() >= agent.getAgent().getY() - 2 
						&& bombermanAgent.getAgent().getX() >= agent.getAgent().getX() - 2
						&& bombermanAgent.getAgent().getY() <= agent.getAgent().getY() + 2) {
					return bombermanAgent;
				}
			}
		}
		return null;
	}
	
	private boolean searchBreakable_walls(Agent agent, boolean[][] wall, BombermanGame game) {
		int beginx = agent.getAgent().getX() - 1;
		int endx = agent.getAgent().getX() + 1;
		if(beginx < 0) beginx = agent.getAgent().getX();
		if(endx > game.getInputMap().getSizeX()) endx = agent.getAgent().getX();
		int beginy = agent.getAgent().getY() - 1;
		int endy = agent.getAgent().getY() + 1;
		if(beginy < 0) beginy = agent.getAgent().getY();
		if(endy > game.getInputMap().getSizeY()) endy = agent.getAgent().getY();
		
		for(int x = beginx; x <= endx ; x++) {
			if(wall[x][beginy]) return true;
			if(wall[x][endy]) return true;
		}
		if(wall[beginx][agent.getAgent().getY()]) return true;
		else if(wall[endx][agent.getAgent().getY()]) return true;
		else return false;
		
	}
}
