package domain;

import java.util.Objects;

import userexceptions.EntryScheduleException;

public class TimeScheduled implements Comparable<TimeScheduled> {
	private Days day;
	private double startTime;
	private double endTime;
	
	public TimeScheduled(Days day, double startTime, double endTime) throws EntryScheduleException {
		this.setDay(day);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.checkStartAndEndTime();
	}

	public TimeScheduled(String timeScheduledString) {
		this.convertToFields(timeScheduledString);
	}
	
	public Days getDay() {
		return day;
	}

	public double getStartTime() {
		return startTime;
	}
	
	public double getEndTime() {
		return endTime;
	}
	
	public void setDay(Days day) {
		this.day = day;
	}

	public void setStartTime(double startTime) {
		if (!(startTime >= 0 && startTime <= 24)) {
			throw new EntryScheduleException("Time must be between 0 and 24.");
		} 
	
		this.startTime = startTime;
	}

	public void setEndTime(double endTime) {
		if (!(endTime >= 0 && endTime <= 24)) {
			throw new EntryScheduleException("Time must be between 0 and 24.");
		} 
		
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return String.format("%s-%.2f-%.2f",
				day, startTime, endTime);
	}
	
	private void checkStartAndEndTime() {
		if (startTime == endTime) {
			throw new EntryScheduleException("Start and end time must not be the same.");
		}
		
		if (endTime < startTime) {
			throw new EntryScheduleException("End time must not be less than start time.");
		}
	}
	
	@Override
	public int compareTo(TimeScheduled o) {
		if (this.getDay() == o.getDay()) {
			return Double.compare(this.startTime, o.startTime);
		}
		
		return this.getDay().compareTo(o.getDay());
	}
	
	private void convertToFields(String timeScheduledString) {
		Objects.requireNonNull(timeScheduledString);
		String[] fieldStrings = timeScheduledString.split("-");
		Days day = Days.valueOf(fieldStrings[0]);
		double startTime = Double.valueOf(fieldStrings[1]);
		double endTime = Double.valueOf(fieldStrings[2]);
		
		this.setDay(day);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
}
