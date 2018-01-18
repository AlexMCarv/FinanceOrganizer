import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Core extends Application
{
	public void start(final Stage stage) throws Exception {
		Button openButton = new Button("Import CSV File");
		
		openButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				FileSelect fileSelector = new FileSelect(stage);
				try {
					fileSelector.start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		
		Group root = new Group(openButton);
		Scene scene = new Scene(root, 300,300, Color.RED);
		
		stage.setTitle("Finance Organizer");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
		//FileImporter.launch();
		//ProgramGUI.main(null);
	}

}
