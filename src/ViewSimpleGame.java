import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewSimpleGame {
	public ViewSimpleGame() {
		JFrame window = new JFrame("Game");
		
		JPanel Globalpanel = new JPanel();
		
		GridLayout GlobalGridlayout = new GridLayout(1, 1);
		
		JLabel NumberOfTurnJLabel = new JLabel("Turn : 5", JLabel.CENTER);
		
		Globalpanel.setLayout(GlobalGridlayout);
		Globalpanel.add(NumberOfTurnJLabel);
		
		window.setSize(new Dimension(500, 500));
		window.add(Globalpanel);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
