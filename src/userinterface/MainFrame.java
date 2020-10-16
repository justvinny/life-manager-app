package userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import logic.JournalEntriesLogic;
import logic.WeeklyScheduleEntriesLogic;

/**
 * Class that contains the main JFrame for our GUI application.
 * 
 * @author Vinson Beduya 19089783
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	/**
	 * Dimension constant for frame size.
	 */
	public static final Dimension DIMENSIONS = new Dimension(1100, 720);
	
	/**
	 * Instance variables.
	 */
	private SpringLayout layout;
	private MainMenuPanel mainMenuPanel;
	private JournalAddPanel journalAddPanel;
	private JournalRemovePanel journalRemovePanel;
	private WeeklyScheduleAddPanel weeklyScheduleAddPanel;
	private WeeklyScheduleRemovePanel weeklyScheduleRemovePanel;
	private JournalEntriesLogic journalEntriesLogic;
	private WeeklyScheduleEntriesLogic weeklyEntriesLogic;
	
	/**
	 * Constructor to initialise main frame for our GUI application.
	 * 
	 * @param title
	 * @param journalEntriesLogic
	 * @param weeklyEntriesLogic
	 * @author 19089783
	 */
	public MainFrame(String title, JournalEntriesLogic journalEntriesLogic, WeeklyScheduleEntriesLogic weeklyEntriesLogic) {
		super(title);

		// Initialise fields.
		this.layout = new SpringLayout();
		this.mainMenuPanel = new MainMenuPanel(this);
		this.journalEntriesLogic = journalEntriesLogic;
		this.weeklyEntriesLogic = weeklyEntriesLogic;
		
		// Add main menu panel to frame.
		this.getContentPane().add(mainMenuPanel);
		
		// Add constraints to the panels to show them side by side.
		this.layout.putConstraint(SpringLayout.WEST, mainMenuPanel, 0, SpringLayout.WEST, this);

		// Main frame settings.
		frameSettings();
	}
	
	/**
	 * Creates the Journal Add Panel and removes any existing panel if there are any.
	 * 
	 * @author 19089783
	 */
	public void openJournalAddPanel() {
		// Remove current panel being show.
		this.removeActivePanel();
		
		// Add journal add panel.
		this.journalAddPanel = new JournalAddPanel(this, this.journalEntriesLogic);
		this.getContentPane().add(this.journalAddPanel);
		this.layout.putConstraint(SpringLayout.WEST, this.journalAddPanel, 0, SpringLayout.EAST, this.mainMenuPanel);
		this.journalAddPanel.revalidate();
		this.journalAddPanel.repaint();
	}
	
	/**
	 * Creates the Journal Remove Panel and removes any existing panel if there are any.
	 * 
	 * @author 19089783
	 */
	public void openJournalRemovePanel() {
		// Removes the current panel being shown.
		this.removeActivePanel();
		
		// Add journal remove panel.
		this.journalRemovePanel = new JournalRemovePanel(this.journalEntriesLogic);
		this.getContentPane().add(this.journalRemovePanel);
		this.layout.putConstraint(SpringLayout.WEST, this.journalRemovePanel, 0, SpringLayout.EAST, this.mainMenuPanel);
		this.journalRemovePanel.revalidate();
		this.journalRemovePanel.repaint();
	}
	
	/**
	 * Creates the Weekly Schedule Add Panel and removes any existing panel if there are any.
	 * 
	 * @author 19089783
	 */
	public void openWeeklyScheduleAddPanel() {
		// Removes current panel being shown.
		this.removeActivePanel();
				
		// Adds the weekly schedule add panel.
		this.weeklyScheduleAddPanel = new WeeklyScheduleAddPanel(this, this.weeklyEntriesLogic);
		this.getContentPane().add(this.weeklyScheduleAddPanel);
		this.layout.putConstraint(SpringLayout.WEST, this.weeklyScheduleAddPanel, 0, SpringLayout.EAST, this.mainMenuPanel);
		this.weeklyScheduleAddPanel.revalidate();
		this.weeklyScheduleAddPanel.repaint();
	}
	
	/**
	 * Creates the Weekly Schedule Remove panel and remove any existing panels being shown.
	 * 
	 * @author 19089783
	 */
	public void openWeeklyScheduleRemovePanel() {
		// Removes current panel being shown.
		this.removeActivePanel();		
		
		// Adds weekly schedule remove panel.
		this.weeklyScheduleRemovePanel = new WeeklyScheduleRemovePanel(this, this.weeklyEntriesLogic);
		this.getContentPane().add(this.weeklyScheduleRemovePanel);
		this.layout.putConstraint(SpringLayout.WEST, this.weeklyScheduleRemovePanel, 0, SpringLayout.EAST, this.mainMenuPanel);
		this.weeklyScheduleRemovePanel.revalidate();
		this.weeklyScheduleRemovePanel.repaint();
	}
	
	/**
	 * Dynamically remove the current panel being shown when another panel needs to be shown.
	 * 
	 * @author 19089783
	 */
	private void removeActivePanel() {
		// Iterates through the components of the frame and determine if there are 
		// JPanels that need to be removed.
		Component tempComponent = null;
		for (Component component : this.getContentPane().getComponents()) {
			if (component instanceof JournalAddPanel) {
				tempComponent = component;
			} else if (component instanceof JournalRemovePanel) {
				tempComponent = component;
			} else if (component instanceof WeeklyScheduleAddPanel) {
				tempComponent = component;
			} else if (component instanceof WeeklyScheduleRemovePanel) {
				tempComponent = component;
			}
		}
		
		// Removes the existing panel being shown.
		if (tempComponent != null) {
			this.getContentPane().remove(tempComponent);
		}
	}
	
	/**
	 * Frame settings.
	 * 
	 * @author 19089783
	 */
	private void frameSettings() {
		this.setLayout(layout);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(MainFrame.DIMENSIONS);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
