package UserExceptions;

import java.util.InputMismatchException;

public class EntryScheduleException extends InputMismatchException {
	public EntryScheduleException(String message) {
		super(message);
	}
	
	public EntryScheduleException() {
		super();
	}
}
