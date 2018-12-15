package core;
import java.io.IOException;

import fxml.YearlySummaryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Core extends Application {
	
	public void start(Stage primaryStage) throws Exception {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/YearlySummary.fxml"));
			loader.setController(new YearlySummaryController());
			Scene scene = new Scene(loader.load(),1400,760);
			Stage newStage = new Stage();
			scene.getStylesheets().add("application.css");
			primaryStage.setTitle("Finance Organizer");
			scene.getStylesheets().add("application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
		
	}
}
