package core;
import java.io.IOException;
import java.net.URL;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddCategory {

	
	public AddCategory () throws IOException {
		
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AddCategory.fxml")),430,250);
		Stage stage = new Stage();
		
		stage.setScene(scene);
		//stage.sizeToScene();
		stage.show();
	}

}