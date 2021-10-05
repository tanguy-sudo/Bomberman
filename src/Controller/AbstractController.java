package Controller;
import Models.*;
public abstract class AbstractController {
	protected Game pGame;
	
	// arrêt et réinitialisation
	public void restart() {
		this.pGame.init();
	}
	
	// passage manuel d’une étape
	public void step() {
		this.pGame.step();
	}
	
	// passage automatique des étapes
	public void play() {
		this.pGame.launch();
	}
	
	// interruption du passage automatique des étapes
	public void pause() {
		this.pGame.pause();
	}
	
	// réglage de la vitesse du jeu
	public void setSpeed(double speed) {
		this.pGame.setTime(speed);
	}
}
