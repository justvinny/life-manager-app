package userinterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import logic.WeeklyScheduleEntriesController;

public class WeeklyScheduleRemovePanel extends JPanel {
	public static final Dimension DIMENSIONS = new Dimension(900, 720);
	
	private MainFrame mainFrame;
	
	public WeeklyScheduleRemovePanel(MainFrame root, WeeklyScheduleEntriesController weeklyEntriesController) {
		// Instance of root window.
		this.mainFrame = mainFrame;
		
		this.panelSettings();
	}
	
	private void panelSettings() {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(JournalAddPanel.DIMENSIONS);
		this.setBackground(Color.LIGHT_GRAY);
	}
}
