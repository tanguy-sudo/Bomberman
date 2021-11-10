package Models.agent;

import Utils.InfoAgent;

public interface AbstractFactory {
	public Agent createAgent(InfoAgent infoAgent);
}
