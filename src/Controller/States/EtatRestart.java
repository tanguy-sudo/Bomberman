package Controller.States;
import View.*;
public class EtatRestart implements Etat{
	
	ViewCommand pViewCommand;
	
	public EtatRestart(ViewCommand viewCommand) {
		this.pViewCommand = viewCommand;
		
		this.pViewCommand.restartButton.setEnabled(false);
		this.pViewCommand.startButton.setEnabled(true);
		this.pViewCommand.nextButton.setEnabled(true);
		this.pViewCommand.waitButton.setEnabled(false);
	}
	
	@Override
	public void restart() {
	}

	@Override
	public void play() {
		this.pViewCommand.setEtat(new EtatStart(this.pViewCommand));
	}

	@Override
	public void pause() {	
	}

	@Override
	public void step() {	
		this.pViewCommand.setEtat(new EtatStep(this.pViewCommand));
	}
	
}
