package domain;

/**
 * Interface that guarantees that all classes that need to write to persistent
 * file storage contain a method that can do so.
 * 
 * @author Vinson Beduya 19089783
 */
public interface Saveable {
	public void save();
}
