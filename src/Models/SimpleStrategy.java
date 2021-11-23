package Models;

import java.util.Random;

import Utils.AgentAction;

public class SimpleStrategy extends Strategy{

	public AgentAction generateAction() {
		Random r = new Random();
		switch(r.nextInt(5)) {
			case 0:
				return AgentAction.MOVE_DOWN;
			case 1:
				return AgentAction.MOVE_LEFT;
			case 2:
				return AgentAction.MOVE_RIGHT;
			case 3:
				return AgentAction.MOVE_UP;
			case 4:
				return AgentAction.STOP;
		}
		return null;
	}

	@Override
	public AgentAction generateActionWithBomb() {
		Random r = new Random();
		switch(r.nextInt(6)) {
			case 0:
				return AgentAction.MOVE_DOWN;
			case 1:
				return AgentAction.MOVE_LEFT;
			case 2:
				return AgentAction.PUT_BOMB;
			case 3:
				return AgentAction.MOVE_UP;
			case 4:
				return AgentAction.STOP;
			case 5:
				return AgentAction.MOVE_RIGHT;
		}
		return null;
	}
}
