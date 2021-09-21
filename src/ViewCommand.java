import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class ViewCommand implements PropertyChangeListener{
	
	private JLabel NumberOfTurnJLabel ;

	public ViewCommand() {
		
		JFrame window = new JFrame("Command");
		
		JPanel Globalpanel = new JPanel();
		JPanel Highpanel = new JPanel();
		JPanel Lowpanel = new JPanel();
		JPanel LowLeftJPanel = new JPanel();
		JPanel LowRightJPanel = new JPanel();
		
		
		GridLayout GlobalGridlayout = new GridLayout(2, 1);
		GridLayout ButtonsGridLayout = new GridLayout(1, 4);
		GridLayout LowGridLayout = new GridLayout(1, 2);
		GridLayout LowLeftGridLayout = new GridLayout(2, 1);
		GridLayout LowRightGridLayout = new GridLayout(1, 1);
		
		Icon RestartIcon= new ImageIcon("icons/icon_restart.png");
		Icon StartIcon= new ImageIcon("icons/icon_play.png");
		Icon NextIcon= new ImageIcon("icons/icon_step.png");
		Icon WaitIcon= new ImageIcon("icons/icon_pause.png");
		
		JButton RestartButton = new JButton(RestartIcon);
		JButton StartButton = new JButton(StartIcon);
		JButton NextButton = new JButton(NextIcon);	
		JButton WaitButton = new JButton(WaitIcon);
		
		this.NumberOfTurnJLabel = new JLabel("Turn : 0", JLabel.CENTER);
		JLabel SliderJLabel = new JLabel("Number of turns per second", JLabel.CENTER);
		
		JSlider Slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 2);
		
		window.setSize(new Dimension(800, 400));
		window.setLocationRelativeTo(null);
		
		Globalpanel.setLayout(GlobalGridlayout);
		Highpanel.setLayout(ButtonsGridLayout);
		Lowpanel.setLayout(LowGridLayout);
		Globalpanel.add(Highpanel);	
		Globalpanel.add(Lowpanel);
			
		Highpanel.add(RestartButton);
		Highpanel.add(StartButton);
		Highpanel.add(NextButton);
		Highpanel.add(WaitButton);
		
		LowLeftJPanel.setLayout(LowLeftGridLayout);
		LowRightJPanel.setLayout(LowRightGridLayout);
		Lowpanel.add(LowLeftJPanel);
		Lowpanel.add(LowRightJPanel);
		
		LowRightJPanel.add(NumberOfTurnJLabel);
		LowLeftJPanel.add(SliderJLabel);
		Slider.setMajorTickSpacing(1);
		Slider.setMinorTickSpacing(1);
		Slider.setPaintTicks(true);
		Slider.setPaintLabels(true);
		LowLeftJPanel.add(Slider);
		
		window.add(Globalpanel);
		
		window.setVisible(true);
		
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		int value = (int) arg0.getNewValue();
		this.NumberOfTurnJLabel.setText(value + "");
	}
}
