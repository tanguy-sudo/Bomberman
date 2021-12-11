package Models.agent;

import Models.Strategy;
import Models.agent.etat.EtatAgent;
import Models.agent.etat.EtatInvincible;
import Models.agent.etat.EtatWithoutEffects;
import Utils.AgentAction;
import Utils.InfoAgent;

public abstract class Agent{
	protected InfoAgent pInfoAgent;
	private EtatAgent pEtat;
	private int pRange;
	private int pInvincibleFor;
	private int pSkullFor;
	private Strategy pStrategy;
	private boolean pLiving;
	
	public Agent(InfoAgent infoAgent, Strategy strategy) {
		this.pInfoAgent = infoAgent;
		this.pEtat = new EtatWithoutEffects(this);
		this.pRange = 2;
		this.pInvincibleFor = 0;
		this.pSkullFor = 0;
		this.pStrategy = strategy;
		this.pLiving = true;
	}
	
	public void setMove(AgentAction action) {		
		switch (action) {
		case MOVE_DOWN:
			this.pInfoAgent.setY(this.pInfoAgent.getY() + 1);
			break;
		case MOVE_LEFT:
			this.pInfoAgent.setX(this.pInfoAgent.getX() - 1);
			break;
		case MOVE_RIGHT:
			this.pInfoAgent.setX(this.pInfoAgent.getX() + 1);
			break;
		case MOVE_UP:
			this.pInfoAgent.setY(this.pInfoAgent.getY() - 1);
			break;
		}
		
		this.pInfoAgent.setAgentAction(action);
	}
	
	public InfoAgent getAgent() {
		return this.pInfoAgent;
	}
	
	public void setEtat(EtatAgent etat) {
		this.pEtat = etat;
	}
	public EtatAgent getEtat() {
		return this.pEtat;
	}
	public void setRange(int range) {
		this.pRange = range;
	}
	public int getRange() {
		return this.pRange;
	}
	public int getInvincibleFor() {
		return this.pInvincibleFor;
	}	
	public void setInvincibleFor(int nbTurn) {
		this.pInvincibleFor = nbTurn;
	}
	public int getSkullFor() {
		return this.pSkullFor;
	}	
	public void setSkullFor(int nbTurn) {
		this.pSkullFor = nbTurn;
	}
	public Strategy getStrategy() {
		return this.pStrategy;
	}
	public void setLiving(boolean living) {
		this.pLiving = living;
	}
	public boolean getLiving() {
		return this.pLiving;
	}
}
