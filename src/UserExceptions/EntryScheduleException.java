package userexceptions;

import java.util.InputMismatchException;

/**
 * Special user exception class for better exception messages.
 * 
 * @author Vinson Beduya 19089783
 */
@SuppressWarnings("serial")
public class EntryScheduleException extends InputMismatchException {
	public EntryScheduleException(String message) {
		super(message);
	}
	
	/**
	 * Overload constructor. 
	 * 
	 * @author 19089783
	 */
	public EntryScheduleException() {
		super();
	}
}
