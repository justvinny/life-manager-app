import logic.JournalEntriesController;
import logic.WeeklyScheduleEntriesController;
import userinterface.MainFrame;

public class MainDriver {

	public static void main(String[] args) {
		// Let controllers interact with user interface.
		JournalEntriesController journalEntriesController = new JournalEntriesController();
		WeeklyScheduleEntriesController weeklyEntriesController = new WeeklyScheduleEntriesController();
		MainFrame mainFrame = new MainFrame("Life Manager", journalEntriesController, weeklyEntriesController);
	
		mainFrame.setVisible(true);
	}
}
