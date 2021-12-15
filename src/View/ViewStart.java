package View;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ViewStart {
	public ViewStart() {
	
		JFrame window = new JFrame("Bomberman");
		JPanel globalpanel = new JPanel();
		JPanel choiceJPanel = new JPanel();
		JPanel mapJPanel = new JPanel();
		JPanel levelJPanel = new JPanel();
		JPanel playJPanel = new JPanel();
		
		GridLayout globalGridlayout = new GridLayout(2, 1);
		GridLayout choiceGridLayout = new GridLayout(1, 2);
		GridLayout mapGridLayout = new GridLayout(2, 1);
		GridLayout levelGridLayout = new GridLayout(2, 1);
		GridLayout playGridLayout = new GridLayout(1, 1);	
		
		String s1[] = { "alone", "arene", "exemple", "jeu_symetrique", "jeu1", "niveau1", "niveau2", "niveau3", "test" }; 
		String s2[] = { "niveau 1", "niveau 2" };		
		JComboBox listMap = new JComboBox(s1);
		JLabel listMapLabel = new JLabel("Choisissez une map", SwingConstants.CENTER); 
		JComboBox listLevel = new JComboBox(s2);
		JLabel listLevelLabel = new JLabel("Choisissez un niveau", SwingConstants.CENTER); 
		JButton button = new JButton("Valider");
			
		globalpanel.setLayout(globalGridlayout);
		choiceJPanel.setLayout(choiceGridLayout);
		mapJPanel.setLayout(mapGridLayout);
		levelJPanel.setLayout(levelGridLayout);
		playJPanel.setLayout(playGridLayout);
		
		playJPanel.add(button);
		levelJPanel.add(listLevelLabel);
		levelJPanel.add(listLevel);
		mapJPanel.add(listMapLabel);
		mapJPanel.add(listMap);
		choiceJPanel.add(mapJPanel);
		choiceJPanel.add(levelJPanel);
		globalpanel.add(choiceJPanel);
		globalpanel.add(playJPanel);
		window.add(globalpanel);
		
		window.setSize(new Dimension(500, 500));
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
