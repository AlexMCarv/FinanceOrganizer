package fxml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import core.FileSelect;
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
import parsers.BankFileParser;
import parsers.ParsePresidentChoice;
import parsers.ParseScotiaBank;
import parsers.ParseScotiaCreditCard;

public class ImportController implements javafx.fxml.Initializable {

	@FXML
	private ListView<String> txtOwnerList;
	@FXML
	private ListView<String> txtAccountList;
	@FXML
	private ListView<String> txtValidTransactions;
	@FXML
	private ListView<String> txtInvalidTransactions;
	@FXML
	private TextField txtFileLocation;
	@FXML
	private Button btnRead;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnConfirm;
	
	
	
	
	List<Account> accountList = new ArrayList<Account>();
	int selectedAccountToImport;
	BankFileParser parseBankFile;
	File fileToImport;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnRead.setOnAction(this::readFromFile);
		btnSearch.setOnAction(this::selectFile);
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
	 * 
	 */
	private void selectFile(ActionEvent event) {
		
		FileSelect fileSelector = new FileSelect((Stage) btnCancel.getScene().getWindow());
		fileToImport = fileSelector.getFile();
		txtFileLocation.setText(fileToImport.getAbsolutePath());
	}
	
	
	/**
	 * 
	 */
	private void readFromFile(ActionEvent event) {
		String selectedAccount = txtAccountList.getSelectionModel().getSelectedItem();
		
		if (selectedAccount.contains("PRESIDENT")) {
			parseBankFile = new ParsePresidentChoice();
		} else if (selectedAccount.equals("SCOTIABANK")){
			parseBankFile = new ParseScotiaBank();
		} else {
			parseBankFile = new ParseScotiaCreditCard();
		}
		
		try {
			parseBankFile.parseFile(fileToImport);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> validList = parseBankFile.printTransactions();
		List<String> invalidList = parseBankFile.printInvalidTransactions();
		
		txtValidTransactions.setItems(FXCollections.observableList(validList));
		txtInvalidTransactions.setItems(FXCollections.observableList(invalidList));
	}
	
	/**
	 * Closes the Import.fxml window
	 */
	private void formCloseWindow(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
	    stage.close();
	}

}
