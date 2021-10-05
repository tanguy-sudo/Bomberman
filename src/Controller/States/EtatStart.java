package Controller.States;
import View.*;
public class EtatStart implements Etat {
	
	ViewCommand pViewCommand;
	
	public EtatStart(ViewCommand viewCommand) {
		this.pViewCommand = viewCommand;
		
		this.pViewCommand.restartButton.setEnabled(true);
		this.pViewCommand.startButton.setEnabled(false);
		this.pViewCommand.nextButton.setEnabled(false);
		this.pViewCommand.waitButton.setEnabled(true);
	}

	@Override
	public void restart() {
		this.pViewCommand.setEtat(new EtatRestart(this.pViewCommand));		
	}

	@Override
	public void play() {
	}

	@Override
	public void pause() {
		this.pViewCommand.setEtat(new EtatWait(this.pViewCommand));	
	}

	@Override
	public void step() {	
	}

}
