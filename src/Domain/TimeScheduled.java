package Domain;

import UserExceptions.EntryScheduleException;

public class TimeScheduled {
	private String date;
	private double startTime;
	private double endTime;
	
	public TimeScheduled(String date, double startTime, double endTime) throws EntryScheduleException {
		this.setDate(date);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.checkStartAndEndTime();
	}

	public String getDate() {
		return date;
	}

	public double getStartTime() {
		return startTime;
	}
	
	public double getEndTime() {
		return endTime;
	}
	
	public void setDate(String date) {
		this.date = date;
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
	
	private void checkStartAndEndTime() {
		if (startTime == endTime) {
			throw new EntryScheduleException("Start and end time must not be the same.");
		}
	}
}
