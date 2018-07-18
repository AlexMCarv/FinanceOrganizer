package core;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Core extends Application {
	
	public void start(Stage primaryStage) throws Exception {

		Scene scene = new Scene(new ApplicationGUI(primaryStage), 300,300, Color.RED);
		primaryStage.setTitle("Finance Organizer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
		
	}
}
