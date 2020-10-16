package domain;

import java.util.Objects;

import database.CSVFile;
import userexceptions.EntryScheduleException;

/**
 * Class that is a blue print of what a Journal Entry should contain.
 * This also implements a comparable interface that sorts the values by date.
 * 
 * @author Vinson Beduya 19089783
 */
public class JournalEntry extends Entry implements Comparable<JournalEntry> {
	/**
	 * Maximum character limit for a journal entry's description.
	 */
	public static final int DESCRIPTION_LIMIT = 3000;
	
	private String description;
	private String title;
	private Date date;
	
	/**
	 * Constructor to set the title, date, and description of the entry.
	 * 
	 * @param title of the entry.
	 * @param date when it was entered
	 * @param description is the main body of the journal entry.
	 * @throws EntryScheduleException is thrown for bad user input like exceeding character limits.
	 * @author 19089783
	 */
	public JournalEntry(String title, Date date, String description) throws EntryScheduleException {
		this.setTitle(title);
		this.setDate(date);
		this.setDescription(description);
	}
	
	/**
	 * Alternate constructor used for the child class where they have a different
	 * description limit.
	 * 
	 * @param title of the entry
	 * @param date when it was entered
	 * @throws EntryScheduleException is thrown for bad user input like exceeding character limits.
	 * @author 19089783
	 */
	public JournalEntry(String title, Date date) throws EntryScheduleException {
		this.setTitle(title);
		this.setDate(date);
		this.setDescription("blank");
	}
	
	/**
	 * @return the title of the entry
	 * @author 19089783
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the description of the entry
	 * @author 19089783
	 */
	public String getDescription() {
		return description.replaceAll("\\\n", CSVFile.NEWLINE_DELIMITER);
	}

	/**
	 * @return the date when the entry was entered.
	 * @author 19089783
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Setter method for the entry. Throws an exception when title exceeds the character
	 * limit which is 64 or if the title is blank.
	 * 
	 * @author 19089783
	 */
	@Override
	public void setTitle(String title) {
		if (title.length() > Entry.TITLE_LIMIT) {
			throw new EntryScheduleException("Title length must be less than 64 characters.");
		}
		
		if (title.length() <= 0) {
			throw new EntryScheduleException("Must not be blank!");
		}
		
		this.title = title;
	}
	
	/**
	 * Setter method for the description of the entry. Throws an exception when the 
	 * character exceeds 3000 or if the description is blank.
	 * 
	 * @author 19089783
	 */
	@Override
	public void setDescription(String description) {
		if (description.length() > JournalEntry.DESCRIPTION_LIMIT) {
			throw new EntryScheduleException("Description length must be less than 3000 characters.");
		}
		
		if (description.length() <= 0) {
			throw new EntryScheduleException("Must not be blank!");
		}
		
		this.description = description;
	}

	/**
	 * Setter method for the date. 
	 * 
	 * @author 19089783
	 */
	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Calls the compareTo method of the data since we want it sorted by date.
	 * 
	 * @author 19089783
	 */
	@Override
	public int compareTo(JournalEntry o) {
		return this.getDate().compareTo(o.getDate());
	}

	/**
	 * Hash code used for object comparison.
	 * 
	 * @author 19089783
	 */
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	/**
	 * Overridden equals method to implement a custom object comparison.
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
		
		JournalEntry other = (JournalEntry) obj;
		
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
	 * String format of how we want a JournalEntry to look when getting saved to a CSV file.
	 * 
	 * @author 19089783
	 */
	@Override
	public String toString() {
		return String.format("%s%s%s%s%s",
				getTitle(), CSVFile.SAVE_DELIMITER, getDate(),
				CSVFile.SAVE_DELIMITER, getDescription());
	}
	
	/**
	 * Factory method to initialise a JournalEntry object based on the string representation
	 * from the pulled CSV file.
	 * 
	 * @param journalEntryString is a string representation of pulled from the CSV file.
	 * @return a JournalEntry object.
	 * @author 19089783
	 */
	public static JournalEntry factoryLoadFromCSV(String journalEntryString) {
		// Sane null pointer exception check.
		Objects.requireNonNull(journalEntryString);
		
		// Splits data into an array size of 3 that can be used to making JournalEntry 
		// objects.
		String[] fieldStrings = journalEntryString.split(CSVFile.LOAD_DELIMITER);
		String title = fieldStrings[0];
		Date date = new Date(fieldStrings[1]);
		String description = fieldStrings[2];
		
		return new JournalEntry(title, date, description);
	}
}
