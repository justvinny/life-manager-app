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

public class JournalEntriesController implements Loadable, Saveable {
	private final String FILE_NAME = "./resources/journal-entries.csv";
	private HashMap<String, JournalEntry> journalEntries;
	
	public JournalEntriesController() throws EntryScheduleException {
		this.journalEntries = new HashMap<>();
	}
	
	public HashMap<String, JournalEntry> getJournalEntries() {
		return journalEntries;
	}
	
	public void add(JournalEntry journalEntry) {
		if (this.journalEntries.containsValue(journalEntry)) {
			throw new EntryScheduleException("No duplicate entries allowed.");
		}
		
		if (this.journalEntries.containsKey(journalEntry.getTitle())) {
			throw new EntryScheduleException("No duplicate keys allowed.");
		}
		
		this.journalEntries.put(journalEntry.getTitle(), journalEntry);
	}
	
	public JournalEntry get(String key) {
		return journalEntries.get(key);
	}
	
	public JournalEntry get(JournalEntry journalEntry) {
		for (JournalEntry entry : journalEntries.values()) {
			if (journalEntry.equals(entry)) {
				return entry;
			}
		}
		
		return null;
	}
	
	public void remove(JournalEntry journalEntry) {
		if (journalEntries.values().contains(journalEntry)) {
			this.journalEntries.remove(journalEntry.getTitle());
		}
	}
	
	public void remove(String key) {
		this.journalEntries.remove(key);
	}
	
	public ArrayList<JournalEntry> sort() {
		return this.journalEntries.values()
								  .stream()
								  .sorted()
							   	  .collect(Collectors.toCollection(ArrayList::new));
	} 
	
	public ArrayList<JournalEntry> reverseSort() {
		return this.journalEntries.values()
								  .stream()
								  .sorted(Comparator.reverseOrder())
								  .collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ArrayList<JournalEntry> sortByTitle() {
		return this.journalEntries.values()
								  .stream()
								  .sorted(Comparator.comparing(JournalEntry::getTitle))
								  .collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ArrayList<JournalEntry> sortByDescriptionLength() {
		Comparator<JournalEntry> compareByDescriptionLength = (entry1, entry2) 
				-> entry1.getDescription().length() - entry2.getDescription().length();
		
		return this.journalEntries.values()
				 				  .stream()
				 				  .sorted(compareByDescriptionLength)
				 				  .collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public void save() {
		ArrayList<String>entriesToSave = 
				this.journalEntries.values()
								   .stream()
								   .map(JournalEntry::toString)
								   .collect(Collectors.toCollection(ArrayList::new));
		
		CSVFile.save(FILE_NAME, entriesToSave);
	}

	@Override
	public void load() {
		ArrayList<String> entriesFromFile = CSVFile.load(FILE_NAME);
		HashMap<String, JournalEntry> tempHashMap = new HashMap<>();
		
		for (String entry : entriesFromFile) {
			JournalEntry tempJournalEntry = JournalEntry.factoryLoadFromCSV(entry);
			tempHashMap.put(tempJournalEntry.getTitle(), tempJournalEntry);
		}
		
		this.journalEntries = tempHashMap;
	}
}
