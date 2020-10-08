package domain;

import userexceptions.EntryScheduleException;

public class WeeklyScheduleEntry extends JournalEntry {
	public static final int DESCRIPTION_LIMIT = 64;
	
	private TimeScheduled timeScheduled;
	
	public WeeklyScheduleEntry(String title, String description, Date date, TimeScheduled timeScheduled) {
		super(title, date);
		this.setDescription(description);
		this.setTimeScheduled(timeScheduled);
	}

	public TimeScheduled getTimeScheduled() {
		return timeScheduled;
	}
	
	@Override
	public void setDescription(String description) {
		if (this.description.length() > WeeklyScheduleEntry.DESCRIPTION_LIMIT) {
			throw new EntryScheduleException("Description length must be less than 64 characters.");
		}
		
		this.description = description;
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
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		WeeklyScheduleEntry other = (WeeklyScheduleEntry) obj;
		
		if (this.toString() == null) {
			if (other.toString() != null)
				return false;
		} else if (!this.toString().equals(other.toString()))
			return false;
				
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("Title: %s\nDate: %s\n%s\nDescription:%s",
				getTitle(), getDate(), getTimeScheduled(), getDescription());
	}
}
