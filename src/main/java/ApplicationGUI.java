import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ApplicationGUI extends HBox{
		
	Button openButton = new Button("Import CSV File");
	Button saveButton = new Button("Save to CSV File");
	TransactionHandler tHandler;
	
	public ApplicationGUI(Stage stage) {
	
		
		openButton.setOnAction(event -> {
			
			FileSelect fileSelector = new FileSelect(stage);
			
			try {
				//fileSelector.start(stage);
				tHandler = new TransactionHandler();
				tHandler.parseFile(fileSelector.getFile());
				tHandler.printTransaction();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	
		saveButton.setOnAction(event -> {
		
			try {
				tHandler.saveTransaction();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	
		getChildren().addAll(openButton, saveButton);
	}
}
