package Models.agent;

import Models.Strategy.BasicEnemyStrategy;
import Models.Strategy.BirdStrategy;
import Models.Strategy.RaijonStrategy;
import Models.Strategy.SimpleStrategy;
import Utils.InfoAgent;

public class FabriqueEnemy implements AbstractFactory{

	@Override
	public Agent createAgent(InfoAgent infoAgent, int niveau) {
		if(niveau == 1) {
			switch(infoAgent.getType()) {
				case 'R':
					return new RajionAgent(infoAgent, new SimpleStrategy());
				case 'V':
					return new BirdAgent(infoAgent, new SimpleStrategy());
				case 'E':
					return new BasicEnemyAgent(infoAgent, new SimpleStrategy());
			}
		} else if(niveau == 2) {
			switch(infoAgent.getType()) {
				case 'R':
					return new RajionAgent(infoAgent, new RaijonStrategy());
				case 'V':
					return new BirdAgent(infoAgent, new BirdStrategy());
				case 'E':
					return new BasicEnemyAgent(infoAgent, new BasicEnemyStrategy());
			}
		}
		// on tombe jamais dans se cas
		return null;
	}
}