package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import domain.Date;
import domain.Days;
import domain.TimeScheduled;
import domain.WeeklyScheduleEntry;
import logic.WeeklyScheduleEntriesLogic;

/**
 * Class that contains the user interface for the Weekyl Schedule Add panel.
 * 
 * @author Vinson Beduya 19089783
 */
@SuppressWarnings("serial")
public class WeeklyScheduleAddPanel extends JPanel {
	/**
	 * Dimension constants that will be used by various JComponents.
	 */
	public static final Dimension DIMENSIONS = new Dimension(900, 720);
	public static final Dimension HEADER_DIMENSIONS = new Dimension(900, 30);
	public static final Dimension LABEL_DIMENSIONS = new Dimension(100, 30);
	public static final Dimension FIELDS_ONE_DIM = new Dimension(750, 30);
	public static final Dimension FIELDS_TWO_DIM = new Dimension(180, 30);
	
	/**
	 * Font constants that will be used by various JComponents.
	 */
	public static final Font HEADER_FONT = new Font("Impact", Font.PLAIN, 32);
	public static final Font LABEL_FONT = new Font("Impact", Font.PLAIN, 16);
	
	/**
	 * Instance variables.
	 */
	private MainFrame mainFrame;
	private WeeklyScheduleEntriesLogic weeklyEntriesLogic;
	private SpringLayout layout;
	private JLabel labelHeader, labelTitle, labelDescription;
	private JLabel labelDay, labelStartTime, labelEndTime;
	private JTextField textFieldTitle, textFieldDescription;
	private JTextField textFieldDay, textFieldStartTime, textFieldEndTime;
	private JButton btnSave;
	
	public WeeklyScheduleAddPanel(MainFrame mainFrame, WeeklyScheduleEntriesLogic weeklyEntriesLogic) {
		// Instance of mainFrame window.
		this.mainFrame = mainFrame;
		this.weeklyEntriesLogic = weeklyEntriesLogic;
		
		// Layout
		this.layout = new SpringLayout();
		
		// Header
		this.createLabelHeader();
		
		// Initialise JComponents.
		this.createLabelTitle();
		this.createTextFieldTitle();
		this.createLabelDescription();
		this.createTextFieldDescription();
		this.createLabelDay();
		this.createTextFieldDay();
		this.createLabelStartTime();
		this.createTextFieldStartTime();
		this.createLabelEndTime();
		this.createTextFieldEndTime();
		this.createBtnSave();
		
		// Add components to panel.
		this.addComponentsToPanel();
		
		// Panel settings.
		this.panelSettings();
	}
	
	/**
	 * Create JLabel header and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelHeader() {
		this.labelHeader = new JLabel("Weekly Schedule", SwingConstants.CENTER);
		this.labelHeader.setPreferredSize(WeeklyScheduleAddPanel.HEADER_DIMENSIONS);
		this.labelHeader.setFont(WeeklyScheduleAddPanel.HEADER_FONT);
		
		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelHeader, 0, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelHeader, 150, SpringLayout.NORTH, this);
	}
	
	/**
	 * Create JLabel title and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelTitle() {
		this.labelTitle = new JLabel("Title", SwingConstants.RIGHT);
		this.labelTitle.setPreferredSize(WeeklyScheduleAddPanel.LABEL_DIMENSIONS);
		this.labelTitle.setFont(WeeklyScheduleAddPanel.LABEL_FONT);

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelTitle, 0, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelTitle, 20, SpringLayout.SOUTH, this.labelHeader);
	}
	
	/**
	 * Create JTextField title and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createTextFieldTitle() {
		this.textFieldTitle = new JTextField();
		this.textFieldTitle.setPreferredSize(WeeklyScheduleAddPanel.FIELDS_ONE_DIM);

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.textFieldTitle, 5, SpringLayout.EAST, this.labelTitle);
		this.layout.putConstraint(SpringLayout.NORTH, this.textFieldTitle, 20, SpringLayout.SOUTH, this.labelHeader);
	}
	
	/**
	 * Create JLabel description and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelDescription() {
		this.labelDescription = new JLabel("Description", SwingConstants.RIGHT);
		this.labelDescription.setPreferredSize(WeeklyScheduleAddPanel.LABEL_DIMENSIONS);
		this.labelDescription.setFont(WeeklyScheduleAddPanel.LABEL_FONT);

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelDescription, 0, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelDescription, 20, SpringLayout.SOUTH, this.labelTitle);
	}
	
	/**
	 * Create JTextField description and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createTextFieldDescription() {
		this.textFieldDescription = new JTextField();
		this.textFieldDescription.setPreferredSize(WeeklyScheduleAddPanel.FIELDS_ONE_DIM);

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.textFieldDescription, 5, SpringLayout.EAST, this.labelDescription);
		this.layout.putConstraint(SpringLayout.NORTH, this.textFieldDescription, 20, SpringLayout.SOUTH, this.textFieldTitle);
	}
	
	/**
	 * Createa JLabel day and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelDay() {
		this.labelDay = new JLabel("Day", SwingConstants.RIGHT);
		this.labelDay.setPreferredSize(WeeklyScheduleAddPanel.LABEL_DIMENSIONS);
		this.labelDay.setFont(WeeklyScheduleAddPanel.LABEL_FONT);

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelDay, 0, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelDay, 20, SpringLayout.SOUTH, this.labelDescription);
	}
	
	/**
	 * Create JTextField day and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createTextFieldDay() {
		this.textFieldDay = new JTextField();
		this.textFieldDay.setPreferredSize(WeeklyScheduleAddPanel.FIELDS_TWO_DIM);

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.textFieldDay, 5, SpringLayout.EAST, this.labelDay);
		this.layout.putConstraint(SpringLayout.NORTH, this.textFieldDay, 20, SpringLayout.SOUTH, this.textFieldDescription);
	}
	
	/**
	 * Create JLabel start time and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelStartTime() {
		this.labelStartTime = new JLabel("Start Time", SwingConstants.RIGHT);
		this.labelStartTime.setPreferredSize(WeeklyScheduleAddPanel.LABEL_DIMENSIONS);
		this.labelStartTime.setFont(WeeklyScheduleAddPanel.LABEL_FONT);

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelStartTime, 0, SpringLayout.EAST, this.textFieldDay);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelStartTime, 20, SpringLayout.SOUTH, this.textFieldDescription);
	}
	
	/**
	 * Create JLabel end time and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createTextFieldStartTime() {
		this.textFieldStartTime = new JTextField();
		this.textFieldStartTime.setPreferredSize(WeeklyScheduleAddPanel.FIELDS_TWO_DIM);

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.textFieldStartTime, 5, SpringLayout.EAST, this.labelStartTime);
		this.layout.putConstraint(SpringLayout.NORTH, this.textFieldStartTime, 20, SpringLayout.SOUTH, this.textFieldDescription);
	}
	
	/**
	 * Create JLabel end time and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelEndTime() {
		this.labelEndTime = new JLabel("End Time", SwingConstants.RIGHT);
		this.labelEndTime.setPreferredSize(WeeklyScheduleAddPanel.LABEL_DIMENSIONS);
		this.labelEndTime.setFont(WeeklyScheduleAddPanel.LABEL_FONT);

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelEndTime, 0, SpringLayout.EAST, this.textFieldStartTime);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelEndTime, 20, SpringLayout.SOUTH, this.textFieldDescription);
	}
	
	/**
	 * Create JTextField end time and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createTextFieldEndTime() {
		this.textFieldEndTime = new JTextField();
		this.textFieldEndTime.setPreferredSize(WeeklyScheduleAddPanel.FIELDS_TWO_DIM);

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.textFieldEndTime, 5, SpringLayout.EAST, this.labelEndTime);
		this.layout.putConstraint(SpringLayout.NORTH, this.textFieldEndTime, 20, SpringLayout.SOUTH, this.textFieldDescription);
	}
	
	/**
	 * Create JButton save and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createBtnSave() {
		this.btnSave = new JButton("Save");
		this.btnSave.setPreferredSize(WeeklyScheduleAddPanel.LABEL_DIMENSIONS);
		this.btnSave.setFont(WeeklyScheduleAddPanel.LABEL_FONT);
		
		// Add action listener to listen for button click on save.
		this.btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonClick();
			}
		});

		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.btnSave, 400, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.btnSave, 20, SpringLayout.SOUTH, this.textFieldEndTime);
	}
	
	/**
	 * Saves the data entered to the database and clears all the JTextFields
	 * 
	 * @author 19089783
	 */
	private void saveButtonClick() {
		try {
			// Load data from database.
			this.weeklyEntriesLogic.load();
			this.weeklyEntriesLogic.add(this.addEntry());
			
			// Clear fields.
			this.textFieldTitle.setText("");
			this.textFieldDescription.setText("");
			this.textFieldDay.setText("");
			this.textFieldStartTime.setText("");
			this.textFieldEndTime.setText("");
			
			// Save to file.
			this.weeklyEntriesLogic.save();
			
			// Show pop-up message if saved successfully.
			JOptionPane.showMessageDialog(this.mainFrame, "Saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IllegalArgumentException | InputMismatchException e) {
			// Show pop-up message if user entered bad input.
			JOptionPane.showMessageDialog(this.mainFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Use all the data provided in the GUI by the user to create a WeeklyScheduleEntry.
	 * 
	 * @return a WeeklyScheduleEntry based on user input.
	 * @author 19089783
	 */
	private WeeklyScheduleEntry addEntry() {
		// Get title.
		String title = this.textFieldTitle.getText().trim();
		
		// Get Current date.
		LocalDate today = LocalDate.now();
		Date date = new Date(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
	
		// Get Time scheduled.
		Days day = Days.valueOf(this.textFieldDay.getText().toUpperCase());
		double startTime = Double.valueOf(this.textFieldStartTime.getText());
		double endTime = Double.valueOf(this.textFieldEndTime.getText());
		TimeScheduled timeScheduled = new TimeScheduled(day, startTime, endTime);
		
		// Get description.
		String description = this.textFieldDescription.getText().trim().replaceAll("\\\n", "==newline==");
		
		return new WeeklyScheduleEntry(title, date, timeScheduled, description);
	}
	/**
	 * Add all JComponents to the panel.
	 * 
	 * @author 19089783
	 */
	private void addComponentsToPanel() {
		this.add(this.labelHeader);
		this.add(this.labelTitle);
		this.add(this.textFieldTitle);
		this.add(this.labelDescription);
		this.add(this.textFieldDescription);
		this.add(this.labelDay);
		this.add(this.textFieldDay);
		this.add(this.labelStartTime);
		this.add(this.textFieldStartTime);
		this.add(this.labelEndTime);
		this.add(this.textFieldEndTime);
		this.add(this.btnSave);
	}
	
	/**
	 * Panel settings.
	 * 
	 * @author 19089783
	 */
	private void panelSettings() {
		this.setLayout(this.layout);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(WeeklyScheduleAddPanel.DIMENSIONS);
		this.setBackground(Color.LIGHT_GRAY);
	}
}
