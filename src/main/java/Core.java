import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Core extends Application {
	private TransactionHandler tHandler;
	
	public void start(final Stage stage) throws Exception {
		Button openButton = new Button("Import CSV File");
		Button saveButton = new Button("Save to CSV File");
		
		openButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				FileSelect fileSelector = new FileSelect(stage);
				
				try {
					fileSelector.start(stage);
					tHandler = new TransactionHandler();
					tHandler.parseFile(fileSelector.getFile());
					tHandler.printTransaction();
					tHandler.saveTransaction("teste.csv");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
		Group root = new Group(openButton);
		Scene scene = new Scene(root, 300,300, Color.RED);
		
		stage.setTitle("Finance Organizer");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
		//ProgramGUI.main(null);
	}
}
