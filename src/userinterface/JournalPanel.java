package userinterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JournalPanel extends JPanel {
	public static final Dimension DIMENSIONS = new Dimension(900, 720);
	
	private MainFrame root;
	
	public JournalPanel(MainFrame root) {
		// Instance of root window.
		this.root = root;
		
		this.panelSettings();
	}
	
	private void panelSettings() {
		this.setPreferredSize(JournalPanel.DIMENSIONS);
		this.setBackground(Color.BLUE);
	}
}
