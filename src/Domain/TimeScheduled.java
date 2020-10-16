package domain;

import java.util.Objects;

import userexceptions.EntryScheduleException;

/**
 * This class provides a blueprint for what may consist a time scheduled entry for a 
 * weekly schedule entry. It consists of a what day the event should happen, its start time
 * , and end time. 
 * 
 * It also implements the comparable interface to compare it by what it is scheduled and if
 * the day is the same, it compares it by start time. 
 * 
 * @author Vinson Beduya 19089783
 */
public class TimeScheduled implements Comparable<TimeScheduled> {
	private Days day;
	private double startTime;
	private double endTime;
	
	/**
	 * Constructor for time scheduled. This throws an exception when an invalid time is 
	 * entered such as conflicting time schedules.
	 * 
	 * @param day when the event will happen.
	 * @param startTime is the beginning time of the event.t
	 * @param endTime is the end time of the event.
	 * @throws EntryScheduleException exception thrown when invalid time is entered.
	 * @author 19089783
	 */
	public TimeScheduled(Days day, double startTime, double endTime) throws EntryScheduleException {
		this.setDay(day);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.checkStartAndEndTime();
	}

	/**
	 * Alternate constructor used for making Time Scheduled objects from the CSV file.
	 * 
	 * @param timeScheduledString
	 * @author 19089783
	 */
	public TimeScheduled(String timeScheduledString) {
		this.convertToFields(timeScheduledString);
	}
	
	/**
	 * @return the day when the event will happen.
	 * @author 19089783
	 */
	public Days getDay() {
		return day;
	}

	/**
	 * @return the start time of the event.
	 * @author 19089783
	 */
	public double getStartTime() {
		return startTime;
	}
	
	/**
	 * @return the end time of the event.
	 * @author 19089783
	 */
	public double getEndTime() {
		return endTime;
	}
	
	/**
	 * Setter method for day.
	 * 
	 * @param day to set.
	 * @author 19089783
	 */
	public void setDay(Days day) {
		this.day = day;
	}

	/**
	 * Setter method for startTime. This will thrown an exception if time is not between
	 * 0 and 24.
	 * 
	 * @param startTime to set.
	 * @author 19089783
	 */
	public void setStartTime(double startTime) {
		if (!(startTime >= 0 && startTime <= 24)) {
			throw new EntryScheduleException("Time must be between 0 and 24.");
		} 
	
		this.startTime = startTime;
	}

	/**
	 * Setter method for endTime. This will thrown an exception if time is not between
	 * 0 and 24.
	 * 
	 * @param endTime to set.
	 * @author 19089783
	 */
	public void setEndTime(double endTime) {
		if (!(endTime >= 0 && endTime <= 24)) {
			throw new EntryScheduleException("Time must be between 0 and 24.");
		} 
		
		this.endTime = endTime;
	}
	
	/**
	 * String representation of a time scheduled. Example: MONDAY-16.5-21.0.
	 * 
	 * @author 19089783
	 */
	@Override
	public String toString() {
		return String.format("%s-%.2f-%.2f",
				day, startTime, endTime);
	}
	
	/**
	 * Method to be called in the constructor to make sure there are no conflicts between
	 * the start time and the end time.
	 * 
	 * @author 19089783
	 */
	private void checkStartAndEndTime() {
		if (startTime == endTime) {
			throw new EntryScheduleException("Start and end time must not be the same.");
		}
		
		if (endTime < startTime) {
			throw new EntryScheduleException("End time must not be less than start time.");
		}
	}
	
	/**
	 * Implementation of compareTo method of Comparable that will compare it by Day and
	 * start time.
	 * 
	 * @author 19089783
	 */
	@Override
	public int compareTo(TimeScheduled o) {
		if (this.getDay() == o.getDay()) {
			return Double.compare(this.startTime, o.startTime);
		}
		
		return this.getDay().compareTo(o.getDay());
	}
	
	/**
	 * Method that converts a string pulled from a CSV file to a time scheduled object.
	 * 
	 * @param timeScheduledString string representation pulled from database.
	 * @author 19089783
	 */
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
