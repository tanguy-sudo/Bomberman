package Models.agent.etat;

import Models.agent.Agent;

public class EtatSkull implements EtatAgent{
	
	private Agent pAgent;
	
	public EtatSkull(Agent agent) {
		this.pAgent = agent;
	}

	@Override
	public void invinsible() {
		this.pAgent.setEtat(new EtatInvinsible(this.pAgent));	
	}

	@Override
	public void skull() {	
	}

	@Override
	public void withoutEffects() {
		this.pAgent.setEtat(new EtatWithoutEffects(this.pAgent));	
	}

}
