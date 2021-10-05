package Controller.States;
import View.*;
public class EtatWait implements Etat{
	
	ViewCommand pViewCommand;
	
	public EtatWait(ViewCommand viewCommand) {
		this.pViewCommand = viewCommand;
		
		this.pViewCommand.restartButton.setEnabled(true);
		this.pViewCommand.startButton.setEnabled(true);
		this.pViewCommand.nextButton.setEnabled(true);
		this.pViewCommand.waitButton.setEnabled(false);
	}

	@Override
	public void restart() {
		this.pViewCommand.setEtat(new EtatRestart(this.pViewCommand));			
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
