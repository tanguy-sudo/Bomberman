package Models.agent;

import Models.Strategy.BombermanStrategy;
import Models.Strategy.SimpleStrategy;
import Utils.InfoAgent;

public class FabriqueBomberman implements AbstractFactory{

	public Agent createAgent(InfoAgent infoAgent, int niveau) {
		if(niveau == 1) {
			return new BombermanAgent(infoAgent, new SimpleStrategy());		
		} else if(niveau == 2) {
			return new BombermanAgent(infoAgent, new BombermanStrategy());	
		}
		// on tombe jamais dans se cas
		return null;
	}
}
