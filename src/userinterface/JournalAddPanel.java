package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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
import logic.JournalEntriesController;
import userexceptions.EntryScheduleException;

@SuppressWarnings("serial")
public class JournalAddPanel extends JPanel {
	public static final Dimension DIMENSIONS = new Dimension(900, 720);
	public static final Dimension HEADER_DIM = new Dimension(900, 30);
	public static final Dimension TEXT_FIELD_DIM = new Dimension(800, 25);
	public static final Dimension TEXT_AREA_DIM = new Dimension(850, 480);
	public static final Font HEADER_FONT = new Font("Impact", Font.PLAIN, 32);
	public static final Font LABEL_FONT = new Font("Impact", Font.PLAIN, 16);
	
	private MainFrame mainFrame;
	private JournalEntriesController journalEntriesController;
	private SpringLayout layout;
	private JLabel labelHeader, labelTitle, labelBody;
	private JTextField textFieldTitle;
	private JTextArea textAreaBody;
	private JScrollPane scrollPane;
	private JButton btnSave;
	
	public JournalAddPanel(MainFrame mainFrame, JournalEntriesController journalEntriesController) {
		// Instance of root window.
		this.mainFrame = mainFrame;
		this.journalEntriesController = journalEntriesController;
		
		// Layout
		this.layout = new SpringLayout();
		
		// Initialize components.
		createLabelHeader();
		createLabelTitle();
		createTextFieldTitle();
		createBodyTitle();
		createTextAreaBody();
		createBtnSave();
		
		// Add components to the panel.
		addComponentsToPanel();
		
		// Panel settings.
		this.panelSettings();
	}
	
	private void createLabelHeader() {
		this.labelHeader = new JLabel("Journal Entry", SwingConstants.CENTER);
		this.labelHeader.setFont(JournalAddPanel.HEADER_FONT);
		this.labelHeader.setPreferredSize(JournalAddPanel.HEADER_DIM);
		
		this.layout.putConstraint(SpringLayout.NORTH, this.labelHeader, 20, SpringLayout.NORTH, this);
	}
	
	private void createLabelTitle() {
		this.labelTitle = new JLabel("Title", SwingConstants.RIGHT);
		this.labelTitle.setFont(JournalAddPanel.LABEL_FONT);
		
		this.layout.putConstraint(SpringLayout.WEST, this.labelTitle, 20, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelTitle, 20, SpringLayout.SOUTH, this.labelHeader);
	}
	
	private void createTextFieldTitle() {
		this.textFieldTitle = new JTextField();
		this.textFieldTitle.setPreferredSize(JournalAddPanel.TEXT_FIELD_DIM);
		
		this.layout.putConstraint(SpringLayout.WEST, this.textFieldTitle, 20, SpringLayout.EAST, this.labelTitle);
		this.layout.putConstraint(SpringLayout.NORTH, this.textFieldTitle, 20, SpringLayout.SOUTH, this.labelHeader);
	}
	
	private void createBodyTitle() {
		this.labelBody = new JLabel("Body", SwingConstants.RIGHT);
		this.labelBody.setFont(JournalAddPanel.LABEL_FONT);
		
		this.layout.putConstraint(SpringLayout.WEST, this.labelBody, 20, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelBody, 10, SpringLayout.SOUTH, this.labelTitle);
	}
	
	private void createTextAreaBody() {
		this.textAreaBody = new JTextArea(5, 30);
		this.textAreaBody.setLineWrap(true);
		this.textAreaBody.setWrapStyleWord(true);
		
		this.scrollPane = new JScrollPane(this.textAreaBody);
		this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.scrollPane.setPreferredSize(JournalAddPanel.TEXT_AREA_DIM);
		
		this.layout.putConstraint(SpringLayout.WEST, this.scrollPane, 20, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.scrollPane, 5, SpringLayout.SOUTH, this.labelBody);
	}
	
	private void createBtnSave() {
		this.btnSave = new JButton("Save");
		this.btnSave.setFont(JournalAddPanel.LABEL_FONT);
		this.btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonClick();
			}
		});
		
		this.layout.putConstraint(SpringLayout.EAST, this.btnSave, -30, SpringLayout.EAST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.btnSave, 20, SpringLayout.SOUTH, this.scrollPane);
	}
	
	private void saveButtonClick() {
		try {
			// Load data from database.
			this.journalEntriesController.load();
			this.journalEntriesController.add(this.addEntry());
			
			// Clear fields.
			this.textFieldTitle.setText("");
			this.textAreaBody.setText("");
			
			// Save to file.
			this.journalEntriesController.save();
			
			// Show pop-up message if saved successfully.
			JOptionPane.showMessageDialog(this.mainFrame, "Saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (EntryScheduleException e) {
			// Show pop-up message if user entered bad input.
			JOptionPane.showMessageDialog(this.mainFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private JournalEntry addEntry() {
		// Get title.
		String title = this.textFieldTitle.getText().trim();
		
		// Get Current date.
		LocalDate today = LocalDate.now();
		Date date = new Date(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
	
		// Get description.
		String description = this.textAreaBody.getText().trim();
		
		return new JournalEntry(title, date, description);
	}
	
	private void addComponentsToPanel() {
		this.add(this.labelHeader);
		this.add(this.labelTitle);
		this.add(this.textFieldTitle);
		this.add(this.labelBody);
		this.add(this.scrollPane);
		this.add(this.btnSave);
	}
	
	private void panelSettings() {
		this.setLayout(this.layout);
		this.setPreferredSize(JournalAddPanel.DIMENSIONS);
		this.setBackground(Color.WHITE);
	}
}
