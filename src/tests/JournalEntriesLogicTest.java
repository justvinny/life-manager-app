package tests;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.Date;
import domain.JournalEntry;
import logic.JournalEntriesLogic;

/**
 * Unit test for JournalEntriesLogic class.
 * 
 * @author Vinson Beduya 19089783
 */
class JournalEntriesLogicTest {

	/**
	 * Checks to see if a valid object is successfully added to the HashMap using the add
	 * method.
	 * 
	 * @author 19089783
	 */
	@Test
	void testIfJournalEntryObjectSuccessfullyAddedToHashMap() {
		JournalEntry sample = new JournalEntry("Title", new Date("10-12-2020"), "Description");
		JournalEntry clone = new JournalEntry("Title", new Date("10-12-2020"), "Description");
		JournalEntriesLogic test = new JournalEntriesLogic();
		test.add(sample);
		
		// Test if array size 1.
		Assertions.assertEquals(1, test.getJournalEntries().size());
		
		// Test if value entered is equal to object.
		Assertions.assertEquals(sample, test.get(sample.getTitle()));
		
		// Test if value entered is equal to a clone of the object. 
		Assertions.assertEquals(sample, test.get(clone));
	}
	
	/**
	 * Checks to see if a valid object is successfully removed from the HashMap using the 
	 * remove method.
	 * 
	 * @author 19089783
	 */
	@Test
	void testIfJournalEntryObjectSuccessfullyRemovedFromHashMap() {
		JournalEntry sample = new JournalEntry("Title", new Date("10-12-2020"), "Description");
		JournalEntry clone = new JournalEntry("Title", new Date("10-12-2020"), "Description");
		JournalEntriesLogic test = new JournalEntriesLogic();
		
		// Test removal using key.
		test.add(sample);
		test.remove(sample.getTitle());
		Assertions.assertEquals(0, test.getJournalEntries().size());
		
		// Test removal using value.
		test.add(sample);
		test.remove(clone);
		Assertions.assertEquals(0, test.getJournalEntries().size());
	}
	
	/**
	 * Tests if the sort method sorts by date as expected.
	 * 
	 * @author 19089783
	 */
	@Test
	void testSortByDate() {
		JournalEntry sample = new JournalEntry("Title", new Date("10-12-2020"), "Description");
		JournalEntry sample2 = new JournalEntry("Title2", new Date("12-8-2020"), "Description");
		JournalEntry sample3 = new JournalEntry("Title3", new Date("14-8-2020"), "Description");
		JournalEntriesLogic test = new JournalEntriesLogic();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<JournalEntry> sortedList = test.sort();
		Assertions.assertEquals(sample2, sortedList.get(0));
		Assertions.assertEquals(sample3, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}
	
	/**
	 * Tests if the reverseSort method reverses the entries by date as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void testSortByDateReversed() {
		JournalEntry sample = new JournalEntry("Title", new Date("10-12-2020"), "Description");
		JournalEntry sample2 = new JournalEntry("Title2", new Date("12-8-2020"), "Description");
		JournalEntry sample3 = new JournalEntry("Title3", new Date("14-8-2020"), "Description");
		JournalEntriesLogic test = new JournalEntriesLogic();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<JournalEntry> sortedList = test.reverseSort();
		Assertions.assertEquals(sample, sortedList.get(0));
		Assertions.assertEquals(sample3, sortedList.get(1));
		Assertions.assertEquals(sample2, sortedList.get(2));
	}
	
	/**
	 * Tests if the sortByTitle method sorts in alphabetical order as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void testSortByTitle() {
		JournalEntry sample = new JournalEntry("Z", new Date("10-12-2020"), "Description");
		JournalEntry sample2 = new JournalEntry("D", new Date("12-8-2020"), "Description");
		JournalEntry sample3 = new JournalEntry("B", new Date("14-8-2020"), "Description");
		JournalEntriesLogic test = new JournalEntriesLogic();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<JournalEntry> sortedList = test.sortByTitle();
		Assertions.assertEquals(sample3, sortedList.get(0));
		Assertions.assertEquals(sample2, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}
	
	/**
	 * Tests if the sortByDescriptionLength method sorts by description length as intended.
	 * 
	 * @author 19089783
	 */
	@Test
	void testSortByDescriptionLength() {
		JournalEntry sample = new JournalEntry("Title", new Date("10-12-2020"), "Description longesttttttttttttttt");
		JournalEntry sample2 = new JournalEntry("Title2", new Date("12-8-2020"), "Description shortest");
		JournalEntry sample3 = new JournalEntry("Title3", new Date("14-8-2020"), "Description 2nd longest");
		JournalEntriesLogic test = new JournalEntriesLogic();
		test.add(sample);
		test.add(sample2);
		test.add(sample3);
		
		ArrayList<JournalEntry> sortedList = test.sortByDescriptionLength();
		Assertions.assertEquals(sample2, sortedList.get(0));
		Assertions.assertEquals(sample3, sortedList.get(1));
		Assertions.assertEquals(sample, sortedList.get(2));
	}
}
