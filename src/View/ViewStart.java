package View;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.AbstractController;
import Controller.ControllerBombermanGame;

public class ViewStart {
	public JFrame window;
	public ViewStart(ControllerBombermanGame controller) {
	
		JFrame window = new JFrame("Bomberman");
		JPanel globalpanel = new JPanel();
		JPanel choiceJPanel = new JPanel();
		JPanel mapJPanel = new JPanel();
		JPanel levelJPanel = new JPanel();
		JPanel playJPanel = new JPanel();
		
		GridLayout globalGridlayout = new GridLayout(5, 1);
		
		String s1[] = { "niveau 1", "niveau 2" };	
		JButton buttonfileChooser = new JButton("Cliquer pour choisir");
		JLabel listMapLabel = new JLabel("Choisissez une map", SwingConstants.CENTER); 
		JComboBox<String> listLevel = new JComboBox<String>(s1);
		JLabel listLevelLabel = new JLabel("Choisissez un niveau", SwingConstants.CENTER); 
		JButton button = new JButton("Valider");
			
		globalpanel.setLayout(globalGridlayout);

		globalpanel.add(listLevelLabel);
		globalpanel.add(listLevel);
		globalpanel.add(listMapLabel);
		globalpanel.add(buttonfileChooser);
		globalpanel.add(button);
		window.add(globalpanel);
		
		buttonfileChooser.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser("./layouts/");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.lay", "lay");
				fileChooser.setFileFilter(filter);
				int returnValue = fileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String extension = selectedFile.getAbsolutePath().split("\\.")[1];
					if(extension == "lay") {
						controller.lunchGame(selectedFile.getAbsolutePath());
						window.setVisible(false);
					}else {
						System.out.print(extension);
					}
				}
			}
		});
		
		window.setSize(new Dimension(200, 200));
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
