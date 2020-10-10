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

public class WeeklyScheduleEntriesController implements Loadable, Saveable {
	
	private final String FILE_NAME = "./resources/weekly-entries.csv";
	private HashMap<String, WeeklyScheduleEntry> weeklyEntries;
	
	public WeeklyScheduleEntriesController() throws EntryScheduleException {
		this.weeklyEntries = new HashMap<>();
	}
	
	public HashMap<String, WeeklyScheduleEntry> getWeeklyEntries() {
		return this.weeklyEntries;
	}
	
	public void add(WeeklyScheduleEntry weeklyEntry) {
		if (this.weeklyEntries.containsValue(weeklyEntry)) {
			throw new EntryScheduleException("No duplicate entries allowed.");
		}
		
		if (this.weeklyEntries.containsKey(weeklyEntry.getTitle())) {
			throw new EntryScheduleException("No duplicate keyes allowed.");
		}
		
		this.weeklyEntries.put(weeklyEntry.getTitle(), weeklyEntry);
	}
	
	public WeeklyScheduleEntry get(String key) {
		return this.weeklyEntries.get(key);
	}
	
	public WeeklyScheduleEntry get(WeeklyScheduleEntry weeklyEntry) {
		for (WeeklyScheduleEntry entry : this.weeklyEntries.values()) {
			if (weeklyEntry.equals(entry)) {
				return entry;
			}
		}
		
		return null;
	}
	
	public void remove(WeeklyScheduleEntry weeklyEntry) {
		if (this.weeklyEntries.values().contains(weeklyEntry)) {
			this.weeklyEntries.remove(weeklyEntry.getTitle());
		}
	}
	
	public void remove(String key) {
		this.weeklyEntries.remove(key);			
	}
	
	public ArrayList<WeeklyScheduleEntry> sort() {
		return this.weeklyEntries.values()
								 .stream()
								 .sorted()
								 .collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ArrayList<WeeklyScheduleEntry> reverseSort() {
		return this.weeklyEntries.values()
								 .stream()
								 .sorted(Comparator.reverseOrder())
								 .collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ArrayList<WeeklyScheduleEntry> sortByTitle() {
		return this.weeklyEntries.values()
								 .stream()
								 .sorted(Comparator.comparing(WeeklyScheduleEntry::getTitle))
								 .collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ArrayList<WeeklyScheduleEntry> sortByDescriptionLength() {
		Comparator<WeeklyScheduleEntry> compareByDescriptionLength = (entry1, entry2) 
				-> entry1.getDescription().length() - entry2.getDescription().length();
		
		return this.weeklyEntries.values()
				 				 .stream()
				 				 .sorted(compareByDescriptionLength)
				 				 .collect(Collectors.toCollection(ArrayList::new));
	}

	public ArrayList<WeeklyScheduleEntry> sortByTimeScheduled() {
		return this.weeklyEntries.values()
								 .stream()
								 .sorted(Comparator.comparing(WeeklyScheduleEntry::getTimeScheduled))
								 .collect(Collectors.toCollection(ArrayList::new));
	}
	
	@Override
	public void save() {
		ArrayList<String>entriesToSave = 
				this.weeklyEntries.values()
								  .stream()
								  .map(JournalEntry::toString)
								  .collect(Collectors.toCollection(ArrayList::new));
		
		CSVFile.save(FILE_NAME, entriesToSave);
	}

	@Override
	public void load() {
		ArrayList<String> entriesFromFile = CSVFile.load(FILE_NAME);
		HashMap<String, WeeklyScheduleEntry> tempHashMap = new HashMap<>();
		
		for (String entry : entriesFromFile) {
			WeeklyScheduleEntry tempWeeklyScheduleEntry = WeeklyScheduleEntry.factoryLoadFromCSV(entry);
			tempHashMap.put(tempWeeklyScheduleEntry.getTitle(), tempWeeklyScheduleEntry);
		}
		
		this.weeklyEntries = tempHashMap;
	}
}
