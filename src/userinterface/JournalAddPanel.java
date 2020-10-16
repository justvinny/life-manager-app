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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import domain.Date;
import domain.JournalEntry;
import logic.JournalEntriesLogic;

/**
 * Class that contains the JPanel user interface for adding Journal Entries.
 * 
 * @author Vinson Beduya 19089783
 */
@SuppressWarnings("serial")
public class JournalAddPanel extends JPanel {
	/**
	 * Dimension constants that will be used by various JComponents.
	 */
	public static final Dimension DIMENSIONS = new Dimension(900, 720);
	public static final Dimension HEADER_DIM = new Dimension(900, 30);
	public static final Dimension TEXT_FIELD_DIM = new Dimension(800, 25);
	public static final Dimension TEXT_AREA_DIM = new Dimension(850, 480);
	
	/**
	 * Font constants that will be used by various JComponents.
	 */
	public static final Font HEADER_FONT = new Font("Impact", Font.PLAIN, 32);
	public static final Font LABEL_FONT = new Font("Impact", Font.PLAIN, 16);
	
	/**
	 * Instance variables. 
	 */
	private MainFrame mainFrame;
	private JournalEntriesLogic journalEntriesLogic;
	private SpringLayout layout;
	private JLabel labelHeader, labelTitle, labelBody;
	private JTextField textFieldTitle;
	private JTextArea textAreaBody;
	private JScrollPane scrollPane;
	private JButton btnSave;
	
	/**
	 * Constructor to initialise the Journal Entry add panel. 
	 * 
	 * @param mainFrame instance of the main JFrame.
	 * @param journalEntriesLogic instance of journalEntriesLogic that contains the data. 
	 * @author 19089783
	 */
	public JournalAddPanel(MainFrame mainFrame, JournalEntriesLogic journalEntriesLogic) {
		// Instance of root window.
		this.mainFrame = mainFrame;
		
		// Instance of journalEntriesLogic.
		this.journalEntriesLogic = journalEntriesLogic;
		
		// Layout
		this.layout = new SpringLayout();
		
		// Initialise components.
		createLabelHeader();
		createLabelTitle();
		createTextFieldTitle();
		createLabelBody();
		createTextAreaBody();
		createBtnSave();
		
		// Add components to the panel.
		addComponentsToPanel();
		
		// Panel settings.
		this.panelSettings();
	}
	
	/**
	 * Creates the JLabel for the header and sets its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelHeader() {
		this.labelHeader = new JLabel("Journal Entry", SwingConstants.CENTER);
		this.labelHeader.setFont(JournalAddPanel.HEADER_FONT);
		this.labelHeader.setPreferredSize(JournalAddPanel.HEADER_DIM);
		
		// Positioning to spring layout.
		this.layout.putConstraint(SpringLayout.NORTH, this.labelHeader, 20, SpringLayout.NORTH, this);
	}
	
	
	/**
	 * Creates the JLabel for the title and sets its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelTitle() {
		this.labelTitle = new JLabel("Title", SwingConstants.RIGHT);
		this.labelTitle.setFont(JournalAddPanel.LABEL_FONT);
		
		// Positioning to spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelTitle, 20, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelTitle, 20, SpringLayout.SOUTH, this.labelHeader);
	}
	
	/**
	 * Creates the JTextField for the field title and sets its settings.
	 * 
	 * @author 19089783
	 */
	private void createTextFieldTitle() {
		this.textFieldTitle = new JTextField();
		this.textFieldTitle.setPreferredSize(JournalAddPanel.TEXT_FIELD_DIM);
		
		// Positioning to spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.textFieldTitle, 20, SpringLayout.EAST, this.labelTitle);
		this.layout.putConstraint(SpringLayout.NORTH, this.textFieldTitle, 20, SpringLayout.SOUTH, this.labelHeader);
	}
	
	/**
	 * Creates the JLabel for the body header and sets its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelBody() {
		this.labelBody = new JLabel("Body", SwingConstants.RIGHT);
		this.labelBody.setFont(JournalAddPanel.LABEL_FONT);
		
		// Positioning to spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelBody, 20, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelBody, 10, SpringLayout.SOUTH, this.labelTitle);
	}
	
	/**
	 * Creates the JTextArea for the body and its settings.
	 * 
	 * @author 19089783
	 */
	private void createTextAreaBody() {
		this.textAreaBody = new JTextArea(5, 30);
		this.textAreaBody.setLineWrap(true);
		this.textAreaBody.setWrapStyleWord(true);
		
		// Scroll pane that will activate if the body gets long.
		this.scrollPane = new JScrollPane(this.textAreaBody);
		this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.scrollPane.setPreferredSize(JournalAddPanel.TEXT_AREA_DIM);
		
		// Positioning to spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.scrollPane, 20, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.scrollPane, 5, SpringLayout.SOUTH, this.labelBody);
	}
	
	/**
	 * Creates the JButton for save and its settings.
	 * 
	 * @author 19089783
	 */
	private void createBtnSave() {
		this.btnSave = new JButton("Save");
		this.btnSave.setFont(JournalAddPanel.LABEL_FONT);
		
		// Add the action listener to listen for button click.
		this.btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonClick();
			}
		});
		
		// Positioning to spring layout.
		this.layout.putConstraint(SpringLayout.EAST, this.btnSave, -30, SpringLayout.EAST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.btnSave, 20, SpringLayout.SOUTH, this.scrollPane);
	}
	
	/**
	 * Helper method that responds to a button click for save which saved the data entered
	 * in the GUI to the database. 
	 * 
	 * @author 19089783
	 */
	private void saveButtonClick() {
		try {
			// Load data from database.
			this.journalEntriesLogic.load();
			this.journalEntriesLogic.add(this.addEntry());
			
			// Clear fields.
			this.textFieldTitle.setText("");
			this.textAreaBody.setText("");
			
			// Save to file.
			this.journalEntriesLogic.save();
			
			// Show pop-up message if saved successfully.
			JOptionPane.showMessageDialog(this.mainFrame, "Saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IllegalArgumentException | InputMismatchException e) {
			// Show pop-up message if user entered bad input.
			JOptionPane.showMessageDialog(this.mainFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Make a JournalEntry object based on the user input from the GUI.
	 * 
	 * @return
	 * @author 19089783
	 */
	private JournalEntry addEntry() {
		// Get title.
		String title = this.textFieldTitle.getText().trim();
		
		// Get Current date.
		LocalDate today = LocalDate.now();
		Date date = new Date(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
	
		// Get description.
		String description = this.textAreaBody.getText().trim().replaceAll("\\\n", "==newline==");
		
		return new JournalEntry(title, date, description);
	}
	
	/**
	 * Adding all the JComponents to the panel.
	 * 
	 * @author 19089783
	 */
	private void addComponentsToPanel() {
		this.add(this.labelHeader);
		this.add(this.labelTitle);
		this.add(this.textFieldTitle);
		this.add(this.labelBody);
		this.add(this.scrollPane);
		this.add(this.btnSave);
	}
	
	/**
	 * JPanel settings.
	 * 
	 * @author 19089783
	 */
	private void panelSettings() {
		this.setLayout(this.layout);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(JournalAddPanel.DIMENSIONS);
		this.setBackground(Color.LIGHT_GRAY);
	}
}
