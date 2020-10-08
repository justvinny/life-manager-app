package logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

import domain.JournalEntry;
import domain.Loadable;
import domain.Saveable;
import userexceptions.EntryScheduleException;

public class JournalEntries implements Loadable, Saveable {
	
	private HashMap<Integer, JournalEntry> journalEntries;
	private int entryNumber;
	
	public JournalEntries() {
		this.journalEntries = new HashMap<>();
		this.entryNumber = 1;
	}
	
	public void add(JournalEntry journalEntry) {
		if (this.journalEntries.containsValue(journalEntry)) {
			throw new EntryScheduleException("No duplicate entries allowed.");
		}
		
		this.journalEntries.put(this.entryNumber, journalEntry);
		this.entryNumber++;
	}
	
	public void remove(JournalEntry journalEntry) {
		int key = 0;
		
		if (this.journalEntries.containsValue(journalEntry)) {
			for(int i = 1; i <= this.journalEntries.size(); i++) {
				if (journalEntry == this.journalEntries.get(i)) {
					key = i;
					break;
				}
			}
			
			this.journalEntries.remove(key);
		}
	}
	
	public void remove(int entryNumber) {
		if (this.journalEntries.containsKey(entryNumber)) {
			this.journalEntries.remove(entryNumber);			
		}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
}
