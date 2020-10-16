package userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import domain.SortBy;
import domain.WeeklyScheduleEntry;
import logic.WeeklyScheduleEntriesLogic;

/**
 * Class that contains the user interface for the Weekly Schedule Remove panel.
 * 
 * @author Vinson Beduya 19089783
 */
@SuppressWarnings("serial")
public class WeeklyScheduleRemovePanel extends JPanel {
	/**
	 * Dimension constants that will be used by various JComponents.
	 */
	public static final Dimension DIMENSIONS = new Dimension(900, 720);
	public static final Dimension SCROLL_PANEL_DIMS = new Dimension(883, 610);
	public static final Dimension DYNAMIC_PANEL_DIMS = new Dimension(865, 100);
	public static final Dimension HEADER_DIMENSIONS = new Dimension(900, 30);
	public static final Dimension LABEL_DIMENSIONS = new Dimension(100, 30);
	public static final Dimension FIELDS_ONE_DIM = new Dimension(750, 30);
	public static final Dimension FIELDS_TWO_DIM = new Dimension(180, 30);
	public static final Dimension DYNAMIC_COMP_DIM1 = new Dimension(865, 30);
	public static final Dimension DYNAMIC_COMP_DIM2 = new Dimension(200, 30);
	
	/**
	 * Font constants that will be used by various JComponents. 
	 */
	public static final Font DYNAMIC_FONT = new Font("Impact", Font.PLAIN, 14);
	public static final Font HEADER_FONT = new Font("Impact", Font.PLAIN, 32);
	public static final Font LABEL_FONT = new Font("Impact", Font.PLAIN, 16);
	
	/**
	 * Instance variables.
	 */
	private Box innerBox;
	private JPanel innerPanel, actionPanel;
	private JScrollPane innerScrollPane;
	private WeeklyScheduleEntriesLogic weeklyEntriesLogic;
	private HashMap<JPanel, WeeklyScheduleEntry> dynamicPanelsList;
	private HashMap<WeeklyScheduleEntry, JPanel> dynamicPanelsListAlternative;
	private ArrayList<JPanel> panelsToDelete;
	private ArrayList<WeeklyScheduleEntry> orderedDynamicPanelsList;
	private BoxLayout layout;
	private JLabel labelHeader;
	
	/**
	 * Constructor to initialise the Weekly Schedule Remove panel.
	 * 
	 * @param mainFrame is the instance of the main JFrame.
	 * @param weeklyEntriesLogic is instance of the weeklyEntriesLogic that contains the 
	 * 	logic to manipulate the data.
	 * @author 19089783
	 */
	public WeeklyScheduleRemovePanel(MainFrame mainFrame, WeeklyScheduleEntriesLogic weeklyEntriesLogic) {
		// Instance variable that contains the data to be used in viewing and removing panels.
		this.weeklyEntriesLogic = weeklyEntriesLogic;
		
		// Hash map that contains key value pair of panels to be dynamically added and removed.
		this.dynamicPanelsList = new HashMap<>();
		// Alternate hash map for dynamic panels that uses the value as a key instead.
		this.dynamicPanelsListAlternative = new HashMap<>();
		
		// Contains the panels to be deleted.
		this.panelsToDelete = new ArrayList<>();
		
		// Layout
		this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);

		// Initialise all the JComponents.
		this.createLabelHeader();
		this.createActionPanel();
		this.createScrollPanePanel();
		this.populatePanelsBasedOnData();
		
		// Add the components to be used in the inner panel.
		this.addComponentsToInnerPanel();
		
		// Add all the components needed for the panel.
		this.addComponentsToPanel();
		
		// Panel settings.
		this.panelSettings();
	}
	
	/**
	 * Creates the JLabel header and sets its settings.
	 * 
	 * @author 19089783
	 */
	private void createLabelHeader() {
		this.labelHeader = new JLabel("Weekly Schedule", SwingConstants.CENTER);
		this.labelHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.labelHeader.setFont(HEADER_FONT);
	}
	
	/**
	 * Creates the JPanel that will contain a select all check box, remove button, a JLabel
	 * for the JCombBox sort by and, a JComboBox to choose sorting algorithms.
	 * 
	 * @author 19089783
	 */
	private void createActionPanel() {
		SpringLayout springLayout = new SpringLayout();
		
		// Action Panel settings.
		this.actionPanel = new JPanel();
		this.actionPanel.setLayout(springLayout);
		this.actionPanel.setMinimumSize(WeeklyScheduleRemovePanel.HEADER_DIMENSIONS);
		this.actionPanel.setPreferredSize(WeeklyScheduleRemovePanel.HEADER_DIMENSIONS);
		this.actionPanel.setMaximumSize(WeeklyScheduleRemovePanel.HEADER_DIMENSIONS);
		this.actionPanel.setBackground(Color.LIGHT_GRAY);
		
		// JCheckBox to select all check boxes.
		JCheckBox selectAll = new JCheckBox("Select All");
		selectAll.setBackground(Color.LIGHT_GRAY);
		selectAll.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		
		// Change listener that checks all the check boxes if selected.
		selectAll.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {	
				selectAllSelected(selectAll);
			}
		});
		
		// Set position of check box in the spring layout.
		springLayout.putConstraint(SpringLayout.WEST, selectAll, 0, SpringLayout.WEST, this.actionPanel);
		
		// Remove selected button.
		JButton removeSelected = new JButton("Remove Selected");
		removeSelected.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		
		// Action listener to listen for remove button click.
		removeSelected.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addSelectedPanelsToDelete();
				deletePanelsFromList();
			}
		});
		
		// Set position of the remove button in the spring layout.
		springLayout.putConstraint(SpringLayout.WEST, removeSelected, 380, SpringLayout.WEST, this.actionPanel);
		
		// Sort by combo box.		
		String[] sortOptions = {"Choose...", "Time Scheduled", "Title", "Description Length", "Date Entered", "Date Entered Reversed"};
		JComboBox<String> sortBy = new JComboBox<String>(sortOptions);
		sortBy.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		
		// Add change listener to the combo box which sorts the data being shown on the 
		// screen depending on the sorting algorithm selected.
		sortBy.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				sortBySelectedOption(sortBy);
			}
		});
		
		// Set position of the combo box in the spring layout.
		springLayout.putConstraint(SpringLayout.EAST, sortBy, -20, SpringLayout.EAST, this.actionPanel);
		
		// Sort label.
		JLabel sortByLabel = new JLabel("Sort By");
		sortByLabel.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		
		// Set position of the sort by label in the spring layout.
		springLayout.putConstraint(SpringLayout.EAST, sortByLabel, -10, SpringLayout.WEST, sortBy);
		springLayout.putConstraint(SpringLayout.SOUTH, sortByLabel, -8, SpringLayout.SOUTH, this.actionPanel);
		
		// Add components to the dynamic action panel.
		this.actionPanel.add(selectAll);
		this.actionPanel.add(removeSelected);
		this.actionPanel.add(sortBy);
		this.actionPanel.add(sortByLabel);
	}
	
	/**
	 * This method will set all available check boxes being shown to selected when the
	 * JCheckBox selectAll is selected.
	 * 
	 * @param selectAll check box.
	 * @author 19089783
	 */
	private void selectAllSelected(JCheckBox selectAll) {
		// Iterate over all the dynamically added panels.
		for (JPanel panel : this.dynamicPanelsList.keySet()) {
			// Iterate over all the JComponents of each dynamically added panel.
			for (Component component : panel.getComponents()) {
				// Check if the component is instance of JCheckBox.
				if (component instanceof JCheckBox) {
					// Set enable if selectAll check box is selected.
					// Else reset the selections.
					if (selectAll.isSelected()) {
						((JCheckBox)component).setSelected(true);						
					} else {
						((JCheckBox)component).setSelected(false);
					}
				} 
			}
		}
	}
	
	/**
	 * Add the panels to be deleted into another array list to make it easier to perform
	 * the delete operation dynamically on the view.
	 * 
	 * @author 19089783
	 */
	private void addSelectedPanelsToDelete() {
		// Iterate over all the JPanels in the dynamic panel keySet.
		for (JPanel panel : this.dynamicPanelsList.keySet()) {
			// Iterate over all the components of each dynamic JPanel.
			for (Component component : panel.getComponents()) {
				// Check if the component is an instance of check box.
				if (component instanceof JCheckBox) {
					// Add the JPanel to the delete list if it is selected.
					if (((JCheckBox)component).isSelected()) {
						this.panelsToDelete.add(panel);
					}
				}
			}
		}
	}
	
	/**
	 * Dynamically delete the panels on the panelsToDelete list. This method also refreshes
	 * the view, delete the related entries in the database, and reset all the array lists
	 * to be ready for the next delete operation.
	 * 
	 * @author 19089783
	 */
	private void deletePanelsFromList() {
		for (JPanel panel : this.panelsToDelete) {
			this.innerPanel.remove(panel);
			this.weeklyEntriesLogic.remove(this.dynamicPanelsList.get(panel));
		}
		
		// Clear panelsToDelete list as the delete operation is finished.
		this.panelsToDelete.clear();
		
		// Save the remaining undeleted entries to database to reflect the changes made.
		this.weeklyEntriesLogic.save();
		
		// Clear the both versions of the dynamic panel list to get it ready to be 
		// re-populated.
		this.dynamicPanelsList.clear();
		this.dynamicPanelsListAlternative.clear();
		
		// Remove all the panels being shown on the dynamicPanel.
		this.innerPanel.removeAll();
		
		// Load the entries from the database which reflects the new changes.
		this.weeklyEntriesLogic.load();
		
		// Populate the dynamicPanel based on the loaded data.
		this.populatePanelsBasedOnData();
		
		// Add the components to the inner panel.
		this.addComponentsToInnerPanel();
		
		// Re-validate and redraw the panel.
		this.innerPanel.revalidate();
		this.innerPanel.repaint();
	}
	
	/**
	 * Sorts the panels depending on the selected sorting algorithm.
	 * 
	 * @param sortByOption the combo box instance that contains the sort options.
	 * @author 19089783
	 */
	private void sortBySelectedOption(JComboBox<String> sortByOption) {
		String selectedItem = sortByOption.getSelectedItem().toString().toLowerCase();
	
		for (SortBy sortBy : SortBy.values()) {
			if (sortBy.getSortByString().equals(selectedItem)) {
				removeAllPanels(sortBy);
			}
		}
	}
	
	/**
	 * Removes all the panels being shown in the inner panel and re-populate it with the 
	 * new sorted panel list depending on the sort option algorithm selected.
	 * 
	 * @param sortBy
	 * @author 19089783
	 */
	private void removeAllPanels(SortBy sortBy) {	
		// Remove all the panels.
		for (JPanel panel : this.dynamicPanelsList.keySet()) {
			this.innerPanel.remove(panel);
		}
	
		// Check to see which sorting algorithm is selected.
		switch(sortBy) {
		case DATE_ENTERED:
			this.orderedDynamicPanelsList = this.weeklyEntriesLogic.sort();
			break;
		case DATE_ENTERED_REVERSED:
			this.orderedDynamicPanelsList = this.weeklyEntriesLogic.reverseSort();
			break;
		case DESCRIPTION_LENGTH:
			this.orderedDynamicPanelsList = this.weeklyEntriesLogic.sortByDescriptionLength();
			break;
		case TIME_SCHEDULED:
			this.orderedDynamicPanelsList = this.weeklyEntriesLogic.sortByTimeScheduled();
			break;
		case TITLE:
			this.orderedDynamicPanelsList = this.weeklyEntriesLogic.sortByTitle();
		}
		
		// Add the new sorted order of the panels to the view.
		this.addComponentsToInnerPanel(this.orderedDynamicPanelsList);
		
		// Refresh the GUI panel.
		this.innerPanel.revalidate();
		this.innerPanel.repaint();
	}
	
	/**
	 * Creates the scrollable panel that contains all our weekly schedule entries.
	 * 
	 * @author 19089783
	 */
	private void createScrollPanePanel() {
		// Box for horizontal alignment
		this.innerBox = Box.createHorizontalBox();
		
		// Inner panel settings.
		this.innerPanel = new JPanel();
		this.innerPanel.setLayout(new BoxLayout(this.innerPanel, BoxLayout.Y_AXIS));
		this.innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.innerPanel.setBackground(Color.LIGHT_GRAY);
		
		// Scroll pane settings.
		this.innerScrollPane = new JScrollPane(this.innerPanel);
		this.innerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.innerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.innerScrollPane.setMinimumSize(WeeklyScheduleRemovePanel.SCROLL_PANEL_DIMS);
		this.innerScrollPane.setPreferredSize(WeeklyScheduleRemovePanel.SCROLL_PANEL_DIMS);
		this.innerScrollPane.setMaximumSize(WeeklyScheduleRemovePanel.SCROLL_PANEL_DIMS);
		
		// Add panel to box to align left in the main panel.
		this.innerBox.add(this.innerScrollPane);
		this.innerBox.add(Box.createHorizontalGlue());
	}
	
	
	/**
	 * Populate the dynamic panels created with data from the database.
	 * 
	 * @author 19089783
	 */
	private void populatePanelsBasedOnData() {
		this.weeklyEntriesLogic.load();
		
		for (WeeklyScheduleEntry entry : this.weeklyEntriesLogic.getWeeklyEntries().values()) {
			this.createPanelsDynamically(entry);
		}
	}
	
	/**
	 * Create the panels dynamically depending on the amount of data loaded from the 
	 * CSV file.
	 * 
	 * @param entry is WeeklyScheduleEntry from the database.
	 * @author 19089783
	 */
	private void createPanelsDynamically(WeeklyScheduleEntry entry) {
		// Layout for dynamic panel.
		SpringLayout springLayout = new SpringLayout();
		
		// Dynamic panel.
		JPanel panel = new JPanel();
		panel.setLayout(springLayout);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setMinimumSize(WeeklyScheduleRemovePanel.DYNAMIC_PANEL_DIMS);
		panel.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_PANEL_DIMS);
		panel.setMaximumSize(WeeklyScheduleRemovePanel.DYNAMIC_PANEL_DIMS);
		
		// Entry Check box 
		JCheckBox removeCheck = new JCheckBox("Remove");
		removeCheck.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM2);
		removeCheck.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, removeCheck, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, removeCheck, 0, SpringLayout.SOUTH, panel);
		
		// Entry title.
		JLabel title = new JLabel("Title");
		title.setText(String.format("Title: %s", entry.getTitle()));
		title.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM1);
		title.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.NORTH, title, 3, SpringLayout.NORTH, panel);
		
		// Entry description.
		JLabel description = new JLabel("Description");
		description.setText(String.format("Description: %s", entry.getDescription()));
		description.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM1);
		description.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, description, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.NORTH, description, 3, SpringLayout.SOUTH, title);
		
		// Entry day scheduled.
		JLabel day = new JLabel("Day");
		day.setText(String.format("Day: %s", entry.getTimeScheduled().getDay()));
		day.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM2);
		day.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, day, 20, SpringLayout.EAST, removeCheck);
		springLayout.putConstraint(SpringLayout.SOUTH, day, 0, SpringLayout.SOUTH, panel);
		
		// Entry start time scheduled.
		JLabel startTime = new JLabel("Start time");
		startTime.setText(String.format("Start Time: %s", entry.getTimeScheduled().getStartTime()));
		startTime.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM2);
		startTime.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, startTime, 20, SpringLayout.EAST, day);
		springLayout.putConstraint(SpringLayout.SOUTH, startTime, 0, SpringLayout.SOUTH, panel);
		
		// Entry end time scheduled.
		JLabel endTime = new JLabel("End time");
		endTime.setText(String.format("End Time: %s", entry.getTimeScheduled().getEndTime()));
		endTime.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM2);
		endTime.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, endTime, 20, SpringLayout.EAST, startTime);
		springLayout.putConstraint(SpringLayout.SOUTH, endTime, 0, SpringLayout.SOUTH, panel);
		
		// Add components to the dynamically created panel.
		panel.add(removeCheck);
		panel.add(day);
		panel.add(startTime);
		panel.add(endTime);
		panel.add(title);
		panel.add(description);
		
		// Add this dynamically created panel to both versions of the dynamic panel list.
		this.dynamicPanelsList.put(panel, entry);
		this.dynamicPanelsListAlternative.put(entry, panel);
	}
	
	
	/**
	 * Add all the dynamically created panels to the inner panel.
	 * 
	 * @author 19089783
	 */
	private void addComponentsToInnerPanel() {
		for (JPanel panel : this.dynamicPanelsList.keySet()) {
			this.innerPanel.add(panel);
		}
	}
	
	/**
	 * Overloaded method to add dynamically created panels based on sorted entries.
	 * 
	 * @param entries instance of the sorted array.
	 * @author 19089783
	 */
	private void addComponentsToInnerPanel(ArrayList<WeeklyScheduleEntry> entries) {
		for (WeeklyScheduleEntry entry : entries) {
			this.innerPanel.add(this.dynamicPanelsListAlternative.get(entry));
		}
	}
	
	/**
	 * Add all the JComponents to the main panel for this class.
	 * 
	 * @author 19089783
	 */
	private void addComponentsToPanel() {
		this.add(this.labelHeader);
		this.add(this.actionPanel);
		this.add(this.innerBox);
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
