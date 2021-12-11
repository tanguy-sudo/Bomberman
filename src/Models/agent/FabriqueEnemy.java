package Models.agent;

import Models.Strategy.BasicEnemyStrategy;
import Models.Strategy.BirdStrategy;
import Models.Strategy.RaijonStrategy;
import Utils.InfoAgent;

public class FabriqueEnemy implements AbstractFactory{

	@Override
	public Agent createAgent(InfoAgent infoAgent) {
		switch(infoAgent.getType()) {
		case 'R':
			return new RajionAgent(infoAgent, new RaijonStrategy());
		case 'V':
			return new BirdAgent(infoAgent, new BirdStrategy());
		case 'E':
			return new BasicEnemyAgent(infoAgent, new BasicEnemyStrategy());
		}
		return null;
	}

}
