package tests;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import database.CSVFile;
import domain.Date;
import domain.JournalEntry;
import userexceptions.EntryScheduleException;

/**
 * Unit test for JournalEntry class.
 * 
 * @author Vinson Beduya 19089783
 */
class JournalEntryTest {

	/**
	 * Tests to see if exception is thrown when the title is set to be greater than 64 
	 * characters.
	 * 
	 * @author 19089783
	 */
	@Test
	void throwTimeScheduleExceptionIfTitleGreaterThan64Characters() {
		JournalEntry test = new JournalEntry("", new Date(2020, 10, 5), "");
		String title = String.join("", Collections.nCopies(65, "a"));
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setTitle(title));
	}

	/**
	 * Tests to see that an exception is thrown if the description exceeds the 3000
	 * character limit.
	 * 
	 * @author 19089783
	 */
	@Test
	void throwTimeScheduleExceptionIfDescriptionGreaterThan3000Characters() {
		JournalEntry test = new JournalEntry("", new Date(2020, 10, 5), "");
		String description = String.join("", Collections.nCopies(3001, "a"));
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setDescription(description));
	}

	/**
	 * Tests to see if the factory method sets the title to "The Title" as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorTitleMustEqualTheTitle() {
		String sample = "The Title" + CSVFile.SAVE_DELIMITER + "10-12-2020" 
				+ CSVFile.SAVE_DELIMITER + "No Description";
		
		JournalEntry test = JournalEntry.factoryLoadFromCSV(sample);
		Assertions.assertEquals("The Title", test.getTitle());
	}
	
	/**
	 * Tests to see if the factory method sets the date to 10-12-2020 as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorDateMustEqualTenTwelveTWENTYTWENTY() {
		String sample = "The Title" + CSVFile.SAVE_DELIMITER + "10-12-2020" 
				+ CSVFile.SAVE_DELIMITER + "No Description";
		
		JournalEntry test = JournalEntry.factoryLoadFromCSV(sample);
		Assertions.assertEquals("10-12-2020", test.getDate().toString());
	}
	
	/**
	 * Tests to see if the factory method sets the description to "No Description" as 
	 * intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void alternateConstructorTitleMustEqualNoDescription() {
		String sample = "The Title" + CSVFile.SAVE_DELIMITER + "10-12-2020" 
				+ CSVFile.SAVE_DELIMITER + "No Description";
		
		JournalEntry test = JournalEntry.factoryLoadFromCSV(sample);
		Assertions.assertEquals("No Description", test.getDescription());
	}
}
