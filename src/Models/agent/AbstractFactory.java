package Models.agent;

import Models.Strategy.Strategy;
import Utils.InfoAgent;

public interface AbstractFactory {
	public Agent createAgent(InfoAgent infoAgent, int niveau);
}
