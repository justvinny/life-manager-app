package domain;

public enum SortBy {
	DATE_ENTERED("date entered"),
	DATE_ENTERED_REVERSED("date entered reversed"),
	TITLE("title"),
	DESCRIPTION_LENGTH("description length"),
	TIME_SCHEDULED("time scheduled");
	
	private String sortByString;
	
	private SortBy(String sortByString) {
		this.sortByString = sortByString;
	}
	
	public String getSortByString() {
		return sortByString;
	}
}
