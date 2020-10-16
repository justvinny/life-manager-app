package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import database.CSVFile;
import domain.JournalEntry;
import logic.JournalEntriesLogic;

/**
 * Class that contains the JPanel user interface for removing journal entries from the 
 * database.
 * 
 * @author Vinson Beduya 19089783
 */
@SuppressWarnings("serial")
public class JournalRemovePanel extends JPanel {
	/**
	 * Dimension constants that will be used by various JComponents.
	 */
	public static final Dimension DIMENSIONS = new Dimension(900, 720);
	public static final Dimension HEADER_DIM = new Dimension(900, 30);
	public static final Dimension COMPONENT_DIM = new Dimension(100, 30);
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
	private JournalEntriesLogic journalEntriesLogic;
	private SpringLayout layout;
	private JLabel labelHeader, labelTitle, labelDate, labelPage;
	private JTextArea textAreaBody;
	private JScrollPane scrollPane;
	private JButton btnRemove, btnNext, btnBack;
	private int currentPage, maxPage;
	
	/**
	 * Constructor to initialise the JPanel that removes entries.
	 * 
	 * @param journalEntriesLogic is the data that will be used for our application.
	 * @author 19089783
	 */
	public JournalRemovePanel(JournalEntriesLogic journalEntriesLogic) {		
		// Instance of JournalEntriesLogic that will be used for viewing/removing data.
		this.journalEntriesLogic = journalEntriesLogic;
		this.journalEntriesLogic.load();
		
		// Layout
		this.layout = new SpringLayout();
		
		// Initialise components.
		this.createLabelHeader();
		this.createLabelTitle();
		this.createLabelDate();
		this.createTextAreaBody();
		this.createBtnBack();
		this.createBtnRemove();
		this.createLabelPage();
		this.createBtnNext();
		
		// Show data to UI.
		this.displayFirstEntry();
		
		// Add components to the panel.
		addComponentsToPanel();
		
		// Panel settings.
		this.panelSettings();
	}
	
	/**
	 * Create JLabel header and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelHeader() {
		this.labelHeader = new JLabel("Journal Entry", SwingConstants.CENTER);
		this.labelHeader.setFont(JournalAddPanel.HEADER_FONT);
		this.labelHeader.setPreferredSize(JournalAddPanel.HEADER_DIM);
		
		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.NORTH, this.labelHeader, 20, SpringLayout.NORTH, this);
	}
	
	/**
	 * Create JLabel title and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelTitle() {
		this.labelTitle = new JLabel("Title: ", SwingConstants.RIGHT);
		this.labelTitle.setFont(JournalAddPanel.LABEL_FONT);
		
		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelTitle, 20, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelTitle, 20, SpringLayout.SOUTH, this.labelHeader);
	}
	
	/**
	 * Create JLabel date and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelDate() {
		this.labelDate = new JLabel("Date: ");
		this.labelDate.setFont(JournalAddPanel.LABEL_FONT);
		
		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelDate, 20, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelDate, 10, SpringLayout.SOUTH, this.labelTitle);
	}
	
	/**
	 * Create JTextArea body and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createTextAreaBody() {
		this.textAreaBody = new JTextArea(5, 30);
		this.textAreaBody.setLineWrap(true);
		this.textAreaBody.setWrapStyleWord(true);
		this.textAreaBody.setEditable(false);
		
		// Create JScrollPane when text gets too long.
		this.scrollPane = new JScrollPane(this.textAreaBody);
		this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.scrollPane.setPreferredSize(JournalAddPanel.TEXT_AREA_DIM);
		
		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.scrollPane, 20, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.scrollPane, 5, SpringLayout.SOUTH, this.labelDate);
	}
	
	/**
	 * Create JButton back and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createBtnBack() {
		this.btnBack = new JButton("< Back");
		this.btnBack.setFont(JournalAddPanel.LABEL_FONT);
		this.btnBack.setPreferredSize(JournalRemovePanel.COMPONENT_DIM);
		
		// Add action listener to listen for back button click.
		this.btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backButtonClick();				
			}
		});
		
		// Set position to spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.btnBack, 240, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, this.btnBack, 20, SpringLayout.SOUTH, this.scrollPane);
	}
	
	/**
	 * Helper method for back button click that brings you the previous journal entry.
	 * @author 19089783
	 */
	private void backButtonClick() {
		if (!(this.currentPage <= 1)) {
			this.currentPage--;
			this.entryNavigation();
		}
	}
	
	/**
	 * Create JButton remove and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createBtnRemove() {
		this.btnRemove = new JButton("Remove");
		this.btnRemove.setFont(JournalAddPanel.LABEL_FONT);
		this.btnRemove.setPreferredSize(JournalRemovePanel.COMPONENT_DIM);
		
		// Add action listener to listen for remove button click.
		this.btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeButtonClick();
				
			}
		});
		
		// Set position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.btnRemove, 5, SpringLayout.EAST, this.btnBack);
		this.layout.putConstraint(SpringLayout.NORTH, this.btnRemove, 20, SpringLayout.SOUTH, this.scrollPane);
	}
	
	/**
	 * Deletes the current entry being show and navigates back to the first entry.
	 * 
	 * @author 19089783
	 */
	private void removeButtonClick() {
		ArrayList<JournalEntry> entries = this.journalEntriesLogic.sort();
		int index = this.currentPage - 1;
		
		// Remove the entry from database and refresh data.
		if (index >= 0 && entries.size() > 0) {
			this.journalEntriesLogic.remove(entries.get(index).getTitle());
			this.journalEntriesLogic.save();
			this.journalEntriesLogic.load();
		} 
		
		// Call again to get refresh array.
		entries = this.journalEntriesLogic.sort();
		
		// Default values if array size is 0.
		if (this.journalEntriesLogic.getJournalEntries().size() == 0) {
			this.setFieldsToBlank();
			this.maxPage = 0;
			this.displayTotalPages(entries, 0);
		// Go back to first page after deletion.
		} else {
			this.displayFirstEntry();
		}
		
	}
	
	/**
	 * Helper method to set all fields to blank.
	 * 
	 * @author 19089783
	 */
	private void setFieldsToBlank() {
		this.labelDate.setText("Date: ");
		this.labelTitle.setText("Title: ");
		this.textAreaBody.setText("");
	}
	
	/**
	 * Create the JLabel page that shows how many pages there are and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelPage() {
		this.labelPage = new JLabel("Page 0 of 0", SwingConstants.CENTER);
		this.labelPage.setFont(JournalAddPanel.LABEL_FONT);
		this.labelPage.setPreferredSize(JournalRemovePanel.COMPONENT_DIM);
		
		// Position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.labelPage, 5, SpringLayout.EAST, this.btnRemove);
		this.layout.putConstraint(SpringLayout.NORTH, this.labelPage, 20, SpringLayout.SOUTH, this.scrollPane);
	}
	
	/**
	 * Crate JButton next that is used in navigating to the next entry and set its settings.
	 * 
	 * @author 19089783
	 */
	private void createBtnNext() {
		this.btnNext = new JButton("Next >");
		this.btnNext.setFont(JournalAddPanel.LABEL_FONT);
		this.btnNext.setPreferredSize(JournalRemovePanel.COMPONENT_DIM);
		
		// Add action listener to listen for next button click.
		this.btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextButtonClick();				
			}
		});
		
		// Position in spring layout.
		this.layout.putConstraint(SpringLayout.WEST, this.btnNext, 5, SpringLayout.EAST, this.labelPage);
		this.layout.putConstraint(SpringLayout.NORTH, this.btnNext, 20, SpringLayout.SOUTH, this.scrollPane);
	}
		
	/**
	 * Navigates to the next page (entry).
	 * 
	 * @author 19089783
	 */
	private void nextButtonClick() {
		if (!(this.currentPage >= this.maxPage)) {
			this.currentPage++;
			this.entryNavigation();
		}
	}
	
	/**
	 * Displays the first entry to the view.
	 * 
	 * @author 19089783
	 */
	private void displayFirstEntry() {
		ArrayList<JournalEntry> entries = this.journalEntriesLogic.sort();
		
		if (entries.size() > 0) {
			this.currentPage = 1;
			this.entryNavigation();
		} else {
			this.currentPage = 0;
		}
	}
	
	/**
	 * Updates the view depending on which entry is selected.
	 * 
	 * @author 19089783
	 */
	private void entryNavigation() {
		ArrayList<JournalEntry> entries = this.journalEntriesLogic.sort();
		int index = this.currentPage - 1;
		this.maxPage = entries.size();
		
		if (entries.size() > 0) {
			String title = "Title: " + entries.get(index).getTitle();
			this.labelTitle.setText(title);
			
			String date = "Date: " + entries.get(index).getDate().toString();
			this.labelDate.setText(date);
			
			String description = entries.get(index).getDescription().replaceAll(CSVFile.NEWLINE_DELIMITER, "\n");
			this.textAreaBody.setText(description);
			
			this.displayTotalPages(entries, this.currentPage);
		}
	}
	
	/**
	 * Sets label to show which page it's currently on and how many pages there are.
	 * 
	 * @param entries array list of entries that needs to be displayed.
	 * @param current current page of the entry being displayed.
	 * @author 19089783
	 */
	private void displayTotalPages(ArrayList<JournalEntry> entries, int current) {
		this.labelPage.setText(String.format("Page %d of %d", current, entries.size()));
	}
	
	/**
	 * Add all the components to the panel.
	 * 
	 * @author 19089783
	 */
	private void addComponentsToPanel() {
		this.add(this.labelHeader);
		this.add(this.labelTitle);
		this.add(this.labelDate);
		this.add(this.scrollPane);
		this.add(this.btnBack);
		this.add(this.btnRemove);
		this.add(this.labelPage);
		this.add(this.btnNext);
	}
	
	/**
	 * Panel settings.
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
