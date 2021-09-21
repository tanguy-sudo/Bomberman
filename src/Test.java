
public class Test {

	public static void main(String[] args) {
		Game simpleGame = new SimpleGame(5);
		
		ViewCommand viewCommand = new ViewCommand();
		
		simpleGame.addPropertyChangeListener(viewCommand);
		
		simpleGame.launch();

	}

}
