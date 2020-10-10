package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {
	public static final Dimension DIMENSIONS = new Dimension(200, 720);
	public static final Dimension BTN_DIMENSIONS = new Dimension(200, 40);
	public static final Dimension FILLER_DIMENSIONS = new Dimension(200, 60);
	public static final Dimension FILLER_BOT_DIMENSIONS = new Dimension(200, 180);
	public static final Font BTN_FONT = new Font("Impact", Font.PLAIN, 20);
	
	private MainFrame root;
	private BoxLayout layout;
	private JLabel fillerTopLabel1, fillerTopLabel2, fillerBottomLabel;
	private JButton btnJournal, btnWeeklySchedule, btnExit;
	
	public MainMenuPanel(MainFrame root) {
		// Instance of root window.
		this.root = root;
		
		// Initialize box layout.
		this.layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
				
		// Initialize components for panel.
		this.createFillerLabelsForMenu();
		this.createBtnJournal();
		this.createBtnWeeklySchedule();
		this.createBtnExit();
		
		// Add components to panel
		this.addComponentsToPanel();

		// Panel settings
		this.panelSettings();
	}
	
	private void createFillerLabelsForMenu() {
		// Dark Gray top filler for menu.
		this.fillerTopLabel1 = new JLabel("");
		this.fillerTopLabel1.setMinimumSize(FILLER_DIMENSIONS);
		this.fillerTopLabel1.setPreferredSize(FILLER_DIMENSIONS);
		this.fillerTopLabel1.setMaximumSize(FILLER_DIMENSIONS);
		
		// Gray top filler for menu.
		this.fillerTopLabel2 = new JLabel("");
		this.fillerTopLabel2.setBackground(Color.GRAY);
		this.fillerTopLabel2.setOpaque(true);
		this.fillerTopLabel2.setMinimumSize(FILLER_DIMENSIONS);
		this.fillerTopLabel2.setPreferredSize(FILLER_DIMENSIONS);
		this.fillerTopLabel2.setMaximumSize(FILLER_DIMENSIONS);
		
		// Gray bottom filler for menu.
		this.fillerBottomLabel = new JLabel("");
		this.fillerBottomLabel.setBackground(Color.GRAY);
		this.fillerBottomLabel.setOpaque(true);
		this.fillerBottomLabel.setMinimumSize(FILLER_BOT_DIMENSIONS);
		this.fillerBottomLabel.setPreferredSize(FILLER_BOT_DIMENSIONS);
		this.fillerBottomLabel.setMaximumSize(FILLER_BOT_DIMENSIONS);
	}
	
	private void createBtnJournal() {
		// Journal Button
		this.btnJournal = new JButton("Journal");
		this.btnJournal.setMinimumSize(BTN_DIMENSIONS);
		this.btnJournal.setPreferredSize(BTN_DIMENSIONS);
		this.btnJournal.setMaximumSize(BTN_DIMENSIONS);
		this.btnJournal.setBackground(Color.GRAY);
		this.btnJournal.setForeground(Color.WHITE);
		this.btnJournal.setFont(BTN_FONT);
		this.btnJournal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				root.openJournalPanel();
			}
		});
	}
	
	private void createBtnWeeklySchedule() {
		// Weekly Schedule Button
		this.btnWeeklySchedule = new JButton("Weekly Schedule");
		this.btnWeeklySchedule.setMinimumSize(BTN_DIMENSIONS);
		this.btnWeeklySchedule.setPreferredSize(BTN_DIMENSIONS);
		this.btnWeeklySchedule.setMaximumSize(BTN_DIMENSIONS);
		this.btnWeeklySchedule.setBackground(Color.GRAY);
		this.btnWeeklySchedule.setForeground(Color.WHITE);
		this.btnWeeklySchedule.setFont(BTN_FONT);
		this.btnWeeklySchedule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				root.openWeeklySchedulePanel();
			}
		});
	}
	
	private void createBtnExit() {
		// Exit Button
		this.btnExit = new JButton("Exit");
		this.btnExit.setMinimumSize(BTN_DIMENSIONS);
		this.btnExit.setPreferredSize(BTN_DIMENSIONS);
		this.btnExit.setMaximumSize(BTN_DIMENSIONS);
		this.btnExit.setBackground(Color.GRAY);
		this.btnExit.setForeground(Color.WHITE);
		this.btnExit.setFont(BTN_FONT);
		this.btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				root.dispose();
			}
		});
	}
	
	private void addComponentsToPanel() {
		this.add(fillerTopLabel1);
		this.add(fillerTopLabel2);
		this.add(btnJournal);
		this.add(btnWeeklySchedule);
		this.add(btnExit);
		this.add(fillerBottomLabel);
	}

	private void panelSettings() {
		this.setLayout(layout);
		this.setPreferredSize(MainMenuPanel.DIMENSIONS);
		this.setBackground(Color.DARK_GRAY);
	}
}
