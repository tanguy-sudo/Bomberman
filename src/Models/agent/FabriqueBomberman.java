package Models.agent;

import Models.SimpleStrategy;
import Models.Strategy;
import Utils.ColorAgent;
import Utils.InfoAgent;

public class FabriqueBomberman implements AbstractFactory{

	public Agent createAgent(InfoAgent infoAgent) {
		return new BombermanAgent(infoAgent, new SimpleStrategy());
	}

}
