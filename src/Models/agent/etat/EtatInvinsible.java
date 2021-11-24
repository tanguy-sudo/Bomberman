package Models.agent.etat;

import Models.agent.Agent;

public class EtatInvinsible implements EtatAgent{
	
	private Agent pAgent;
	
	public EtatInvinsible(Agent agent) {
		this.pAgent = agent;
	}

	@Override
	public void invinsible() {
	}

	@Override
	public void skull() {
		this.pAgent.setEtat(new EtatSkull(this.pAgent));
	}

	@Override
	public void withoutEffects() {
		this.pAgent.setEtat(new EtatWithoutEffects(this.pAgent));	
	}

}
