import java.io.File;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

// Uses JavaFX to display a new window prompting the user to select a file to load.
public class FileSelect {
	private File file;
	private FileChooser fileChooser = new FileChooser();
		
	public FileSelect (Stage stage) {
		fileChooser.setTitle("Select File");
		file = fileChooser.showOpenDialog(stage);
	}
	
	// Get methods
	public File getFile() {return file;}

}
