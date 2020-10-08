package domain;

public abstract class Entry {
	public static final int TITLE_LIMIT = 64;
	
	public abstract void setTitle(String title);
	public abstract void setDescription(String description);
	public abstract void setDate(Date date);
}
