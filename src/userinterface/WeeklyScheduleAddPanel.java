package userinterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WeeklyScheduleAddPanel extends JPanel {
	public static final Dimension DIMENSIONS = new Dimension(900, 720);
	
	private MainFrame mainFrame;
	
	public WeeklyScheduleAddPanel(MainFrame root) {
		// Instance of root window.
		this.MainFrame = mainFrame;
		
		this.panelSettings();
	}
	
	private void panelSettings() {
		this.setPreferredSize(JournalAddPanel.DIMENSIONS);
		this.setBackground(Color.RED);
	}
}
