package core;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class AddCategory {

	
	public AddCategory () throws IOException {
		
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AddCategory.fxml")),430,250);
		Stage stage = new Stage();
		
		stage.setScene(scene);
		//stage.sizeToScene();
		stage.show();
	}

}