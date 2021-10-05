package Controller;
import View.*;
import Models.*;
public class ControllerSimpleGame extends AbstractController{
	public ControllerSimpleGame() {
		this.pGame = new SimpleGame(5);
		ViewCommand viewCommand = new ViewCommand(this);
		//ViewSimpleGame viewSimpleGame = new ViewSimpleGame();
		this.pGame.addPropertyChangeListener(viewCommand);
	}
}
