package domain;

import database.CSVFile;
import userexceptions.EntryScheduleException;

public class WeeklyScheduleEntry extends JournalEntry {
	public static final int DESCRIPTION_LIMIT = 64;
	
	private TimeScheduled timeScheduled;
	
	public WeeklyScheduleEntry(String title, Date date, TimeScheduled timeScheduled, String description) {
		super(title, date);
		this.setTimeScheduled(timeScheduled);
		this.setDescription(description);
	}

	public TimeScheduled getTimeScheduled() {
		return timeScheduled;
	}
	
	@Override
	public void setDescription(String description) {
		if (description.length() > WeeklyScheduleEntry.DESCRIPTION_LIMIT) {
			throw new EntryScheduleException("Description length must be less than 64 characters.");
		}
		
		super.setDescription(description);
	}
	
	public void setTimeScheduled(TimeScheduled timeScheduled) {
		this.timeScheduled = timeScheduled;
	}

	@Override
	public int compareTo(JournalEntry o) {
		WeeklyScheduleEntry object = (WeeklyScheduleEntry) o;
		
		if (this.getDate().equals(o.getDate())) {
			return Double.compare(this.getTimeScheduled().getStartTime(), object.getTimeScheduled().getStartTime());
		}
		
		return super.compareTo(o);
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
	
	@Override
	public String toString() {
		return String.format("%s%s%s%s%s%s%s",
				getTitle(), CSVFile.SAVE_DELIMITER, getDate(), CSVFile.SAVE_DELIMITER,
				getTimeScheduled(), CSVFile.SAVE_DELIMITER, getDescription());
	}
	
	public static WeeklyScheduleEntry factoryLoadFromCSV(String weeklyScheduleEntryString) {
		String[] fieldStrings = weeklyScheduleEntryString.split(CSVFile.LOAD_DELIMITER);
		String title = fieldStrings[0];
		Date date = new Date(fieldStrings[1]);
		TimeScheduled timeScheduled = new TimeScheduled(fieldStrings[2]);
		String description = fieldStrings[3];
		
		return new WeeklyScheduleEntry(title, date, timeScheduled, description);
	}
}
