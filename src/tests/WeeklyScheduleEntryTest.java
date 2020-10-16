package tests;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import database.CSVFile;
import domain.Date;
import domain.TimeScheduled;
import domain.WeeklyScheduleEntry;
import userexceptions.EntryScheduleException;

/**
 * Unit test for WeeklyScheduleEntry class.
 * 
 * @author Vinson Beduya 19089783
 */
class WeeklyScheduleEntryTest {

	/**
	 * Tests to see if an exception is thrown when the description exceeds the 3000 
	 * character limit as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void throwErrorIfDescriptionCharacterLengthMoreThan3000() {
		Date date = new Date("20-12-2020");
		TimeScheduled timeScheduled = new TimeScheduled("MONDAY-2.4-4");
		WeeklyScheduleEntry test = new WeeklyScheduleEntry("Title", date, timeScheduled, "None");
		String description65chars = String.join("", Collections.nCopies(65, "a"));
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setDescription(description65chars));
	}
	
	/**
	 * Tests to see if the alternate constructor really sets the title to "The Title" as 
	 * intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorTitleMustBeEqualToTheTitle() {
		String sample = "The Title" + CSVFile.SAVE_DELIMITER + "10-12-2020" 
				+ CSVFile.SAVE_DELIMITER + "TUESDAY-18-23.5" 
				+ CSVFile.SAVE_DELIMITER + "No Description";
		
		WeeklyScheduleEntry test = WeeklyScheduleEntry.factoryLoadFromCSV(sample);
		Assertions.assertEquals("The Title", test.getTitle());
	}
	
	/**
	 * Tests to see if the alternate constructor really does set the date to equal
	 * 10-12-2020 as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorDateMustBeEqualTo10122020() {
		String sample = "The Title" + CSVFile.SAVE_DELIMITER + "10-12-2020" 
				+ CSVFile.SAVE_DELIMITER + "TUESDAY-18-23.5" 
				+ CSVFile.SAVE_DELIMITER + "No Description";
		
		WeeklyScheduleEntry test = WeeklyScheduleEntry.factoryLoadFromCSV(sample);
		Assertions.assertEquals("10-12-2020", test.getDate().toString());
	}
	
	/**
	 * Tests to see if the alternate constructor really does set the TimeScheduled to
	 * TUESDAY-18-23.5 as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorTimeScheduledMustBeTuesdayEighteenTwentyFourPointFive() {
		String sample = "The Title" + CSVFile.SAVE_DELIMITER + "10-12-2020" 
				+ CSVFile.SAVE_DELIMITER + "TUESDAY-18-23.5" 
				+ CSVFile.SAVE_DELIMITER + "No Description";
		
		WeeklyScheduleEntry test = WeeklyScheduleEntry.factoryLoadFromCSV(sample);
		Assertions.assertEquals("TUESDAY-18.00-23.50", test.getTimeScheduled().toString());
	}
	
	/**
	 * Tests to see if the alternate constructor really does set the description to 
	 * "No Description" as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorDescriptionMustBeEqulToNoDescription() {
		String sample = "The Title" + CSVFile.SAVE_DELIMITER + "10-12-2020" 
				+ CSVFile.SAVE_DELIMITER + "TUESDAY-18-23.5" 
				+ CSVFile.SAVE_DELIMITER + "No Description";
		
		WeeklyScheduleEntry test = WeeklyScheduleEntry.factoryLoadFromCSV(sample);
		Assertions.assertEquals("No Description", test.getDescription());
	}
}
