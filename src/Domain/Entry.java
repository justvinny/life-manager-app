package domain;

/**
 * Abstract class for entries to be used as a blueprint for my Journal Entries
 * and Weekly Schedule Entries in the application.
 * 
 * @author Vinson Beduya 19089783
 */
public abstract class Entry {

	/**
	 * All titles for entries must be less than 64 characters. 
	 */
	public static final int TITLE_LIMIT = 64;


	/**
	 * Setter methods needed by classes that are entries.
	 * @param title
	 * @author 19089783
	 */
	public abstract void setTitle(String title);
	public abstract void setDescription(String description);
	public abstract void setDate(Date date);
}
