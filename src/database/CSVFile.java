package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFile {
	
	// Constants for CSV delimiters for easy reuse.
	public static final String SAVE_DELIMITER = "-||==||-";
	public static final String LOAD_DELIMITER = "-\\|\\|==\\|\\|-";
	
	public static ArrayList<String> load(String filePath) {
		ArrayList<String> entryList = new ArrayList<>();
		File path = new File(filePath);
		String row;
		
		if (!path.isFile()) {
			return entryList;
		}
		
		try {
			BufferedReader readerCSV = new BufferedReader(new FileReader(path));
			
			while((row = readerCSV.readLine()) != null) {
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

	public static void save(String filePath, ArrayList<String> entryList) {
		File path = new File(filePath);
		FileWriter fileCSV = null;
		
		try {
			fileCSV = new FileWriter(path);
		
			
			for (String entry  : entryList) {
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