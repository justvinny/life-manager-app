package tests;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.Date;
import domain.TimeScheduled;
import domain.WeeklyScheduleEntry;
import logic.WeeklyScheduleEntriesLogic;

/**
 * Unit test for WeeklyScheduleEntriesLogic class.
 * 
 * @author Vinson Beduya 19089783
 */
class WeeklyScheduleEntriesLogicTest {

	/**
	 * Tests to see if the add method works as intended and really does add the object
	 * to the hash map.
	 * 
	 * @author 19089783
	 */
	@Test
	void testIfJournalEntryObjectSuccessfullyAddedToHashMap() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry clone = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntriesLogic test = new WeeklyScheduleEntriesLogic();
		test.add(sample);
		
		// Test if array size 1.
		Assertions.assertEquals(1, test.getWeeklyEntries().size());
		
		// Test if value entered is equal to object.
		Assertions.assertEquals(sample, test.get(sample.getTitle()));
		
		// Test if value entered is equal to a clone of the object. 
		Assertions.assertEquals(sample, test.get(clone));
	}
	
	/**
	 * Tests to see if the remove method works as intended and really does remove the object
	 * from the hash map using either a key or a value.
	 * 
	 * @author 19089783
	 */
	@Test
	void testIfJournalEntryObjectSuccessfullyRemovedFromHashMap() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry clone = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntriesLogic test = new WeeklyScheduleEntriesLogic();
		
		// Test removal using key.
		test.add(sample);
		test.remove(sample.getTitle());
		Assertions.assertEquals(0, test.getWeeklyEntries().size());
		
		// Test removal using value.
		test.add(sample);
		test.remove(clone);
		Assertions.assertEquals(0, test.getWeeklyEntries().size());
	}
	
	/**
	 * Tests to see if the sort method really does sort by date as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void testSortByDate() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample2 = new WeeklyScheduleEntry("Title2", new Date("12-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample3 = new WeeklyScheduleEntry("Title3", new Date("14-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntriesLogic test = new WeeklyScheduleEntriesLogic();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<WeeklyScheduleEntry> sortedList = test.sort();
		Assertions.assertEquals(sample2, sortedList.get(0));
		Assertions.assertEquals(sample3, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}
	
	/**
	 * Tests to see if the reverseSort method really does sort by date in reversed order
	 * as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void testSortByDateReversed() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Title", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample2 = new WeeklyScheduleEntry("Title2", new Date("12-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample3 = new WeeklyScheduleEntry("Title3", new Date("14-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntriesLogic test = new WeeklyScheduleEntriesLogic();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<WeeklyScheduleEntry> sortedList = test.reverseSort();
		Assertions.assertEquals(sample, sortedList.get(0));
		Assertions.assertEquals(sample3, sortedList.get(1));
		Assertions.assertEquals(sample2, sortedList.get(2));
	}
	
	/**
	 * Tests to see if the sortByTitle method really does sorts by title in alphabetical
	 * order as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void testSortByTitle() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Z", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample2 = new WeeklyScheduleEntry("D", new Date("12-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntry sample3 = new WeeklyScheduleEntry("B", new Date("14-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description");
		WeeklyScheduleEntriesLogic test = new WeeklyScheduleEntriesLogic();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<WeeklyScheduleEntry> sortedList = test.sortByTitle();
		Assertions.assertEquals(sample3, sortedList.get(0));
		Assertions.assertEquals(sample2, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}
	
	/**
	 * Tests to see if the sortByDescriptionLength really does sort by description length
	 * in ascending order as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void testSortByDescriptionLength() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Z", new Date("10-12-2020"), new TimeScheduled("MONDAY-16-20"), "Description longestttttttttttttttt");
		WeeklyScheduleEntry sample2 = new WeeklyScheduleEntry("D", new Date("12-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description shortest");
		WeeklyScheduleEntry sample3 = new WeeklyScheduleEntry("B", new Date("14-8-2020"), new TimeScheduled("MONDAY-16-20"), "Description 2nd longest");
		WeeklyScheduleEntriesLogic test = new WeeklyScheduleEntriesLogic();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<WeeklyScheduleEntry> sortedList = test.sortByDescriptionLength();
		Assertions.assertEquals(sample2, sortedList.get(0));
		Assertions.assertEquals(sample3, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}
	
	/**
	 * Tests to see if the sortByTimeScheduled really does sorts by Time Scheduled in 
	 * ascending order as inteded.
	 * 
	 * @author 19089783
	 */
	@Test
	void testSortByTimeScheduled() {
		WeeklyScheduleEntry sample = new WeeklyScheduleEntry("Z", new Date("10-12-2020"), new TimeScheduled("WEDNESDAY-16-20"), "Description");
		WeeklyScheduleEntry sample2 = new WeeklyScheduleEntry("D", new Date("12-8-2020"), new TimeScheduled("TUESDAY-14-20"), "Description");
		WeeklyScheduleEntry sample3 = new WeeklyScheduleEntry("B", new Date("14-8-2020"), new TimeScheduled("MONDAY-12-13"), "Description");
		WeeklyScheduleEntriesLogic test = new WeeklyScheduleEntriesLogic();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<WeeklyScheduleEntry> sortedList = test.sortByDescriptionLength();
		Assertions.assertEquals(sample3, sortedList.get(0));
		Assertions.assertEquals(sample2, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}

}
