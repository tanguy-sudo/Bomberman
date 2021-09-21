
public class SimpleGame extends Game  {
	
	//Constructeur
	public SimpleGame(int maxturn){
		super(maxturn);
	}

	@Override
	public void gameOver() {
		System.out.println("Fin du jeu");
		
	}

	@Override
	public void takeTurn() {
		System.out.println("Tour " + getTurn() + " du jeu en cours");
	}

	@Override
	public void initializeGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean gameContinue() {
		return true;
	}

}
