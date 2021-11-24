package Models.agent.etat;

import Models.agent.Agent;

public class EtatWithoutEffects implements EtatAgent{
	
	private Agent pAgent;
	
	public EtatWithoutEffects(Agent agent) {
		this.pAgent = agent;
	}

	@Override
	public void invinsible() {
		this.pAgent.setEtat(new EtatInvinsible(this.pAgent));
	}

	@Override
	public void skull() {
		this.pAgent.setEtat(new EtatSkull(this.pAgent));	
	}

	@Override
	public void withoutEffects() {	
	}

}
