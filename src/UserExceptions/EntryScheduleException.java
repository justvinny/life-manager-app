package userexceptions;

import java.util.InputMismatchException;

@SuppressWarnings("serial")
public class EntryScheduleException extends InputMismatchException {
	public EntryScheduleException(String message) {
		super(message);
	}
	
	public EntryScheduleException() {
		super();
	}
}
