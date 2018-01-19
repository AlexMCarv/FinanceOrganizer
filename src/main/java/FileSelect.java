import java.io.File;
import java.time.*;
import java.time.format.*;
import java.util.*;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

// Uses JavaFX to display a new window prompting the user to select a file to load.
// Create a class for parsing?
public class FileSelect extends Application {
	private File file;
	private Stage stage;
	private FileChooser fileChooser = new FileChooser();
	private ExtensionFilter filter = new ExtensionFilter("CSV File", "csv"); // implement file filter
		
	// Constructor methods
	public FileSelect () {
		this.stage = null;
	}

	public FileSelect (Stage stage) {
		this.stage = stage;
	}
	
	// Get methods
	public Stage getStageComponent() {
		return stage;
	}

	public void start(Stage stage) throws Exception {
		fileChooser.setTitle("Finance Organizer");
		file = fileChooser.showOpenDialog(stage);
		
		// Parsing the data
		FileImporter parser = new FileImporter(file);
		List<String[]> importedData = parser.parseFile(); 
		List<Transaction> transactionData = new ArrayList<Transaction>();
		
		// Creating transaction objects
		for (String[] line : importedData) 
		{
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
    		LocalDate date = LocalDate.parse(line[0], formatter);
    		double value = Double.parseDouble(line[1]);
    		String type = line[3];
    		String description = line[4];
    		Transaction transaction = new Transaction(date, value, type, description);
    		transactionData.add(transaction);
    	}
		
		// Printing result for DEBUG
		for (int i = 0; i < transactionData.size(); i++) {
			System.out.println(transactionData.get(i));
		}
	}
}
