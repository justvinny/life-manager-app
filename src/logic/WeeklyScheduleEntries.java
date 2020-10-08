package logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

import domain.Loadable;
import domain.Saveable;
import domain.WeeklyScheduleEntry;
import userexceptions.EntryScheduleException;

public class WeeklyScheduleEntries implements Loadable, Saveable {
	
	private HashMap<Integer, WeeklyScheduleEntry> weeklyEntries;
	private int entryNumber;
	
	public WeeklyScheduleEntries() {
		this.weeklyEntries = new HashMap<>();
		this.entryNumber = 1;
	}
	
	public void add(WeeklyScheduleEntry weeklyEntry) {
		if (this.weeklyEntries.containsValue(weeklyEntry)) {
			throw new EntryScheduleException("No duplicate entries allowed.");
		}
		
		this.weeklyEntries.put(this.entryNumber, weeklyEntry);
		this.entryNumber++;
	}
	
	public void remove(WeeklyScheduleEntry weeklyEntry) {
		int key = 0;
		
		if (this.weeklyEntries.containsValue(weeklyEntry)) {
			for(int i = 1; i <= this.weeklyEntries.size(); i++) {
				if (weeklyEntry == this.weeklyEntries.get(i)) {
					key = i;
					break;
				}
			}
			
			this.weeklyEntries.remove(key);
		}
	}
	
	public void remove(int entryNumber) {
		if (this.weeklyEntries.containsKey(entryNumber)) {
			this.weeklyEntries.remove(entryNumber);			
		}
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

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
}
