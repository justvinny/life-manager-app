package tests;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.Date;
import domain.TimeScheduled;
import domain.WeeklyScheduleEntry;
import logic.WeeklyScheduleEntriesController;

class WeeklyScheduleEntriesControllerTest {

	@Test
	void testIfJournalEntryObjectSuccessfullyAddedToHashMap() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry clone = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntriesController test = new WeeklyScheduleEntriesController();
		test.add(sample);
		
		// Test if array size 1.
		Assertions.assertEquals(1, test.getWeeklyEntries().size());
		
		// Test if value entered is equal to object.
		Assertions.assertEquals(sample, test.get(sample.getTitle()));
		
		// Test if value entered is equal to a clone of the object. 
		Assertions.assertEquals(sample, test.get(clone));
	}
	
	@Test
	void testIfJournalEntryObjectSuccessfullyRemovedFromHashMap() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry clone = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntriesController test = new WeeklyScheduleEntriesController();
		
		// Test removal using key.
		test.add(sample);
		test.remove(sample.getTitle());
		Assertions.assertEquals(0, test.getWeeklyEntries().size());
		
		// Test removal using value.
		test.add(sample);
		test.remove(clone);
		Assertions.assertEquals(0, test.getWeeklyEntries().size());
	}
	
	@Test
	void testSortByDate() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample2 = new WeeklyScheduleEntry("Title2", new Date("12-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample3 = new WeeklyScheduleEntry("Title3", new Date("14-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntriesController test = new WeeklyScheduleEntriesController();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<WeeklyScheduleEntry> sortedList = test.sort();
		Assertions.assertEquals(sample2, sortedList.get(0));
		Assertions.assertEquals(sample3, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}
	
	@Test
	void testSortByDateReversed() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample2 = new WeeklyScheduleEntry("Title2", new Date("12-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample3 = new WeeklyScheduleEntry("Title3", new Date("14-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntriesController test = new WeeklyScheduleEntriesController();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<WeeklyScheduleEntry> sortedList = test.reverseSort();
		Assertions.assertEquals(sample, sortedList.get(0));
		Assertions.assertEquals(sample3, sortedList.get(1));
		Assertions.assertEquals(sample2, sortedList.get(2));
	}
	
	@Test
	void testSortByTitle() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Z", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample2 = new WeeklyScheduleEntry("D", new Date("12-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample3 = new WeeklyScheduleEntry("B", new Date("14-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntriesController test = new WeeklyScheduleEntriesController();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<WeeklyScheduleEntry> sortedList = test.sortByTitle();
		Assertions.assertEquals(sample3, sortedList.get(0));
		Assertions.assertEquals(sample2, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}
	
	@Test
	void testSortByDescriptionLength() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Z", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description longestttttttttttttttt");
		WeeklyScheduleEntry sample2 = new WeeklyScheduleEntry("D", new Date("12-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description shortest");
		WeeklyScheduleEntry sample3 = new WeeklyScheduleEntry("B", new Date("14-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description 2nd longest");
		WeeklyScheduleEntriesController test = new WeeklyScheduleEntriesController();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<WeeklyScheduleEntry> sortedList = test.sortByDescriptionLength();
		Assertions.assertEquals(sample2, sortedList.get(0));
		Assertions.assertEquals(sample3, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}
	
	@Test
	void testSortByTimeScheduled() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Z", new Date("10-12-2020"), new TimeScheduled("WEDNESDAY-16-20"), "Description");
		WeeklyScheduleEntry sample2 = new WeeklyScheduleEntry("D", new Date("12-8-2020"), new TimeScheduled("TUESDAY-14-20"), "Description");
		WeeklyScheduleEntry sample3 = new WeeklyScheduleEntry("B", new Date("14-8-2020"), new TimeScheduled("MONDAY-12-13"), "Description");
		WeeklyScheduleEntriesController test = new WeeklyScheduleEntriesController();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<WeeklyScheduleEntry> sortedList = test.sortByDescriptionLength();
		Assertions.assertEquals(sample3, sortedList.get(0));
		Assertions.assertEquals(sample2, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}

}
