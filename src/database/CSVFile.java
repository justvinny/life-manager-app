package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Utility class that provides methods for reading and writing on a CSV file.
 * 
 * @author Vinson Beduya 19089783
 */
public class CSVFile {

	/**
	 * Custom CSV delimiter patterns that I made to avoid errors when reading and
	 * writing files.
	 */
	public static final String SAVE_DELIMITER = "-||==||-";
	public static final String LOAD_DELIMITER = "-\\|\\|==\\|\\|-";
	public static final String NEWLINE_DELIMITER = "==newline==";

	/**
	 * This method reads CSV files that contain the entries that are used for my
	 * journal and weekly schedule.
	 * 
	 * @param filePath provides the file path on where to read/write the file.
	 * @return an array list of strings that can be converted into Objects necessary
	 *         in my program.
	 * @author 19089783
	 */
	public static ArrayList<String> load(String filePath) {
		ArrayList<String> entryList = new ArrayList<>();
		File path = new File(filePath);
		
		if (!path.isFile()) {
			return entryList;
		}

		try {
			BufferedReader readerCSV = new BufferedReader(new FileReader(path));
			
			String row;
			while ((row = readerCSV.readLine()) != null) {
				entryList.add(row);
			}

			readerCSV.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}

		return entryList;
	}

	/**
	 * This method writes the data entered by the user to a CSV file so the data
	 * will be stored in a persistent storage that can be viewed by the user
	 * whenever they open the program.
	 * 
	 * @param filePath  provides the file path on where to read/write the file.
	 * @param entryList The string version of the objects with delimiters to be
	 *                  saved.
	 * @author 19089783
	 */
	public static void save(String filePath, ArrayList<String> entryList) {
		File path = new File(filePath);
		FileWriter fileCSV = null;

		try {
			fileCSV = new FileWriter(path);

			for (String entry : entryList) {
				fileCSV.append(entry);
				fileCSV.append("\n");
			}

			fileCSV.flush();
			fileCSV.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}