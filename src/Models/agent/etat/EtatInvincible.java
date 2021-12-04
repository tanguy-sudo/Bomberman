package Models.agent.etat;

import Models.agent.Agent;

public class EtatInvincible implements EtatAgent{
	
	private Agent pAgent;
	
	public EtatInvincible(Agent agent) {
		this.pAgent = agent;
		this.pAgent.setInvincibleFor(3);
		this.pAgent.setSkullFor(0);
		this.pAgent.getAgent().setInvincible(true);
	}

	@Override
	public void invincible() {
		this.pAgent.setInvincibleFor(3);
	}

	@Override
	public void skull() {
	}

	@Override
	public void withoutEffects() {
	}

}