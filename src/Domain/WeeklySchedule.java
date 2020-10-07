package Domain;

import UserExceptions.EntryScheduleException;

public class WeeklySchedule extends Entry {
	public static final int DESCRIPTION_LIMIT = 64;
	
	private String title;
	private String description;
	private Days day;
	private TimeScheduled timeScheduled;
	
	public WeeklySchedule(String title, String description, Days day, TimeScheduled timeScheduled) {
		this.setTitle(title);
		this.setDescription(description);
		this.setDay(day);
		this.setTimeScheduled(timeScheduled);
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

	public TimeScheduled getTimeScheduled() {
		return timeScheduled;
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
	
	public void setTimeScheduled(TimeScheduled timeScheduled) {
		this.timeScheduled = timeScheduled;
	}
}
