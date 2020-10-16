package domain;

import java.util.Objects;

import database.CSVFile;
import userexceptions.EntryScheduleException;

/**
 * This class is a blueprint for what an entry for weekly schedule should compose of.
 * It should have a title, date, time scheduled, and a description.
 * 
 * @author Vinson Beduya 19089783
 */
public class WeeklyScheduleEntry extends JournalEntry {
	/**
	 * Description limit is changed to 64 as it should be brief in weekly entries.
	 */
	public static final int DESCRIPTION_LIMIT = 64;
	
	private TimeScheduled timeScheduled;
	
	/**
	 * Constructor for weekly journal entries.
	 * 
	 * @param title is the name of the entry
	 * @param date is when it was entered
	 * @param timeScheduled is when is the event happening
	 * @param description is what you are doing in that event
	 * @author 19089783
	 */
	public WeeklyScheduleEntry(String title, Date date, TimeScheduled timeScheduled, String description) {
		super(title, date);
		this.setTimeScheduled(timeScheduled);
		this.setDescription(description);
	}

	/**
	 * @return TimeScheduled object of when the entry is supposed to happen.
	 * @author 19089783
	 */
	public TimeScheduled getTimeScheduled() {
		return timeScheduled;
	}
	
	/**
	 * Overridden setter method for description as we need to impose a new character
	 * limit which is 64.
	 * 
	 * @param description is what entry is about.
	 * @author 19089783
	 */
	@Override
	public void setDescription(String description) {
		if (description.length() > WeeklyScheduleEntry.DESCRIPTION_LIMIT) {
			throw new EntryScheduleException("Description length must be less than 64 characters.");
		}
		
		super.setDescription(description);
	}
	
	/**
	 * Setter method for timeScheduled.
	 * 
	 * @param timeScheduled
	 * @author 19089783
	 */
	public void setTimeScheduled(TimeScheduled timeScheduled) {
		this.timeScheduled = timeScheduled;
	}

	/**
	 * compareTo method that compares
	 * 
	 * @author 19089783
	 */
	@Override
	public int compareTo(JournalEntry o) {
		WeeklyScheduleEntry object = (WeeklyScheduleEntry) o;
		
		if (this.getDate().equals(o.getDate())) {
			return this.timeScheduled.compareTo(object.getTimeScheduled());
		}
		
		return super.compareTo(o);
	}

	/**
	 * HashCode method used for object comparison.
	 * 
	 * @author 19089783
	 */
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	/**
	 * Compares objects by their toString method on whether or not they are the same.
	 * 
	 * @author 19089783
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;			
		}
		
		if (obj == null) {
			return false;			
		}
		
		if (getClass() != obj.getClass()) {
			return false;			
		}
		
		WeeklyScheduleEntry other = (WeeklyScheduleEntry) obj;
		
		if (this.toString() == null) {
			if (other.toString() != null) {
				return false;				
			}
		} else if (!this.toString().equals(other.toString())) {
			return false;			
		}
				
		return true;
	}
	
	/**
	 * String representation used for saving to CSV files.
	 * 
	 * @author 19089783
	 */
	@Override
	public String toString() {
		return String.format("%s%s%s%s%s%s%s",
				getTitle(), CSVFile.SAVE_DELIMITER, getDate(), CSVFile.SAVE_DELIMITER,
				getTimeScheduled(), CSVFile.SAVE_DELIMITER, getDescription());
	}
	
	/**
	 * Factory method that servers as an alternate way go generate a WeeklyScheduleEntry
	 * object that will be primarily used for converting CSV data to objects our program
	 * can use.
	 * 
	 * @param weeklyScheduleEntryString string from CSV file.
	 * @return a WeeklyScheduleEntry object.
	 * @author 19089783
	 */
	public static WeeklyScheduleEntry factoryLoadFromCSV(String weeklyScheduleEntryString) {
		// Sane method for null exception check
		Objects.requireNonNull(weeklyScheduleEntryString);
		
		// Splits to an array size of 4 that can be used to make WeeklyScheduleEntry objects.
		String[] fieldStrings = weeklyScheduleEntryString.split(CSVFile.LOAD_DELIMITER);
		String title = fieldStrings[0];
		Date date = new Date(fieldStrings[1]);
		TimeScheduled timeScheduled = new TimeScheduled(fieldStrings[2]);
		String description = fieldStrings[3];
		
		return new WeeklyScheduleEntry(title, date, timeScheduled, description);
	}
}
