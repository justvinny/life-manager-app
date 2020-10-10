package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.Date;
import userexceptions.EntryScheduleException;

class DateTest {

	@Test 
	void throwEntryScheduleExceptionIfYearIsZero() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setYear(0));
	}
	
	@Test 
	void throwEntryScheduleExceptionIfYearIsNegative() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setYear(-1));
	}
	
	@Test 
	void throwEntryScheduleExceptionIfMonthLessThanOne() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setMonth(0));
	}
	
	@Test 
	void throwEntryScheduleExceptionIfMonthGreaterThanTwelve() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setMonth(13));
	}
	
	@Test 
	void throwEntryScheduleExceptionIfDayLessThanOne() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setDay(0));
	}
	
	@Test 
	void throwEntryScheduleExceptionIfDayGreaterThanThirtyOne() {
		Date test = new Date(1, 1, 1);
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setMonth(32));
	}
	
	@Test
	void yearMustBeEqualToFive() {
		Date test = new Date(5, 1, 1);
		Assertions.assertEquals(5, test.getYear());
	}
	
	@Test
	void monthMustBeEqualToTwelve() {
		Date test = new Date(1, 12, 1);
		Assertions.assertEquals(12, test.getMonth());
	}
	
	@Test
	void dayMustBeEqualToOne() {
		Date test = new Date(1, 1, 1);
		Assertions.assertEquals(1, test.getDay());
	}
	
	@Test
	void alternateConstructorDayMustBeEqualToTwenty() {
		Date test = new Date("20-1-2020");
		Assertions.assertEquals(20, test.getDay());
	}
	
	@Test
	void alternateConstructorMonthMustBeEqualToTen() {
		Date test = new Date("20-10-2020");
		Assertions.assertEquals(10, test.getMonth());
	}
	
	@Test
	void alternateConstructorYearMustBeEqualToTwentyTwenty() {
		Date test = new Date("20-1-2020");
		Assertions.assertEquals(2020, test.getYear());
	}
}
