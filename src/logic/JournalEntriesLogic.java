package logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

import database.CSVFile;
import domain.JournalEntry;
import domain.Loadable;
import domain.Saveable;
import userexceptions.EntryScheduleException;

/**
 * Class that will be main model used in our Journal Entry portion of the program.
 * This will class handles the adding, removing, and sorting of Journal Entries.
 * 
 * This also implements the Loadable and Saveable interfaces to make sure that
 * this class will have methods for reading and writing CSV files.
 * 
 * @author Vinson Beduya 19089783
 */
public class JournalEntriesLogic implements Loadable, Saveable {
	// File name of the CSV file that will be used four journal entries.
	private final String FILE_NAME = "journal-entries.csv";
	private HashMap<String, JournalEntry> journalEntries;
	
	/**
	 * Constructor for JournalEntriesLogic that initialises an empty hash map.
	 * 
	 * @author 19089783
	 */
	public JournalEntriesLogic() {
		this.journalEntries = new HashMap<>();
	}
	
	/**
	 * @return the HashMap instance being used that contains journal entries.
	 * @author 19089783
	 */
	public HashMap<String, JournalEntry> getJournalEntries() {
		return journalEntries;
	}
	
	/**
	 * Adds an entry to the HashMap. Throws an exception if there are duplicate entries
	 * or if the key entered is blank.
	 * 
	 * @param journalEntry
	 * @author 19089783
	 */
	public void add(JournalEntry journalEntry) {
		if (this.journalEntries.containsValue(journalEntry)) {
			throw new EntryScheduleException("No duplicate entries allowed.");
		}
		
		if (this.journalEntries.containsKey(journalEntry.getTitle())) {
			throw new EntryScheduleException("No duplicate keys allowed.");
		}
		
		this.journalEntries.put(journalEntry.getTitle(), journalEntry);
	}
	
	/**
	 * Retrieves a JournalEntry object using a hash map key which is the title.
	 * 
	 * @param title to get the journal entry.
	 * @return
	 * @author 19089783
	 */
	public JournalEntry get(String title) {
		return journalEntries.get(title);
	}
	
	/**
	 * Retrieves by value using object comparison.
	 * 
	 * @param journalEntry
	 * @return
	 * @author 19089783
	 */
	public JournalEntry get(JournalEntry journalEntry) {
		for (JournalEntry entry : journalEntries.values()) {
			if (journalEntry.equals(entry)) {
				return entry;
			}
		}
		
		return null;
	}
	
	/**
	 * Removes an entry using value comparison.
	 * 
	 * @param journalEntry
	 * @author 19089783
	 */
	public void remove(JournalEntry journalEntry) {
		if (journalEntries.values().contains(journalEntry)) {
			this.journalEntries.remove(journalEntry.getTitle());
		}
	}
	
	/**
	 * Removes an entry using the title key.
	 * 
	 * @param key
	 * @author 19089783
	 */
	public void remove(String title) {
		this.journalEntries.remove(title);
	}
	
	/**
	 * Sorts by the default comparable which sorts by date entered.
	 * 
	 * @return an ArrayList of Journal Entries.
	 * @author 19089783
	 */
	public ArrayList<JournalEntry> sort() {
		return this.journalEntries.values()
								  .stream()
								  .sorted()
							   	  .collect(Collectors.toCollection(ArrayList::new));
	} 
	
	/**
	 * Sorts by date entered in reversed order.
	 * 
	 * @return an ArrayList of Journal Entries.
	 * @author 19089783
	 */
	public ArrayList<JournalEntry> reverseSort() {
		return this.journalEntries.values()
								  .stream()
								  .sorted(Comparator.reverseOrder())
								  .collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Sorts by title name. 
	 * 
	 * @return an ArrayList of Journal Entries.
	 * @author 19089783
	 */
	public ArrayList<JournalEntry> sortByTitle() {
		return this.journalEntries.values()
								  .stream()
								  .sorted(Comparator.comparing(JournalEntry::getTitle))
								  .collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Sorts by the description length.
	 * 
	 * @return an ArrayList of Journal Entries.
	 * @author 19089783
	 */
	public ArrayList<JournalEntry> sortByDescriptionLength() {
		Comparator<JournalEntry> compareByDescriptionLength = (entry1, entry2) 
				-> entry1.getDescription().length() - entry2.getDescription().length();
		
		return this.journalEntries.values()
				 				  .stream()
				 				  .sorted(compareByDescriptionLength)
				 				  .collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Method that saves an ArrayList of journal entries in string format to the CSV file.
	 * 
	 * @author 19089783
	 */
	@Override
	public void save() {
		// Converts the JournalEntries into String form.
		ArrayList<String>entriesToSave = 
				this.journalEntries.values()
								   .stream()
								   .map(JournalEntry::toString)
								   .collect(Collectors.toCollection(ArrayList::new));
		
		// Save to CSV
		CSVFile.save(FILE_NAME, entriesToSave);
	}

	/**
	 * Method that reads the data from a CSV file and convert it to a HashMap of 
	 * journal entries that can be used by our application.
	 * 
	 * @author 19089783
	 */
	@Override
	public void load() {
		// Loads the Journal Entries in string form from the CSV and convert 
		// to JournalEntry objects inside a HashMap.
		ArrayList<String> entriesFromFile = CSVFile.load(FILE_NAME);
		HashMap<String, JournalEntry> tempHashMap = new HashMap<>();
		
		// Adding JournalEntry objects to HashMap.
		for (String entry : entriesFromFile) {
			JournalEntry tempJournalEntry = JournalEntry.factoryLoadFromCSV(entry);
			tempHashMap.put(tempJournalEntry.getTitle(), tempJournalEntry);
		}
		
		// Sets the field journalEntries to contain the value of the loaded data.
		this.journalEntries = tempHashMap;
	}
}
