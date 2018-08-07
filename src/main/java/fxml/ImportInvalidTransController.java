package fxml;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ImportInvalidTransController {
	
	@FXML private ListView<String> txtInvalidTransactions;
		
	public void initialize() {
		txtInvalidTransactions.setOnMouseClicked(this::clickItem);
	}
	
	/**
	 * Populates the txtValidTransactions ListView with the transactions
	 * that were successfully imported from the import file.
	 */
	public void setTxtInvalidTransactions(List<String> list) {
		txtInvalidTransactions.setItems(FXCollections.observableList(list));
	}
	
	@FXML
	public void clickItem(MouseEvent event)
	{
	    if (event.getClickCount() == 2) //Checking double click
	    {
	        String transaction = txtInvalidTransactions.getSelectionModel().getSelectedItem();
	        
	        try {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/fxml/ModifyInvalidTransaction.fxml"));
				loader.setController(new ModifyInvalidTransactionController(transaction));
				Scene scene = new Scene(loader.load(),900,450);
				Stage newStage = new Stage();
				
				newStage.setScene(scene);
				//stage.sizeToScene();
				newStage.show();
			
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
        
	    }
	}
	
}
