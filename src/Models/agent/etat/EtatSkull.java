package Models.agent.etat;

import Models.agent.Agent;

public class EtatSkull implements EtatAgent{
	
	private Agent pAgent;
	
	public EtatSkull(Agent agent) {
		this.pAgent = agent;
		this.pAgent.setSkullFor(2);
		this.pAgent.getAgent().setSick(true);
		
	}

	@Override
	public void invincible() {
		this.pAgent.getAgent().setSick(false);
		this.pAgent.setEtat(new EtatInvincible(this.pAgent));	
	}

	@Override
	public void skull() {	
		this.pAgent.setSkullFor(2);
	}

	@Override
	public void withoutEffects() {
	}

}