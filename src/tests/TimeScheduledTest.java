package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.Days;
import domain.TimeScheduled;
import userexceptions.EntryScheduleException;

/**
 * Unit test for TimeScheduled class.
 * 
 * @author Vinson Beduya 19089783
 */
class TimeScheduledTest {

	/**
	 * Tests to see if an exception is thrown when start time is set to negative as intended.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfStartTimeIsNegative() {
		TimeScheduled test = new TimeScheduled(Days.MONDAY, 0, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setStartTime(-1));
	}
	
	/**
	 * Tests to see if an exception is thrown when start time is set to greater than 24
	 * as intended.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfStartTimeIsGreaterThanTwentyFour() {
		TimeScheduled test = new TimeScheduled(Days.MONDAY, 0, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setStartTime(24.5));
	}
	
	/**
	 * Tests to see if an exception is thrown when end time is set to negative as intended.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfEndTimeIsNegative() {
		TimeScheduled test = new TimeScheduled(Days.MONDAY, 0, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setEndTime(-1));
	}
	
	/**
	 * Checks to see if an exception is thrown if end time is set to greater than twenty four
	 * as intended.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfEndTimeIsGreaterThanTwentyFour() {
		TimeScheduled test = new TimeScheduled(Days.MONDAY, 0, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setEndTime(24.5));
	}
	
	/**
	 * Checks to see if an exception is thrown when start time is equal to end time as 
	 * intended.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfStartTimeIsEqualToEndTime() {
		Assertions.assertThrows(EntryScheduleException.class, () -> new TimeScheduled(Days.MONDAY, 1,1));
	}
	
	/**
	 * Tests to see if an exception is thrown when end time is less than start time as
	 * intended.
	 * 
	 * @author 19089783
	 */
	@Test 
	void throwEntryScheduleExceptionIfEndTimeIsLessThanStartTime() {
		Assertions.assertThrows(EntryScheduleException.class, () -> new TimeScheduled(Days.MONDAY, 2,1));
	}
	
	/**
	 * Tests to see if the alternate constructor sets the day to Monday as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorDayMustBeMonday() {
		TimeScheduled test = new TimeScheduled("MONDAY-2-4");
		Assertions.assertEquals(Days.MONDAY, test.getDay());
	}
	
	/**
	 * Tests to see if the alternate constructor sets the start time to 4.5 as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorStartTimeMustBeFourPointFive() {
		TimeScheduled test = new TimeScheduled("MONDAY-4.5-6");
		Assertions.assertEquals(4.5, test.getStartTime());
	}
	
	/**
	 * Tests to see if the alternate constructor sets the end time to 12 as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorEndTimeMustBeTwelve() {
		TimeScheduled test = new TimeScheduled("MONDAY-2-12");
		Assertions.assertEquals(12, test.getEndTime());
	}
}
