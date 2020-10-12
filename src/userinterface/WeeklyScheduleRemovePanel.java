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
import logic.WeeklyScheduleEntriesController;

@SuppressWarnings("serial")
public class WeeklyScheduleRemovePanel extends JPanel {
	public static final Dimension DIMENSIONS = new Dimension(900, 720);
	public static final Dimension SCROLL_PANEL_DIMS = new Dimension(883, 610);
	public static final Dimension DYNAMIC_PANEL_DIMS = new Dimension(865, 100);
	public static final Dimension HEADER_DIMENSIONS = new Dimension(900, 30);
	public static final Dimension LABEL_DIMENSIONS = new Dimension(100, 30);
	public static final Dimension FIELDS_ONE_DIM = new Dimension(750, 30);
	public static final Dimension FIELDS_TWO_DIM = new Dimension(180, 30);
	public static final Dimension DYNAMIC_COMP_DIM1 = new Dimension(865, 30);
	public static final Dimension DYNAMIC_COMP_DIM2 = new Dimension(200, 30);
	public static final Font DYNAMIC_FONT = new Font("Impact", Font.PLAIN, 14);
	public static final Font HEADER_FONT = new Font("Impact", Font.PLAIN, 32);
	public static final Font LABEL_FONT = new Font("Impact", Font.PLAIN, 16);
	
	private Box innerBox;
	private JPanel innerPanel, actionPanel;
	private JScrollPane innerScrollPane;
	private WeeklyScheduleEntriesController weeklyEntriesController;
	private HashMap<JPanel, WeeklyScheduleEntry> dynamicPanelsList;
	private HashMap<WeeklyScheduleEntry, JPanel> dynamicPanelsListAlternative;
	private ArrayList<JPanel> panelsToDelete;
	private ArrayList<WeeklyScheduleEntry> orderedDynamicPanelsList;
	private BoxLayout layout;
	private JLabel labelHeader;
	
	public WeeklyScheduleRemovePanel(MainFrame root, WeeklyScheduleEntriesController weeklyEntriesController) {
		this.weeklyEntriesController = weeklyEntriesController;
		this.dynamicPanelsList = new HashMap<>();
		this.dynamicPanelsListAlternative = new HashMap<>();
		this.panelsToDelete = new ArrayList<>();
		
		// Layout
		this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);

		// Add header.
		this.createLabelHeader();

		// Add action panel.
		this.createActionPanel();
		
		// Components
		this.createScrollPanePanel();
		this.populatePanelsBasedOnData();
		
		// Add components to panels.
		this.addComponentsToInnerPanel();
		this.addComponentsToPanel();
		
		// Panel settings.
		this.panelSettings();
	}
	
	private void createLabelHeader() {
		this.labelHeader = new JLabel("Weekly Schedule", SwingConstants.CENTER);
		this.labelHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.labelHeader.setFont(HEADER_FONT);
	}
	
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
		selectAll.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {	
				selectAllSelected(selectAll);
			}
		});
		
		springLayout.putConstraint(SpringLayout.WEST, selectAll, 0, SpringLayout.WEST, this.actionPanel);
		
		// Remove selected button.
		JButton removeSelected = new JButton("Remove Selected");
		removeSelected.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		removeSelected.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addSelectedPanelsToDelete();
				deletePanelsFromList();
			}
		});
		
		springLayout.putConstraint(SpringLayout.WEST, removeSelected, 380, SpringLayout.WEST, this.actionPanel);
		
		// Sort by combobox.		
		String[] sortOptions = {"Time Scheduled", "Title", "Description Length", "Date Entered", "Date Entered Reversed"};
		JComboBox<String> sortBy = new JComboBox<String>(sortOptions);
		sortBy.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		sortBy.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				sortBySelectedOption(sortBy);
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, sortBy, -20, SpringLayout.EAST, this.actionPanel);
		
		// Sort label.
		JLabel sortByLabel = new JLabel("Sort By");
		sortByLabel.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.EAST, sortByLabel, -10, SpringLayout.WEST, sortBy);
		springLayout.putConstraint(SpringLayout.SOUTH, sortByLabel, -8, SpringLayout.SOUTH, this.actionPanel);
		
		// Add components.
		this.actionPanel.add(selectAll);
		this.actionPanel.add(removeSelected);
		this.actionPanel.add(sortBy);
		this.actionPanel.add(sortByLabel);
	}
	
	private void selectAllSelected(JCheckBox selectAll) {
		for (JPanel panel : this.dynamicPanelsList.keySet()) {
			for (Component component : panel.getComponents()) {
				if (component instanceof JCheckBox) {
					if (selectAll.isSelected()) {
						((JCheckBox)component).setSelected(true);						
					} else {
						((JCheckBox)component).setSelected(false);
					}
				} 
			}
		}
	}
	
	private void addSelectedPanelsToDelete() {
		for (JPanel panel : this.dynamicPanelsList.keySet()) {
			for (Component component : panel.getComponents()) {
				if (component instanceof JCheckBox) {
					if (((JCheckBox)component).isSelected()) {
						this.panelsToDelete.add(panel);
					}
				}
			}
		}
	}
	
	private void deletePanelsFromList() {
		for (JPanel panel : this.panelsToDelete) {
			this.innerPanel.remove(panel);
			this.weeklyEntriesController.remove(this.dynamicPanelsList.get(panel));
		}
		
		this.panelsToDelete.clear();
		this.weeklyEntriesController.save();
		this.dynamicPanelsList.clear();
		this.dynamicPanelsListAlternative.clear();
		this.innerPanel.removeAll();
		this.weeklyEntriesController.load();
		this.populatePanelsBasedOnData();
		this.addComponentsToInnerPanel();
		this.innerPanel.revalidate();
		this.innerPanel.repaint();
	}
	
	private void sortBySelectedOption(JComboBox<String> sortByOption) {
		String selectedItem = sortByOption.getSelectedItem().toString().toLowerCase();
		
		for (SortBy sortBy : SortBy.values()) {
			if (sortBy.getSortByString().equals(selectedItem)) {
				removeAllPanels(sortBy);
			}
		}
	}
	
	private void removeAllPanels(SortBy sortBy) {	
		for (JPanel panel : this.dynamicPanelsList.keySet()) {
			this.innerPanel.remove(panel);
		}
	
		switch(sortBy) {
		case DATE_ENTERED:
			this.orderedDynamicPanelsList = this.weeklyEntriesController.sort();
			break;
		case DATE_ENTERED_REVERSED:
			this.orderedDynamicPanelsList = this.weeklyEntriesController.reverseSort();
			break;
		case DESCRIPTION_LENGTH:
			this.orderedDynamicPanelsList = this.weeklyEntriesController.sortByDescriptionLength();
			break;
		case TIME_SCHEDULED:
			this.orderedDynamicPanelsList = this.weeklyEntriesController.sortByTimeScheduled();
			break;
		case TITLE:
			this.orderedDynamicPanelsList = this.weeklyEntriesController.sortByTitle();
		}
		
		this.addComponentsToInnerPanel(this.orderedDynamicPanelsList);
		
		// Refresh the GUI panel.
		this.innerPanel.revalidate();
		this.innerPanel.repaint();
	}
	
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
	
	
	private void populatePanelsBasedOnData() {
		this.weeklyEntriesController.load();
		
		for (WeeklyScheduleEntry entry : this.weeklyEntriesController.getWeeklyEntries().values()) {
			this.createPanelsDynamically(entry);
		}
	}
	
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
		
		// Components
		JCheckBox removeCheck = new JCheckBox("Remove");
		removeCheck.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM2);
		removeCheck.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, removeCheck, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, removeCheck, 0, SpringLayout.SOUTH, panel);
		
		JLabel title = new JLabel("Title");
		title.setText(String.format("Title: %s", entry.getTitle()));
		title.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM1);
		title.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.NORTH, title, 3, SpringLayout.NORTH, panel);
		
		JLabel description = new JLabel("Description");
		description.setText(String.format("Description: %s", entry.getDescription()));
		description.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM1);
		description.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, description, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.NORTH, description, 3, SpringLayout.SOUTH, title);
		
		JLabel day = new JLabel("Day");
		day.setText(String.format("Day: %s", entry.getTimeScheduled().getDay()));
		day.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM2);
		day.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, day, 20, SpringLayout.EAST, removeCheck);
		springLayout.putConstraint(SpringLayout.SOUTH, day, 0, SpringLayout.SOUTH, panel);
		
		JLabel startTime = new JLabel("Start time");
		startTime.setText(String.format("Start Time: %s", entry.getTimeScheduled().getStartTime()));
		startTime.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM2);
		startTime.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, startTime, 20, SpringLayout.EAST, day);
		springLayout.putConstraint(SpringLayout.SOUTH, startTime, 0, SpringLayout.SOUTH, panel);
		
		JLabel endTime = new JLabel("End time");
		endTime.setText(String.format("End Time: %s", entry.getTimeScheduled().getEndTime()));
		endTime.setPreferredSize(WeeklyScheduleRemovePanel.DYNAMIC_COMP_DIM2);
		endTime.setFont(WeeklyScheduleRemovePanel.DYNAMIC_FONT);
		springLayout.putConstraint(SpringLayout.WEST, endTime, 20, SpringLayout.EAST, startTime);
		springLayout.putConstraint(SpringLayout.SOUTH, endTime, 0, SpringLayout.SOUTH, panel);
		
		panel.add(removeCheck);
		panel.add(day);
		panel.add(startTime);
		panel.add(endTime);
		panel.add(title);
		panel.add(description);
		
		this.dynamicPanelsList.put(panel, entry);
		this.dynamicPanelsListAlternative.put(entry, panel);
	}
	
	
	private void addComponentsToInnerPanel() {
		for (JPanel panel : this.dynamicPanelsList.keySet()) {
			this.innerPanel.add(panel);
		}
	}
	
	private void addComponentsToInnerPanel(ArrayList<WeeklyScheduleEntry> entries) {
		for (WeeklyScheduleEntry entry : entries) {
			this.innerPanel.add(this.dynamicPanelsListAlternative.get(entry));
		}
	}
	
	private void addComponentsToPanel() {
		this.add(this.labelHeader);
		this.add(this.actionPanel);
		this.add(this.innerBox);
	}
	
	private void panelSettings() {
		this.setLayout(this.layout);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(JournalAddPanel.DIMENSIONS);
		this.setBackground(Color.LIGHT_GRAY);
	}
}
