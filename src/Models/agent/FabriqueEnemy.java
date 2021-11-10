package Models.agent;

import Utils.InfoAgent;

public class FabriqueEnemy implements AbstractFactory{

	@Override
	public Agent createAgent(InfoAgent infoAgent) {
		switch(infoAgent.getType()) {
		case 'R':
			return new RajionAgent(infoAgent);
		case 'V':
			return new BirdAgent(infoAgent);
		case 'E':
			return new BasicEnemyAgent(infoAgent);
		}
		return null;
	}

}
