package logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

import database.CSVFile;
import domain.JournalEntry;
import domain.Loadable;
import domain.Saveable;
import domain.WeeklyScheduleEntry;
import userexceptions.EntryScheduleException;

/**
 * Class that will be main model used in our Weekly Schedule portion of the program.
 * This will class handles the adding, removing, and sorting of Weekly Schedule
 * Entries.
 * 
 * This also implements the Loadable and Saveable interfaces to make sure that
 * this class will have methods for reading and writing CSV files.
 * 
 * @author Vinson Beduya 19089783
 */
public class WeeklyScheduleEntriesLogic implements Loadable, Saveable {
	
	/**
	 * File name of the CSV file to read and write.
	 */
	private final String FILE_NAME = "weekly-entries.csv";
	private HashMap<String, WeeklyScheduleEntry> weeklyEntries;
	
	/**
	 * Constructor that initialises an empty hash map to be used by our program.
	 * 
	 * @author 19089783
	 */
	public WeeklyScheduleEntriesLogic() {
		this.weeklyEntries = new HashMap<>();
	}
	
	/**
	 * @return the hash map that contains the Weekly Entries. 
	 * @author 19089783
	 */
	public HashMap<String, WeeklyScheduleEntry> getWeeklyEntries() {
		return this.weeklyEntries;
	}
	
	/**
	 * Adds weekly schedule entries entered by the user. This also checks for time conflicts
	 * with existing entries, duplicates, and blank entries to make sure that we won't save
	 * bad entries to the database. All invalid inputs will throw an expected exception.
	 * 
	 * @param weeklyEntry
	 * @author 19089783
	 */
	public void add(WeeklyScheduleEntry weeklyEntry) {
		// Helper method to check for duplicates.
		this.checkDuplicates(weeklyEntry);
		
		// Helper method to check for time conflicts.
		this.checkTimeConflicts(weeklyEntry);
		
		// Adds to HashMap after the checks pass.
		this.weeklyEntries.put(weeklyEntry.getTitle(), weeklyEntry);
	}
	
	/**
	 * Helper method to check for duplicate keys and values.
	 * 
	 * @param weeklyEntry
	 * @author 19089783
	 */
	public void checkDuplicates(WeeklyScheduleEntry weeklyEntry) {
		if (this.weeklyEntries.containsValue(weeklyEntry)) {
			throw new EntryScheduleException("No duplicate entries allowed.");
		}
		
		if (this.weeklyEntries.containsKey(weeklyEntry.getTitle())) {
			throw new EntryScheduleException("No duplicate keys allowed.");
		}
	}
	
	/**
	 * Helper method to check for time conflicts.
	 * 
	 * @param weeklyEntry
	 * @author 19089783
	 */
	public void checkTimeConflicts(WeeklyScheduleEntry weeklyEntry) {
		// Evaluates to true if there is an identical TimeSchedule already in the HashMap.
		boolean matchTimeScheduled = 
				this.weeklyEntries.values()
								  .stream()
								  .map(WeeklyScheduleEntry::getTimeScheduled)
								  .anyMatch(scheduled -> scheduled.equals(weeklyEntry.getTimeScheduled()));
		
		// Throws an exception if matchTimeScheduled evaluates to true.
		if (matchTimeScheduled) {
			throw new EntryScheduleException("No duplicate time scheduled allowed.");
		}
		
		// Evaluates to true if there is no start time conflicts on the same day.
		boolean isStartTimeConflict =
				this.weeklyEntries.values()
				    .stream()
				    .map(WeeklyScheduleEntry::getTimeScheduled)
				    .filter(day -> day.getDay() == weeklyEntry.getTimeScheduled().getDay())
				    .anyMatch(scheduled -> weeklyEntry.getTimeScheduled().getStartTime() >= scheduled.getStartTime() 
	  							&& weeklyEntry.getTimeScheduled().getStartTime() < scheduled.getEndTime());
		
		// Throws an exception if there isStartTimeConflict evaluates to true.
		if (isStartTimeConflict) {
			throw new EntryScheduleException("Start time must not be between existing start and end time.");
		}
		
		// Evaluates to true if there is no end time conflicts on the same day.
		boolean isEndTimeConflict = 
				this.weeklyEntries.values()
				.stream()
				.map(WeeklyScheduleEntry::getTimeScheduled)
				.filter(day -> day.getDay() == weeklyEntry.getTimeScheduled().getDay())
				.anyMatch(scheduled -> weeklyEntry.getTimeScheduled().getEndTime() > scheduled.getStartTime() 
						&& weeklyEntry.getTimeScheduled().getEndTime() <= scheduled.getEndTime());
		
		// Throws an exception if there isEndTimeConflict evaluates to true.
		if (isEndTimeConflict) {
			throw new EntryScheduleException("End time must not be between existing start and end time.");
		}
	}
	
	/**
	 * Getter method for a value in the hash map.
	 * 
	 * @param title is the key to search
	 * @return WeekylScheduleEntry matching the key.
	 * @author 19089783
	 */
	public WeeklyScheduleEntry get(String title) {
		return this.weeklyEntries.get(title);
	}
	
	/**
	 * Alternate getter method that searches by value.
	 * 
	 * @param weeklyEntry is the value to search.
	 * @return WeeklySceduleEntry of the same value.
	 * @author 19089783
	 */
	public WeeklyScheduleEntry get(WeeklyScheduleEntry weeklyEntry) {
		for (WeeklyScheduleEntry entry : this.weeklyEntries.values()) {
			if (weeklyEntry.equals(entry)) {
				return entry;
			}
		}
		
		return null;
	}
	
	/**
	 * Removes an entry in the list using object comparison of the values.
	 * 
	 * @param weeklyEntry object to compare.
	 * @author 19089783
	 */
	public void remove(WeeklyScheduleEntry weeklyEntry) {
		if (this.weeklyEntries.values().contains(weeklyEntry)) {
			this.weeklyEntries.remove(weeklyEntry.getTitle());
		}
	}
	
	/**
	 * Removes the entry using a key.
	 * 
	 * @param title key to search.
	 * @author 19089783
	 */
	public void remove(String title) {
		this.weeklyEntries.remove(title);			
	}
	
	/**
	 * Sorts by date entered which is the default comparison for WeekylScheduleEntry.
	 * 
	 * @return sorted ArrayList of WeeklyScheduleEntry
	 * @author 19089783
	 */
	public ArrayList<WeeklyScheduleEntry> sort() {
		return this.weeklyEntries.values()
								 .stream()
								 .sorted()
								 .collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Sorts by time entered in reversed order.
	 * 
	 * @return sorted ArrayList of WeeklyScheduleEntry
	 * @author 19089783
	 */
	public ArrayList<WeeklyScheduleEntry> reverseSort() {
		return this.weeklyEntries.values()
								 .stream()
								 .sorted(Comparator.reverseOrder())
								 .collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Sorts by title.
	 * 
	 * @return sorted ArrayList of WeeklyScheduleEntry
	 * @author 19089783
	 */
	public ArrayList<WeeklyScheduleEntry> sortByTitle() {
		return this.weeklyEntries.values()
								 .stream()
								 .sorted(Comparator.comparing(WeeklyScheduleEntry::getTitle))
								 .collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Sorts by description length. 
	 * 
	 * @return sorted ArrayList of WeeklyScheduleEntry
	 * @author 19089783
	 */
	public ArrayList<WeeklyScheduleEntry> sortByDescriptionLength() {
		Comparator<WeeklyScheduleEntry> compareByDescriptionLength = (entry1, entry2) 
				-> entry1.getDescription().length() - entry2.getDescription().length();
		
		return this.weeklyEntries.values()
				 				 .stream()
				 				 .sorted(compareByDescriptionLength)
				 				 .collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Sorts by time scheduled.
	 * 
	 * @return sorted ArrayList of WeeklyScheduleEntry
	 * @author 19089783
	 */
	public ArrayList<WeeklyScheduleEntry> sortByTimeScheduled() {
		return this.weeklyEntries.values()
								 .stream()
								 .sorted(Comparator.comparing(WeeklyScheduleEntry::getTimeScheduled))
								 .collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Method that saves an ArrayList of weekly schedules in string format to the CSV file.
	 * 
	 * @author 19089783
	 */
	@Override
	public void save() {
		// Converts the WeeklyScheduleEntries to string form.
		ArrayList<String>entriesToSave = 
				this.weeklyEntries.values()
								  .stream()
								  .map(JournalEntry::toString)
								  .collect(Collectors.toCollection(ArrayList::new));
		
		// Save the string representation to the CSV file.
		CSVFile.save(FILE_NAME, entriesToSave);
	}

	/**
	 * Method that reads the data from a CSV file and convert it to a HashMap of 
	 * weekly schedules that can be used by our application.
	 * 
	 * @author 19089783
	 */
	@Override
	public void load() {
		// Loads the Weekly Schedules in string form from the CSV and convert 
		// to WeeklyScheduleEntry objects inside a HashMap.
		ArrayList<String> entriesFromFile = CSVFile.load(FILE_NAME);
		HashMap<String, WeeklyScheduleEntry> tempHashMap = new HashMap<>();
		
		// Adding WeeklyScheduleEntry to hash map.
		for (String entry : entriesFromFile) {
			WeeklyScheduleEntry tempWeeklyScheduleEntry = WeeklyScheduleEntry.factoryLoadFromCSV(entry);
			tempHashMap.put(tempWeeklyScheduleEntry.getTitle(), tempWeeklyScheduleEntry);
		}
		
		// Sets the field weeklyEntries to contain the value of the loaded data.
		this.weeklyEntries = tempHashMap;
	}
}
