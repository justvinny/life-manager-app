package domain;

import java.util.Objects;

import database.CSVFile;
import userexceptions.EntryScheduleException;

public class JournalEntry extends Entry implements Comparable<JournalEntry> {
	public static final int DESCRIPTION_LIMIT = 3000;
	
	private String description;
	private String title;
	private Date date;
	
	public JournalEntry(String title, Date date, String description) throws EntryScheduleException {
		this.setTitle(title);
		this.setDate(date);
		this.setDescription(description);
	}
	
	public JournalEntry(String title, Date date) throws EntryScheduleException {
		this.setTitle(title);
		this.setDate(date);
		this.setDescription("blank");
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description.replaceAll("\\\n", CSVFile.NEWLINE_DELIMITER);
	}

	public Date getDate() {
		return date;
	}

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

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int compareTo(JournalEntry o) {
		return this.getDate().compareTo(o.getDate());
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

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
	
	@Override
	public String toString() {
		return String.format("%s%s%s%s%s",
				getTitle(), CSVFile.SAVE_DELIMITER, getDate(),
				CSVFile.SAVE_DELIMITER, getDescription());
	}
	
	public static JournalEntry factoryLoadFromCSV(String journalEntryString) {
		Objects.requireNonNull(journalEntryString);
		String[] fieldStrings = journalEntryString.split(CSVFile.LOAD_DELIMITER);
		String title = fieldStrings[0];
		Date date = new Date(fieldStrings[1]);
		String description = fieldStrings[2];
		
		return new JournalEntry(title, date, description);
	}
}
