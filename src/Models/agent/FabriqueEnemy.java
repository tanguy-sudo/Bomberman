package Models.agent;

import Models.BirdStrategy;
import Models.SimpleStrategy;
import Models.Strategy;
import Utils.InfoAgent;

public class FabriqueEnemy implements AbstractFactory{

	@Override
	public Agent createAgent(InfoAgent infoAgent) {
		switch(infoAgent.getType()) {
		case 'E':
			return new RajionAgent(infoAgent, new SimpleStrategy());
		case 'V':
			return new BirdAgent(infoAgent, new BirdStrategy());
		case 'R':
			return new BasicEnemyAgent(infoAgent, new SimpleStrategy());
		}
		return null;
	}

}
