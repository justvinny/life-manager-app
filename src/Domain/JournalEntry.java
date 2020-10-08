package domain;

import userexceptions.EntryScheduleException;

public class JournalEntry extends Entry implements Comparable<JournalEntry> {
	public static final int DESCRIPTION_LIMIT = 1000;
	
	protected String description;
	private String title;
	private Date date;
	
	public JournalEntry(String title, String description, Date date) {
		this.setTitle(title);
		this.setDescription(description);
		this.setDate(date);
	}
	
	public JournalEntry(String title, Date date) {
		this.setTitle(title);
		this.setDate(date);
		this.setDescription("");
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
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
		if (description.length() > JournalEntry.DESCRIPTION_LIMIT) {
			throw new EntryScheduleException("Description length must be less than 1000 characters.");
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
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		JournalEntry other = (JournalEntry) obj;
		
		if (this.toString() == null) {
			if (other.toString() != null)
				return false;
		} else if (!this.toString().equals(other.toString()))
			return false;
				
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("Title: %s\nDate: %s\nDescription:%s",
				getTitle(), getDate(), getDescription());
	}
}
