import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

// Uses JavaFX to display a new window prompting the user to select a file to load. 

public class FileSelect extends Application {
	private File file;
	private Stage stage;
	private Desktop desktop = Desktop.getDesktop();
	private FileChooser fileChooser = new FileChooser();
	private ExtensionFilter filter = new ExtensionFilter("CSV File", "csv");
		
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
		
		if (file != null) {
			openFile(file);
		}
		
		FileImporter parser = new FileImporter(file);
		parser.parseFile();
	}
	
	//This function returns the file reference, after testing if a file was selected. Returns null otherwise. 
	public void openFile(File file) {
		try {
			desktop.open(file);
		} catch (IOException e) {
			
		}
	}
}
