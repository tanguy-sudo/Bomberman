import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Game implements Runnable{
	//Attributs
	private int pTurn;
	protected int pMaxturn;
	protected boolean pIsRunning;
	private Thread mThread;
	private long mTime;
	private PropertyChangeSupport support;
	
	//Constructeur
	public Game(int maxturn){
		this.pMaxturn = maxturn;
		this.mTime = 1000;
		this.support = new PropertyChangeSupport(this);
	}
	
	//Méthodes
	public void init() {
		this.pTurn = 0;
		this.pIsRunning = true;
		
		this.initializeGame();
	}
	
	public void step() {
		int value = this.pTurn + 1;
		support.firePropertyChange("pTurn", this.pTurn, value);
		this.pTurn = value;
		
		if(this.gameContinue() && this.pTurn <= this.pMaxturn) {
			this.takeTurn();			
		}
		else {
			this.pIsRunning = false;
			this.gameOver();
		}
	}
	
	public void run() {
		while(this.pIsRunning) {
			this.step();
			try {
				Thread.sleep(mTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void pause() {
		this.pIsRunning = false;
	}
	
	public void launch() {
		this.pIsRunning = true;
		this.mThread = new Thread(this);
		this.mThread.start();
	}
	
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
   
    public int getTurn() {
    	return this.pTurn;
    }
	
	//Méthodes abstraite
	public abstract void gameOver();
	public abstract void takeTurn();
	public abstract void initializeGame();
	public abstract boolean gameContinue();
}
