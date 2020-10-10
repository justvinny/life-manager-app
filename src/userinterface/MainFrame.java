package userinterface;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import logic.JournalEntriesController;
import logic.WeeklyScheduleEntriesController;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public static final Dimension DIMENSIONS = new Dimension(1100, 720);
	
	private SpringLayout layout;
	private MainMenuPanel mainMenuPanel;
	private JournalPanel journalPanel;
	private WeeklySchedulePanel weeklySchedulePanel;
	
	public MainFrame(String title, JournalEntriesController journalEntriesController, WeeklyScheduleEntriesController weeklyEntriesController) {
		super(title);

		// Initialize fields.
		this.layout = new SpringLayout();
		this.mainMenuPanel = new MainMenuPanel(this);
		this.journalPanel = new JournalPanel(this);
		this.weeklySchedulePanel = new WeeklySchedulePanel(this);
		
		// Add main menu panel to frame.
		this.getContentPane().add(mainMenuPanel);
		
		// Add constraints to the panels to show them side by side.
		this.layout.putConstraint(SpringLayout.WEST, mainMenuPanel, 0, SpringLayout.WEST, this);

		// Main frame settings.
		frameSettings();
	}
	
	public void openJournalPanel() {
		Component tempComponent = null;
		for (Component component : this.getContentPane().getComponents()) {
			if (component instanceof WeeklySchedulePanel) {
				tempComponent = component;
			}
		}
		
		if (tempComponent != null) {
			this.getContentPane().remove(tempComponent);
		}
		
		this.getContentPane().add(this.journalPanel);
		this.layout.putConstraint(SpringLayout.WEST, journalPanel, 0, SpringLayout.EAST, mainMenuPanel);
		this.journalPanel.revalidate();
		this.journalPanel.repaint();
	}
	
	public void openWeeklySchedulePanel() {
		Component tempComponent = null;
		for (Component component : this.getContentPane().getComponents()) {
			if (component instanceof JournalPanel) {
				tempComponent = component;
			}
		}
		
		if (tempComponent != null) {
			this.getContentPane().remove(tempComponent);
		}
		
		Arrays.stream(this.getContentPane().getComponents()).forEach(System.out::println);
		this.getContentPane().add(this.weeklySchedulePanel);
		this.layout.putConstraint(SpringLayout.WEST, weeklySchedulePanel, 0, SpringLayout.EAST, mainMenuPanel);
		this.weeklySchedulePanel.revalidate();
		this.weeklySchedulePanel.repaint();
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
