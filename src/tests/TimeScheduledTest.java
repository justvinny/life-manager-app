package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.Days;
import domain.TimeScheduled;
import userexceptions.EntryScheduleException;

class TimeScheduledTest {

	@Test 
	void throwEntryScheduleExceptionIfStartTimeIsNegative() {
		TimeScheduled test = new TimeScheduled(Days.MONDAY, 0, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setStartTime(-1));
	}
	
	@Test 
	void throwEntryScheduleExceptionIfStartTimeIsGreaterThanTwentyFour() {
		TimeScheduled test = new TimeScheduled(Days.MONDAY, 0, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setStartTime(24.5));
	}
	
	@Test 
	void throwEntryScheduleExceptionIfEndTimeIsNegative() {
		TimeScheduled test = new TimeScheduled(Days.MONDAY, 0, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setEndTime(-1));
	}
	
	@Test 
	void throwEntryScheduleExceptionIfEndTimeIsGreaterThanTwentyFour() {
		TimeScheduled test = new TimeScheduled(Days.MONDAY, 0, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setEndTime(24.5));
	}
	
	@Test 
	void throwEntryScheduleExceptionIfStartTimeIsEqualToEndTime() {
		Assertions.assertThrows(EntryScheduleException.class, () -> new TimeScheduled(Days.MONDAY, 1,1));
	}
	
	@Test 
	void throwEntryScheduleExceptionIfEndTimeIsLessThanStartTime() {
		Assertions.assertThrows(EntryScheduleException.class, () -> new TimeScheduled(Days.MONDAY, 2,1));
	}
	
	@Test
	void alternateConstructorDayMustBeMonday() {
		TimeScheduled test = new TimeScheduled("MONDAY-2-4");
		Assertions.assertEquals(Days.MONDAY, test.getDay());
	}
	
	@Test
	void alternateConstructorStartTimeMustBeFourPointFive() {
		TimeScheduled test = new TimeScheduled("MONDAY-4.5-6");
		Assertions.assertEquals(4.5, test.getStartTime());
	}
	
	@Test
	void alternateConstructorEndTimeMustBeTwelve() {
		TimeScheduled test = new TimeScheduled("MONDAY-2-12");
		Assertions.assertEquals(12, test.getEndTime());
	}
}
