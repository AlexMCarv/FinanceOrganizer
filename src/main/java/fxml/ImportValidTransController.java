package fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class ImportValidTransController {
	
	@FXML private AnchorPane paneValidTransaction;
	@FXML private ListView<String> txtValidTransactions;
	@FXML private Button btnConfirm;
	
	
	public void setTxtValidTransactions(List<String> list) {
		
		txtValidTransactions.setItems(FXCollections.observableList(list));
	}
	
	

}
