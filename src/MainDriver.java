import logic.JournalEntriesLogic;
import logic.WeeklyScheduleEntriesLogic;
import userinterface.MainFrame;

/**
 * Class that contains the main entry point of the program.
 * 
 * @author Vinson Beduya 19089783
 */
public class MainDriver {

	/**
	 * Main entry point of the program that initialises where the logic and the user
	 * interface interact with each other.
	 * 
	 * @param args command line argument/s. I will not be using this for my program.
	 * @author 19089783
	 */
	public static void main(String[] args) {
		// Let controllers interact with user interface.
		JournalEntriesLogic journalEntriesLogic = new JournalEntriesLogic();
		WeeklyScheduleEntriesLogic weeklyEntriesLogic = new WeeklyScheduleEntriesLogic();
		MainFrame mainFrame = new MainFrame("Life Manager", journalEntriesLogic, weeklyEntriesLogic);

		// Set the mainFrame to visible.
		mainFrame.setVisible(true);
	}
}
