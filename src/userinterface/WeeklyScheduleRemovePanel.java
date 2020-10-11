package userinterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class WeeklyScheduleRemovePanel extends JPanel {
	public static final Dimension DIMENSIONS = new Dimension(900, 720);
	
	private MainFrame root;
	
	public WeeklyScheduleRemovePanel(MainFrame root) {
		// Instance of root window.
		this.root = root;
		
		this.panelSettings();
	}
	
	private void panelSettings() {
		this.setPreferredSize(JournalAddPanel.DIMENSIONS);
		this.setBackground(Color.GREEN);
	}
}
