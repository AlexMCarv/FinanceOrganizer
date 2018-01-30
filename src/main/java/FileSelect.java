import java.io.File;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

// Uses JavaFX to display a new window prompting the user to select a file to load.
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
	public Stage getStageComponent() {return stage;}
	public File getFile() {return file;}
	

	public void start(Stage stage) throws Exception {
		fileChooser.setTitle("Select File");
		file = fileChooser.showOpenDialog(stage);
	}
}
