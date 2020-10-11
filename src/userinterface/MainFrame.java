package userinterface;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import logic.JournalEntriesController;
import logic.WeeklyScheduleEntriesController;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public static final Dimension DIMENSIONS = new Dimension(1100, 720);
	
	private SpringLayout layout;
	private MainMenuPanel mainMenuPanel;
	private JournalAddPanel journalAddPanel;
	private JournalRemovePanel journalRemovePanel;
	private WeeklyScheduleAddPanel weeklyScheduleAddPanel;
	private WeeklyScheduleRemovePanel weeklyScheduleRemovePanel;
	private JournalEntriesController journalEntriesController;
	private WeeklyScheduleEntriesController weeklyEntriesController;
	
	public MainFrame(String title, JournalEntriesController journalEntriesController, WeeklyScheduleEntriesController weeklyEntriesController) {
		super(title);

		// Initialize fields.
		this.layout = new SpringLayout();
		this.mainMenuPanel = new MainMenuPanel(this);
		this.journalEntriesController = journalEntriesController;
		this.weeklyEntriesController = weeklyEntriesController;
		
		// Add main menu panel to frame.
		this.getContentPane().add(mainMenuPanel);
		
		// Add constraints to the panels to show them side by side.
		this.layout.putConstraint(SpringLayout.WEST, mainMenuPanel, 0, SpringLayout.WEST, this);

		// Main frame settings.
		frameSettings();
	}
	
	public void openJournalAddPanel() {
		this.journalAddPanel = new JournalAddPanel(this, this.journalEntriesController);
		
		this.removeActivePanel();
		
		this.getContentPane().add(this.journalAddPanel);
		this.layout.putConstraint(SpringLayout.WEST, this.journalAddPanel, 0, SpringLayout.EAST, this.mainMenuPanel);
		this.journalAddPanel.revalidate();
		this.journalAddPanel.repaint();
	}
	
	public void openJournalRemovePanel() {
		this.journalRemovePanel = new JournalRemovePanel(this.journalEntriesController);

		this.removeActivePanel();
		
		this.getContentPane().add(this.journalRemovePanel);
		this.layout.putConstraint(SpringLayout.WEST, this.journalRemovePanel, 0, SpringLayout.EAST, this.mainMenuPanel);
		this.journalRemovePanel.revalidate();
		this.journalRemovePanel.repaint();
	}
	
	public void openWeeklyScheduleAddPanel() {
		this.weeklyScheduleAddPanel = new WeeklyScheduleAddPanel(this);
		
		this.removeActivePanel();
		
		this.getContentPane().add(this.weeklyScheduleAddPanel);
		this.layout.putConstraint(SpringLayout.WEST, this.weeklyScheduleAddPanel, 0, SpringLayout.EAST, this.mainMenuPanel);
		this.weeklyScheduleAddPanel.revalidate();
		this.weeklyScheduleAddPanel.repaint();
	}
	
	public void openWeeklyScheduleRemovePanel() {
		this.weeklyScheduleRemovePanel = new WeeklyScheduleRemovePanel(this);
		
		this.removeActivePanel();
		
		this.getContentPane().add(this.weeklyScheduleRemovePanel);
		this.layout.putConstraint(SpringLayout.WEST, this.weeklyScheduleRemovePanel, 0, SpringLayout.EAST, this.mainMenuPanel);
		this.weeklyScheduleRemovePanel.revalidate();
		this.weeklyScheduleRemovePanel.repaint();
	}
	
	private void removeActivePanel() {
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
		
		if (tempComponent != null) {
			this.getContentPane().remove(tempComponent);
		}
	}
	
	private void frameSettings() {
		this.setLayout(layout);
		this.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(MainFrame.DIMENSIONS);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
