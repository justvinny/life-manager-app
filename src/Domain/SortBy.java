package domain;

/**
 * Enumeration class that contains sorting options for the data viewed by the
 * user on the GUI. This includes options such as sorting by date entered,
 * reversed, title, description length, and the time scheduled.
 * 
 * @author Vinson Beduya 19089783
 */
public enum SortBy {
	DATE_ENTERED("date entered"),
	DATE_ENTERED_REVERSED("date entered reversed"),
	TITLE("title"),
	DESCRIPTION_LENGTH("description length"),
	TIME_SCHEDULED("time scheduled");

	private String sortByString;

	
	/**
	 * Private constructor used to initialise the string version of the enumeration.
	 * 
	 * @param sortByString string version of the enumeration.
	 * @author 19089783
	 */
	private SortBy(String sortByString) {
		this.sortByString = sortByString;
	}

	/**
	 * @return the string representation of the enumeration.
	 * @author 19089783
	 */
	public String getSortByString() {
		return sortByString;
	}
}
