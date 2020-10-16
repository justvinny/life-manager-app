package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Class that contains the JPanel user interface for the Main Menu used for navigation.
 * 
 * @author Vinson Beduya 19089783
 */
@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {
	/**
	 * Dimension constants that will be used by various JComponents.
	 */
	public static final Dimension DIMENSIONS = new Dimension(200, 720);
	public static final Dimension MAIN_COMP_DIMENSIONS = new Dimension(200, 40);
	public static final Dimension FILLER_DIMENSIONS = new Dimension(200, 60);
	public static final Dimension FILLER_BOT_DIMENSIONS = new Dimension(200, 2);
	
	/**
	 * Font constants that will be used by various JComponents.
	 */
	public static final Font MAIN_COMP_FONT = new Font("Impact", Font.PLAIN, 20);
	public static final Font BTN_SUB_FONT = new Font("Impact", Font.PLAIN, 14);
	
	/**
	 * Instance variables.
	 * 
	 */
	private MainFrame mainFrame;
	private BoxLayout layout;
	private JLabel fillerTopLabel1, fillerTopLabel2, fillerBottomLabel;
	private JLabel labelJournal, labelWeeklySchedule;
	private JButton btnJournalAdd, btnWeeklyScheduleAdd;
	private JButton btnJournalRemove, btnWeeklyScheduleRemove;
	private JButton btnExit;
	
	/**
	 * Constructor to initialise the main menu panel.
	 * 
	 * @param mainFrame is the instance of the main JFrame.
	 * @author 19089783
	 */
	public MainMenuPanel(MainFrame mainFrame) {
		// Instance of mainFrame window.
		this.mainFrame = mainFrame;
		
		// Initialise box layout.
		this.layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
				
		// Initialise components for panel.
		this.createFillerLabelsForMenu();
		this.createLabelJournal();
		this.createBtnJournalAdd();
		this.createBtnJournalRemove();
		this.createLabelWeeklySchedule();
		this.createBtnWeeklyScheduleAdd();
		this.createBtnWeeklyScheduleRemove();
		this.createBtnExit();
		
		// Add components to panel
		this.addComponentsToPanel();

		// Panel settings
		this.panelSettings();
	}
	
	/**
	 * Creates filler labels for menu spacing.
	 * 
	 * @author 19089783
	 */
	private void createFillerLabelsForMenu() {
		// Dark Gray top filler for menu.
		this.fillerTopLabel1 = new JLabel("");
		this.fillerTopLabel1.setMinimumSize(FILLER_DIMENSIONS);
		this.fillerTopLabel1.setPreferredSize(FILLER_DIMENSIONS);
		this.fillerTopLabel1.setMaximumSize(FILLER_DIMENSIONS);
		
		// Gray top filler for menu.
		this.fillerTopLabel2 = new JLabel("");
		this.fillerTopLabel2.setBackground(Color.BLACK);
		this.fillerTopLabel2.setOpaque(true);
		this.fillerTopLabel2.setMinimumSize(FILLER_BOT_DIMENSIONS);
		this.fillerTopLabel2.setPreferredSize(FILLER_BOT_DIMENSIONS);
		this.fillerTopLabel2.setMaximumSize(FILLER_BOT_DIMENSIONS);
		
		// Gray bottom filler for menu.
		this.fillerBottomLabel = new JLabel("");
		this.fillerBottomLabel.setBackground(Color.BLACK);
		this.fillerBottomLabel.setOpaque(true);
		this.fillerBottomLabel.setMinimumSize(FILLER_BOT_DIMENSIONS);
		this.fillerBottomLabel.setPreferredSize(FILLER_BOT_DIMENSIONS);
		this.fillerBottomLabel.setMaximumSize(FILLER_BOT_DIMENSIONS);
	}
	
	/**
	 * Creates the label header for the journal buttons.
	 * 
	 * @author 19089783
	 */
	private void createLabelJournal() {
		this.labelJournal = new JLabel("Journal", SwingConstants.CENTER);
		this.labelJournal.setFont(MAIN_COMP_FONT);
		this.labelJournal.setForeground(Color.BLACK);
		this.labelJournal.setBackground(Color.LIGHT_GRAY);
		this.labelJournal.setOpaque(true);
		this.labelJournal.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.labelJournal.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.labelJournal.setMaximumSize(MAIN_COMP_DIMENSIONS);
	}
	
	/**
	 * Creates the button that will be used for navigating to the Journal Add panel.
	 * 
	 * @author 19089783
	 */
	private void createBtnJournalAdd() {
		this.btnJournalAdd = new JButton("> Add Entry");
		this.btnJournalAdd.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnJournalAdd.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalAdd.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalAdd.setMaximumSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalAdd.setBackground(Color.LIGHT_GRAY);
		this.btnJournalAdd.setForeground(Color.BLACK);
		this.btnJournalAdd.setFont(BTN_SUB_FONT);
		
		// Mouse listener to change the button background when mouse hovers.
		this.btnJournalAdd.addMouseListener(new ButtonMouseListener());
		
		// Action listener for the journal add button click.
		this.btnJournalAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.openJournalAddPanel();
			}
		});
	}
	
	/**
	 * Creates the button that will be used to navigate to the journal remove panel.
	 * 
	 * @author 19089783
	 */
	private void createBtnJournalRemove() {
		this.btnJournalRemove = new JButton("> View / Remove Entry");
		this.btnJournalRemove.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnJournalRemove.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalRemove.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalRemove.setMaximumSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalRemove.setBackground(Color.LIGHT_GRAY);
		this.btnJournalRemove.setForeground(Color.BLACK);
		this.btnJournalRemove.setFont(BTN_SUB_FONT);
		
		// Mouse listener to change the button background when the mouse hovers.
		this.btnJournalRemove.addMouseListener(new ButtonMouseListener());
		
		// Action listener to listen for button click of journal remove button.
		this.btnJournalRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.openJournalRemovePanel();
			}
		});		
	}
	
	/**
	 * Create the weekly schedule label that is the header for the weekly schedule buttons.
	 * 
	 * @author 19089783
	 */
	private void createLabelWeeklySchedule() {
		this.labelWeeklySchedule = new JLabel("Weekly Schedule", SwingConstants.CENTER);
		this.labelWeeklySchedule.setFont(MAIN_COMP_FONT);
		this.labelWeeklySchedule.setForeground(Color.BLACK);
		this.labelWeeklySchedule.setBackground(Color.LIGHT_GRAY);
		this.labelWeeklySchedule.setOpaque(true);
		this.labelWeeklySchedule.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.labelWeeklySchedule.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.labelWeeklySchedule.setMaximumSize(MAIN_COMP_DIMENSIONS);
	}
	
	/**
	 * Create the button that navigates to the weekly schedule add panel.
	 * 
	 * @author 19089783
	 */
	private void createBtnWeeklyScheduleAdd() {
		this.btnWeeklyScheduleAdd = new JButton("> Add Entry");
		this.btnWeeklyScheduleAdd.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnWeeklyScheduleAdd.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleAdd.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleAdd.setMaximumSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleAdd.setBackground(Color.LIGHT_GRAY);
		this.btnWeeklyScheduleAdd.setForeground(Color.BLACK);
		this.btnWeeklyScheduleAdd.setFont(BTN_SUB_FONT);
		
		// Mouse listener to change the button background on mouse hover.
		this.btnWeeklyScheduleAdd.addMouseListener(new ButtonMouseListener());
		
		// Action listener to listen for weekly schedule add button click.
		this.btnWeeklyScheduleAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.openWeeklyScheduleAddPanel();
			}
		});
	}
	
	/**
	 * Creates the weekly schedule remove button that navigates to the weekly schedule remove
	 * panel.
	 * 
	 * @author 19089783
	 */
	private void createBtnWeeklyScheduleRemove() {
		this.btnWeeklyScheduleRemove = new JButton("> View / Remove Entry");
		this.btnWeeklyScheduleRemove.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnWeeklyScheduleRemove.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleRemove.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleRemove.setMaximumSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleRemove.setBackground(Color.LIGHT_GRAY);
		this.btnWeeklyScheduleRemove.setForeground(Color.BLACK);
		this.btnWeeklyScheduleRemove.setFont(BTN_SUB_FONT);
		
		// Adds a mouse listener to change the background of the button on mouse hover.
		this.btnWeeklyScheduleRemove.addMouseListener(new ButtonMouseListener());
		
		// Add an action listener to listen for weekly schedule remove button click.
		this.btnWeeklyScheduleRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.openWeeklyScheduleRemovePanel();
			}
		});
	}
	
	/**
	 * Creates the exit button that exits that application.
	 * 
	 * @author 19089783
	 */
	private void createBtnExit() {
		// Exit Button
		this.btnExit = new JButton("Exit");
		this.btnExit.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.btnExit.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.btnExit.setMaximumSize(MAIN_COMP_DIMENSIONS);
		this.btnExit.setBackground(Color.LIGHT_GRAY);
		this.btnExit.setForeground(Color.BLACK);
		this.btnExit.setFont(MAIN_COMP_FONT);
		
		// Adds mouse listener to change the button background when mouse hovers.
		this.btnExit.addMouseListener(new ButtonMouseListener());
		
		// Adds action listener to listen for button clicks on exit button.
		this.btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
	}
	
	/**
	 * Add all components to the panel.
	 * 
	 * @author 19089783
	 */
	private void addComponentsToPanel() {
		this.add(this.fillerTopLabel1);
		this.add(this.fillerTopLabel2);
		this.add(this.labelJournal);
		this.add(this.btnJournalAdd);
		this.add(this.btnJournalRemove);
		this.add(this.labelWeeklySchedule);
		this.add(this.btnWeeklyScheduleAdd);
		this.add(this.btnWeeklyScheduleRemove);
		this.add(this.btnExit);
		this.add(this.fillerBottomLabel);
	}

	/**
	 * Panel settings.
	 * 
	 * @author 19089783
	 */
	private void panelSettings() {
		this.setLayout(layout);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(MainMenuPanel.DIMENSIONS);
		this.setBackground(Color.DARK_GRAY);
	}
	
	/**
	 * Mouse listener that will change the background colour of menu buttons when the mouse
	 * hovers over them to dark gray.
	 * 
	 * @author Vinson Beduya 19089783
	 */
	private class ButtonMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			((JButton)e.getSource()).setBackground(Color.DARK_GRAY);
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			((JButton)e.getSource()).setBackground(Color.DARK_GRAY);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			((JButton)e.getSource()).setBackground(Color.LIGHT_GRAY);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			((JButton)e.getSource()).setBackground(Color.DARK_GRAY);
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			((JButton)e.getSource()).setBackground(Color.LIGHT_GRAY);
			
		}
	}
}
