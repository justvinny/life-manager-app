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

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {
	public static final Dimension DIMENSIONS = new Dimension(200, 720);
	public static final Dimension MAIN_COMP_DIMENSIONS = new Dimension(200, 40);
	public static final Dimension FILLER_DIMENSIONS = new Dimension(200, 60);
	public static final Dimension FILLER_BOT_DIMENSIONS = new Dimension(200, 2);
	public static final Font MAIN_COMP_FONT = new Font("Impact", Font.PLAIN, 20);
	public static final Font BTN_SUB_FONT = new Font("Impact", Font.PLAIN, 14);
	
	private MainFrame root;
	private BoxLayout layout;
	private JLabel fillerTopLabel1, fillerTopLabel2, fillerBottomLabel;
	private JLabel labelJournal, labelWeeklySchedule;
	private JButton btnJournalAdd, btnWeeklyScheduleAdd;
	private JButton btnJournalRemove, btnWeeklyScheduleRemove;
	private JButton btnExit;
	
	public MainMenuPanel(MainFrame root) {
		// Instance of root window.
		this.root = root;
		
		// Initialize box layout.
		this.layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
				
		// Initialize components for panel.
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
	
	private void createBtnJournalAdd() {
		this.btnJournalAdd = new JButton("> Add Entry");
		this.btnJournalAdd.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnJournalAdd.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalAdd.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalAdd.setMaximumSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalAdd.setBackground(Color.LIGHT_GRAY);
		this.btnJournalAdd.setForeground(Color.BLACK);
		this.btnJournalAdd.setFont(BTN_SUB_FONT);
		this.btnJournalAdd.addMouseListener(new ButtonMouseListener());
		this.btnJournalAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				root.openJournalAddPanel();
			}
		});
	}
	
	private void createBtnJournalRemove() {
		this.btnJournalRemove = new JButton("> View / Remove Entry");
		this.btnJournalRemove.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnJournalRemove.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalRemove.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalRemove.setMaximumSize(MAIN_COMP_DIMENSIONS);
		this.btnJournalRemove.setBackground(Color.LIGHT_GRAY);
		this.btnJournalRemove.setForeground(Color.BLACK);
		this.btnJournalRemove.setFont(BTN_SUB_FONT);
		this.btnJournalRemove.addMouseListener(new ButtonMouseListener());
		this.btnJournalRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				root.openJournalRemovePanel();
			}
		});		
	}
	
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
	
	private void createBtnWeeklyScheduleAdd() {
		this.btnWeeklyScheduleAdd = new JButton("> Add Entry");
		this.btnWeeklyScheduleAdd.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnWeeklyScheduleAdd.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleAdd.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleAdd.setMaximumSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleAdd.setBackground(Color.LIGHT_GRAY);
		this.btnWeeklyScheduleAdd.setForeground(Color.BLACK);
		this.btnWeeklyScheduleAdd.setFont(BTN_SUB_FONT);
		this.btnWeeklyScheduleAdd.addMouseListener(new ButtonMouseListener());
		this.btnWeeklyScheduleAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				root.openWeeklyScheduleAddPanel();
			}
		});
	}
	
	private void createBtnWeeklyScheduleRemove() {
		this.btnWeeklyScheduleRemove = new JButton("> View / Remove Entry");
		this.btnWeeklyScheduleRemove.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnWeeklyScheduleRemove.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleRemove.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleRemove.setMaximumSize(MAIN_COMP_DIMENSIONS);
		this.btnWeeklyScheduleRemove.setBackground(Color.LIGHT_GRAY);
		this.btnWeeklyScheduleRemove.setForeground(Color.BLACK);
		this.btnWeeklyScheduleRemove.setFont(BTN_SUB_FONT);
		this.btnWeeklyScheduleRemove.addMouseListener(new ButtonMouseListener());
		this.btnWeeklyScheduleRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				root.openWeeklyScheduleRemovePanel();
			}
		});
	}
	
	private void createBtnExit() {
		// Exit Button
		this.btnExit = new JButton("Exit");
		this.btnExit.setMinimumSize(MAIN_COMP_DIMENSIONS);
		this.btnExit.setPreferredSize(MAIN_COMP_DIMENSIONS);
		this.btnExit.setMaximumSize(MAIN_COMP_DIMENSIONS);
		this.btnExit.setBackground(Color.LIGHT_GRAY);
		this.btnExit.setForeground(Color.BLACK);
		this.btnExit.setFont(MAIN_COMP_FONT);
		this.btnExit.addMouseListener(new ButtonMouseListener());
		this.btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				root.dispose();
			}
		});
	}
	
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

	private void panelSettings() {
		this.setLayout(layout);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(MainMenuPanel.DIMENSIONS);
		this.setBackground(Color.DARK_GRAY);
	}
	
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
