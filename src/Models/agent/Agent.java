package Models.agent;

import Utils.AgentAction;
import Utils.ColorAgent;
import Utils.InfoAgent;

public abstract class Agent{
	protected InfoAgent pInfoAgent;
	
	public Agent(InfoAgent infoAgent) {
		this.pInfoAgent = infoAgent;
	}
	
	public void setMove(AgentAction action) {		
		switch (action) {
		case MOVE_DOWN:
			this.pInfoAgent.setY(this.pInfoAgent.getY() + 1);
			break;
		case MOVE_LEFT:
			this.pInfoAgent.setX(this.pInfoAgent.getX() - 1);
			break;
		case MOVE_RIGHT:
			this.pInfoAgent.setX(this.pInfoAgent.getX() + 1);
			break;
		case MOVE_UP:
			this.pInfoAgent.setY(this.pInfoAgent.getY() - 1);
			break;
		}
	}
	
	public InfoAgent getAgent() {
		return this.pInfoAgent;
	}
}
