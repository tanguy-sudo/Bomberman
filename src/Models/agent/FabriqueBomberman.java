package Models.agent;

import Models.Strategy.SimpleStrategy;
import Models.Strategy.Strategy;
import Utils.ColorAgent;
import Utils.InfoAgent;

public class FabriqueBomberman implements AbstractFactory{

	public Agent createAgent(InfoAgent infoAgent) {
		return new BombermanAgent(infoAgent, new SimpleStrategy());
	}

}
