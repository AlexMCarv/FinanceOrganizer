package fxml;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import parsers.BankFileParser;


public class ImportInvalidTransController {
	
    /* Reminder: the variable name for the injected controller is the
     * id defined in the <fx: include fx:id="..."> appended by Controller. 
     * Therefore for this controller, which has an id: validTrans
     * <fx:include fx:id="validTrans" source="ImportValidTrans.fxml" />
     * the variable name is validTransController.
     @FXML private ImportValidTransController validTransController;
    */
    
	BankFileParser parseBankFile;
	String[] returnValue;
	@FXML private ListView<String> txtInvalidTransactions;
	@FXML private ListView<String> txtValidTransactions;
	@FXML private Button btnConfirm;
		
	public void initialize(BankFileParser parseBankFile) {
		this.parseBankFile = parseBankFile;
		List<String> validList = parseBankFile.printTransactions();
		List<String> invalidList = parseBankFile.printInvalidTransactions();
		
		populateList(txtInvalidTransactions, invalidList);
		txtInvalidTransactions.setOnMouseClicked(this::clickItem);
		populateList(txtValidTransactions, validList);
		ModifyInvalidTransactionController.injectBuilderController(this);
	}
	
	/**
	 * Populates the txtValidTransactions ListView with the transactions
	 * that were successfully imported from the import file.
	 * @param listView ListView to be populated 
	 * @param list Source of data to populate the ListView
	 */
	public void populateList(ListView<String> listView, List<String> list) {
		listView.setItems(FXCollections.observableList(list));
	}
	
	@FXML
	public void clickItem(MouseEvent event)
	{
		if (event.getClickCount() == 2) //Checking double click
	    {
	        String transaction = txtInvalidTransactions.getSelectionModel().getSelectedItem();
	        if (transaction != null) {
		        
	        	try {
					
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/fxml/ModifyInvalidTransaction.fxml"));
					loader.setController(new ModifyInvalidTransactionController(transaction));
					Scene scene = new Scene(loader.load(),900,450);
					Stage newStage = new Stage();
				
					newStage.setScene(scene);
					newStage.initModality(Modality.APPLICATION_MODAL);
					newStage.showAndWait();
	
				
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
	        }
	    }
	}
	
	public void setReturnValue(String[] returnValue){
		this.returnValue = returnValue;
	}
	
	public void updateWindow() {
		System.out.println("teste");
		System.out.println(returnValue[1]);
		int index = Integer.parseInt(returnValue[0]);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate newDate = LocalDate.parse(returnValue[1], formatter);
		String newDescription = returnValue[2];
		Double newValue = Double.parseDouble(returnValue[3]);
		char newType = 'W';
		if (returnValue[4] == "Deposit") 
			newType = 'D';
			
		parseBankFile.updateInvalidTransaction(index, newDate, newDescription, newValue, newType);
		populateList(txtInvalidTransactions, parseBankFile.printInvalidTransactions());
		populateList(txtValidTransactions, parseBankFile.printTransactions());
	}
}
