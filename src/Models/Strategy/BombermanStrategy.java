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
			if(!game.BombHere(agent.getAgent().getX(), agent.getAgent().getY()) && enemyIsTooClose(agent, game) == null) return AgentAction.PUT_BOMB;
			else return nextAction(agent, game);

		}
		else if(searchBreakable_walls(agent, game.getBreakable_walls(), game)) {
			if(!game.BombHere(agent.getAgent().getX(), agent.getAgent().getY())) return AgentAction.PUT_BOMB;
			else return randomAction();
		}
		else return randomAction();
	}
	
	private Agent searchEnemy(Agent agent, BombermanGame game) {
		for(Agent enemy : game.getListAgentEnemy()) {
			if(enemy.getLiving()) {
				if(enemy.getAgent().getX() <= agent.getAgent().getX() + 3 
						&& enemy.getAgent().getY() >= agent.getAgent().getY() - 3 
						&& enemy.getAgent().getX() >= agent.getAgent().getX() - 3
						&& enemy.getAgent().getY() <= agent.getAgent().getY() + 3) {
					return enemy;
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
	
	private AgentAction nextAction(Agent agent, BombermanGame game) {
		enemyIsTooClose(agent, game);
		for(Agent enemy : game.getListAgentEnemy()) {
			if(enemy.getLiving()) {
				if(enemy.getAgent().getX() <= agent.getAgent().getX() + 2 
						&& enemy.getAgent().getY() >= agent.getAgent().getY() - 2 
						&& enemy.getAgent().getX() >= agent.getAgent().getX() - 2
						&& enemy.getAgent().getY() <= agent.getAgent().getY() + 2) {
					if (enemy.getAgent().getX() < agent.getAgent().getX() && game.isLegalMove(agent, AgentAction.MOVE_RIGHT)) return AgentAction.MOVE_RIGHT;
					else if (enemy.getAgent().getY() > agent.getAgent().getY() && game.isLegalMove(agent, AgentAction.MOVE_UP)) return AgentAction.MOVE_UP;
					else if (enemy.getAgent().getX() > agent.getAgent().getX() && game.isLegalMove(agent, AgentAction.MOVE_LEFT)) return AgentAction.MOVE_LEFT;
					else if (enemy.getAgent().getY() < agent.getAgent().getY() && game.isLegalMove(agent, AgentAction.MOVE_DOWN)) return AgentAction.MOVE_DOWN;
				}
			}
		}
		for(Agent enemy : game.getListAgentEnemy()) {
			if(enemy.getLiving()) {
				if(enemy.getAgent().getX() <= agent.getAgent().getX() + 3 
						&& enemy.getAgent().getY() >= agent.getAgent().getY() - 3 
						&& enemy.getAgent().getX() >= agent.getAgent().getX() - 3
						&& enemy.getAgent().getY() <= agent.getAgent().getY() + 3) {
					if (enemy.getAgent().getX() < agent.getAgent().getX() && game.isLegalMove(agent, AgentAction.MOVE_RIGHT)) return AgentAction.MOVE_RIGHT;
					else if (enemy.getAgent().getY() > agent.getAgent().getY() && game.isLegalMove(agent, AgentAction.MOVE_UP)) return AgentAction.MOVE_UP;
					else if (enemy.getAgent().getX() > agent.getAgent().getX() && game.isLegalMove(agent, AgentAction.MOVE_LEFT)) return AgentAction.MOVE_LEFT;
					else if (enemy.getAgent().getY() < agent.getAgent().getY() && game.isLegalMove(agent, AgentAction.MOVE_DOWN)) return AgentAction.MOVE_DOWN;
				}
			}
		}
		
		return AgentAction.STOP;
	}
	
	private AgentAction enemyIsTooClose(Agent agent, BombermanGame game) {
		for(Agent enemy : game.getListAgentEnemy()) {
			if(enemy.getLiving()) {
				if(enemy.getAgent().getX() <= agent.getAgent().getX() + 1 
						&& enemy.getAgent().getY() >= agent.getAgent().getY() - 1 
						&& enemy.getAgent().getX() >= agent.getAgent().getX() - 1
						&& enemy.getAgent().getY() <= agent.getAgent().getY() + 1) {
					if (enemy.getAgent().getX() < agent.getAgent().getX() && game.isLegalMove(agent, AgentAction.MOVE_RIGHT)) return AgentAction.MOVE_RIGHT;
					else if (enemy.getAgent().getY() > agent.getAgent().getY() && game.isLegalMove(agent, AgentAction.MOVE_UP)) return AgentAction.MOVE_UP;
					else if (enemy.getAgent().getX() > agent.getAgent().getX() && game.isLegalMove(agent, AgentAction.MOVE_LEFT)) return AgentAction.MOVE_LEFT;
					else if (enemy.getAgent().getY() < agent.getAgent().getY() && game.isLegalMove(agent, AgentAction.MOVE_DOWN)) return AgentAction.MOVE_DOWN;
				}
			}
		}
		return null;
	}
	
	private AgentAction randomAction() {
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
		return AgentAction.STOP;
	}
}
