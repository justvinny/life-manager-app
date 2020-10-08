package domain;

import userexceptions.EntryScheduleException;

public class Date implements Comparable<Date>{
	private int year;
	private int month;
	private int day;
	
	public Date(int year, int month, int day) throws EntryScheduleException {
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public void setYear(int year) {
		if (year <= 0) {
			throw new EntryScheduleException("Year must not be 0 or less than 0.");
		}
		
		this.year = year;
	}

	public void setMonth(int month) {
		if (!(month > 0 && month <= 12)) {
			throw new EntryScheduleException("Month must be 1 to 12.");
		}
		
		this.month = month;
	}

	public void setDay(int day) {
		if (!(day > 0 && day <= 31)) {
			throw new EntryScheduleException("Day must be 1 to 31.");
		}
		
		this.day = day;
	}
	
	@Override
	public String toString() {
		return String.format("%d-%d-%d", day, month, year);
	}

	@Override
	public int compareTo(Date o) {
		if (this.getYear() == o.getYear()) {
			if (this.getMonth() == o.getMonth()) {
				return this.getDay() - o.getDay();
			}
			
			return this.getMonth() - o.getMonth();
		}
		
		return this.getYear() - o.getYear();
	}
}
