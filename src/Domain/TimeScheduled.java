package domain;

import database.CSVFile;
import userexceptions.EntryScheduleException;

public class TimeScheduled {
	private Days day;
	private double startTime;
	private double endTime;
	
	public TimeScheduled(Days day, double startTime, double endTime) throws EntryScheduleException {
		this.setDay(day);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.checkStartAndEndTime();
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
		return String.format("%s%s%f%s%f",
				day, CSVFile.SAVE_DELIMITER, startTime, CSVFile.SAVE_DELIMITER, endTime);
	}
	
	private void checkStartAndEndTime() {
		if (startTime == endTime) {
			throw new EntryScheduleException("Start and end time must not be the same.");
		}
	}
}
