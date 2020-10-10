package tests;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import database.CSVFile;
import domain.Date;
import domain.JournalEntry;
import userexceptions.EntryScheduleException;

class JournalEntryTest {

	@Test
	void throwTimeScheduleExceptionIfTitleGreaterThan64Characters() {
		JournalEntry test = new JournalEntry("", new Date(2020, 10, 5), "");
		String title = String.join("", Collections.nCopies(65, "a"));
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setTitle(title));
	}

	@Test
	void throwTimeScheduleExceptionIfDescriptionGreaterThan3000Characters() {
		JournalEntry test = new JournalEntry("", new Date(2020, 10, 5), "");
		String description = String.join("", Collections.nCopies(3001, "a"));
		Assertions.assertThrows(EntryScheduleException.class, () -> test.setDescription(description));
	}

	@Test
	void alternateConstructorTitleMustEqualTheTitle() {
		String sample = "The Title" + CSVFile.SAVE_DELIMITER + "10-12-2020" 
				+ CSVFile.SAVE_DELIMITER + "No Description";
		
		JournalEntry test = JournalEntry.factoryLoadFromCSV(sample);
		Assertions.assertEquals("The Title", test.getTitle());
	}
	
	@Test
	void alternateConstructorDateMustEqualTenTwelveTWENTYTWENTY() {
		String sample = "The Title" + CSVFile.SAVE_DELIMITER + "10-12-2020" 
				+ CSVFile.SAVE_DELIMITER + "No Description";
		
		JournalEntry test = JournalEntry.factoryLoadFromCSV(sample);
		Assertions.assertEquals("10-12-2020", test.getDate().toString());
	}
	
	@Test
	void alternateConstructorTitleMustEqualNoDescription() {
		String sample = "The Title" + CSVFile.SAVE_DELIMITER + "10-12-2020" 
				+ CSVFile.SAVE_DELIMITER + "No Description";
		
		JournalEntry test = JournalEntry.factoryLoadFromCSV(sample);
		Assertions.assertEquals("No Description", test.getDescription());
	}
}
