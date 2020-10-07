package Domain;

import UserExceptions.EntryScheduleException;

public class JournalEntry extends Entry {
	public static final int DESCRIPTION_LIMIT = 1000;
	
	private String title;
	private String description;
	private Days day;
	
	public JournalEntry(String title, String description, Days day) {
		this.setTitle(title);
		this.setDescription(description);
		this.setDay(day);
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Days getDay() {
		return day;
	}

	@Override
	public void setTitle(String title) {
		if (title.length() > Entry.TITLE_LIMIT) {
			throw new EntryScheduleException("Title length must be less than 64 characters.");
		}
		
		this.title = title;
	}
	
	@Override
	public void setDescription(String description) {
		if (description.length() > WeeklySchedule.DESCRIPTION_LIMIT) {
			throw new EntryScheduleException("Description length must be less than 64 characters.");
		}
		
		this.description = description;
	}

	@Override
	public void setDay(Days day) {
		this.day = day;
	}
}
