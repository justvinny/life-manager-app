package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.Date;
import userexceptions.EntryScheduleException;

/**
 * Unit test for the Date class.
 * 
 * @author Vinson Beduya 19089783
 */
class DateTest {

	/**
	 * Checks if exception is thrown if year is set to 0.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfYearIsZero() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setYear(0));
	}
	
	/**
	 * Checks if exception is thrown if year is set to negative.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfYearIsNegative() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setYear(-1));
	}
	
	/**
	 * Checks if exception is throw if month is set to less than 1.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfMonthLessThanOne() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setMonth(0));
	}
	
	/**
	 * Check if exception is thrown when month is set to greater than 12.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfMonthGreaterThanTwelve() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setMonth(13));
	}
	
	/**
	 * Checks if exception is thrown when day is set to a negative value.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfDayLessThanOne() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setDay(0));
	}
	
	/**
	 * Checks if exception is thrown when day is set to a value greater than 31.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfDayGreaterThanThirtyOne() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setMonth(32));
	}
	
	/**
	 * Checks if year is successfully set to five.
	 * 
	 * @author 19089783
	 */
	@Test
	void yearMustBeEqualToFive() {
		Date test = new Date(5, 1, 1);
		Assertions.assertEquals(5, test.getYear());
	}
	
	/**
	 * Checks if month is successfully set to 12.
	 * 
	 * @author 19089783
	 */
	@Test
	void monthMustBeEqualToTwelve() {
		Date test = new Date(1, 12, 1);
		Assertions.assertEquals(12, test.getMonth());
	}
	
	/**
	 * Checks if day is successfully set to one.
	 * 
	 * @author 19089783
	 */
	@Test
	void dayMustBeEqualToOne() {
		Date test = new Date(1, 1, 1);
		Assertions.assertEquals(1, test.getDay());
	}
	
	/**
	 * Check if alternate constructor sets the day to 20 as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorDayMustBeEqualToTwenty() {
		Date test = new Date("20-1-2020");
		Assertions.assertEquals(20, test.getDay());
	}
	
	/**
	 * Checks to see if the alternate constructor sets the month equal to ten as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorMonthMustBeEqualToTen() {
		Date test = new Date("20-10-2020");
		Assertions.assertEquals(10, test.getMonth());
	}
	
	/**
	 * Checks to see if alternate constructor sets the year to 2020 as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorYearMustBeEqualToTwentyTwenty() {
		Date test = new Date("20-1-2020");
		Assertions.assertEquals(2020, test.getYear());
	}
}
