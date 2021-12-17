package Models.agent;

import Models.Strategy.Strategy;
import Utils.AgentAction;
import Utils.InfoAgent;
/**
 * Classe représentant un bomberman
 * @author tanguy
 *
 */
public class BombermanAgent extends Agent{
	
	private AgentAction pActionUtilisateur;
	
	public BombermanAgent(InfoAgent infoAgent, Strategy strategy) {
		super(infoAgent, strategy);
		this.pActionUtilisateur = AgentAction.STOP;
	}
	
	/**
	 * 
	 * @return Une action
	 */
	public AgentAction getAtion() {
		return this.pActionUtilisateur;
	}
	
	/**
	 * Mets à jour l'action de l'utilisateur
	 * @param action
	 */
	public void setAtion(AgentAction action) {
		this.pActionUtilisateur = action;
	}
}
