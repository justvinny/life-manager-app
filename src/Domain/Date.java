package domain;

import java.util.Objects;

import userexceptions.EntryScheduleException;

/**
 * Date class that provides a template to be used for dates in scheduling
 * entries. This class also implements the comparable interface to allow it to
 * be sorted by date.
 * 
 * @author Vinson Beduya 19089783
 */
public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;

	/**
	 * @param day   of the month
	 * @param month of the year
	 * @param year  that will be used for dates
	 * @throws EntryScheduleException if the arguments passed are invalid dates.
	 * @author 19089783
	 */
	public Date(int day, int month, int year) throws EntryScheduleException {
		this.setDay(day);
		this.setMonth(month);
		this.setYear(year);
	}

	/**
	 * Alternate constructor that parses a string format of a date and turn into a
	 * Date object.
	 * 
	 * @param dateString is the string form of the date pulled from the CSV file.
	 * @author 19089783
	 */
	public Date(String dateString) {
		this.convertToFields(dateString);
	}

	/**
	 * @return the value of the year instance variable.
	 * @author 19089783
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return the value of the month instance variable.
	 * @author 19089783
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @return the value of the day instance variable.
	 * @author 19089783
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Setter method for the year instance variable. A valid year is anything that
	 * is not 0 or negative.
	 * 
	 * @param year
	 * @author 19089783
	 */
	public void setYear(int year) {
		if (year <= 0) {
			throw new EntryScheduleException("Year must not be 0 or less than 0.");
		}

		this.year = year;
	}

	/**
	 * Setter method for the instance variable month. A valid value is between 1 -
	 * 12 only.
	 * 
	 * @param month
	 * @author 19089783
	 */
	public void setMonth(int month) {
		if (!(month > 0 && month <= 12)) {
			throw new EntryScheduleException("Month must be 1 to 12.");
		}

		this.month = month;
	}

	/**
	 * Setter method for the instance variable day. A valid value must be between 1
	 * to 31 only.
	 * 
	 * @param day
	 * @author 19089783
	 */
	public void setDay(int day) {
		if (!(day > 0 && day <= 31)) {
			throw new EntryScheduleException("Day must be 1 to 31.");
		}

		this.day = day;
	}

	/**
	 * To string method of the data in a DD-MM-YYYY format.
	 * 
	 * @author 19089783
	 */
	@Override
	public String toString() {
		return String.format("%d-%d-%d", day, month, year);
	}

	/**
	 * Compares dates in ascending order.
	 * 
	 * @author 19089783
	 */
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

	/**
	 * Private method called by the alternate constructor to convert a date in
	 * string format to a Date object.
	 * 
	 * @param dateString is the string version of the date pulled from a CSV file.
	 * @author 19089783
	 */
	private void convertToFields(String dateString) {
		// Handy method to make sure null pointer exception messages are easily
		// debugged.
		Objects.requireNonNull(dateString);

		// Splits the string into an array of 3 strings that can be parsed as a date.
		String[] fieldStrings = dateString.split("-");
		int day = Integer.valueOf(fieldStrings[0]);
		int month = Integer.valueOf(fieldStrings[1]);
		int year = Integer.valueOf(fieldStrings[2]);

		// Sets the parsed values to our fields.
		this.setDay(day);
		this.setMonth(month);
		this.setYear(year);
	}
}
