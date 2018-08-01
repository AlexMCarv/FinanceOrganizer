package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import database.SQLQueries;
import datatype.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ImportController implements javafx.fxml.Initializable {

	@FXML
	private ListView<String> txtOwnerList;
	@FXML
	private ListView<String> txtAccountList;
	@FXML
	private TextField txtFileLocation;
	@FXML
	private Button btnImport;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnSearch;
	
	List<Account> accountList = new ArrayList<Account>();
	int selectedAccountToImport;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnImport.setOnAction(this::formCloseWindow);
		btnSearch.setOnAction(this::formCloseWindow);
		btnCancel.setOnAction(this::formCloseWindow);
		
		accountList = SQLQueries.retrieveAccountFromDB();
		txtOwnerList.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            String selectedOwner = txtOwnerList.getSelectionModel().getSelectedItem();
	    		List<String> list = new ArrayList<String>(); 
	    		
	    		for(Account account : accountList) {
	    			System.out.println(account.getOwner());
	    			if(account.getOwner().equals(selectedOwner)) {
	    				list.add(account.getBank());
	    				System.out.println(account.getBank());
	    			}
	    		}
	    		
	    		txtAccountList.setItems(FXCollections.observableList(list));
	        }
	    });
		
		txtOwnerList.setItems(populateOwner());
		
		txtAccountList.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            String selectedOwner = txtOwnerList.getSelectionModel().getSelectedItem();
	    		String selectAccount = txtAccountList.getSelectionModel().getSelectedItem();
	            	    		
	    		for(Account account : accountList) {
	    			if(account.getOwner().equals(selectedOwner) &&
	    					account.getBank().equals(selectAccount)) 
	    				selectedAccountToImport = account.getid();
	    		}
	        }
	    });
	}

	
	public ObservableList<String> populateOwner(){

		List<String> list = new ArrayList<String>(); 
		
		for(Account account : accountList) {
			if (!list.contains(account.getOwner()))
				list.add(account.getOwner());
		}

		return FXCollections.observableList(list);
		
	} // End of populateOwner() 
	
	
	/**
	 * Closes the Import.fxml window
	 */
	private void formCloseWindow(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
	    stage.close();
	}

}
