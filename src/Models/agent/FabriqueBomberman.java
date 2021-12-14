package Models.agent;

import Models.Strategy.BombermanStrategy;
import Utils.InfoAgent;

public class FabriqueBomberman implements AbstractFactory{

	public Agent createAgent(InfoAgent infoAgent) {
		return new BombermanAgent(infoAgent, new BombermanStrategy());
	}

}
