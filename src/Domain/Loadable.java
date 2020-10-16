package domain;

/**
 * Interface to guarantee that classes that need to read data from a persistent
 * file storage contain a method that can do so.
 * 
 * @author Vinson Beduya 19089783
 */
public interface Loadable {
	public void load();
}
