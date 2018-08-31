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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import parsers.BankFileParser;
import parsers.ParsePresidentChoice;
import parsers.ParseScotiaBank;
import parsers.ParseScotiaCreditCard;

public class ImportMainController {

	@FXML private ListView<String> txtOwnerList;
	@FXML private ListView<String> txtAccountList;
	@FXML private ListView<String> txtValidTransactions;
	@FXML private ListView<String> txtInvalidTransactions;
	@FXML private TextField txtFileLocation;
	@FXML private Button btnRead;
	@FXML private Button btnCancel;
	@FXML private Button btnSearch;
	@FXML private Button btnConfirm;
	
	/**	Holds the list of accounts names, which are retrieved from the DB */
	List<Account> accountList = new ArrayList<Account>();
	
	/**	Holds the id of the account to which the imported transactions
		are going to be added to. */
	int selectedAccountToImport;
	
	/**	Holds the file parser that allows access to all imported data */
	BankFileParser parseBankFile;
	
	/**	Holds the file reference used by the parser */
	File fileToImport;
	

	public void init() {
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

	/**
	 * Retrieves all the account owners in the DB
	 * @return The ObservableList of all account owners in the DB 
	 * */
	public ObservableList<String> populateOwner(){

		List<String> list = new ArrayList<String>(); 
		
		for(Account account : accountList) {
			if (!list.contains(account.getOwner()))
				list.add(account.getOwner());
		}

		return FXCollections.observableList(list);
		
	} // End of populateOwner() 
	

	/**
	 * Opens a window to allow the selection of a file to be imported.
	 * It alters the fileToImport reference with the new file chosen, and
	 * updates the File to Import text field.
	 */
	private void selectFile(ActionEvent event) {
		
		FileSelect fileSelector = new FileSelect((Stage) btnCancel.getScene().getWindow());
		fileToImport = fileSelector.getFile();
		txtFileLocation.setText(fileToImport.getAbsolutePath());
	}
	
	
	/**
	 * Based on the selected account type, it creates the correct file parser to 
	 * retrieve all transactions in the file. It loads modifies the parseBankFile
	 * variable with all imported data, and it populates the valid transaction box,
	 * and invalid transaction box.
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
		
		// Passes the imported list to the reviewer window and displays it.
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/ImportInvalidTrans.fxml"));
			Scene scene = new Scene(loader.load(),1100,390);
			Stage newStage = new Stage();
			ImportInvalidTransController controller = loader.getController();
			controller.initialize(parseBankFile);
			
			newStage.setScene(scene);
			newStage.initModality(Modality.APPLICATION_MODAL);
			//stage.sizeToScene();
			newStage.showAndWait();
		
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	/**
	 * Closes the Import.fxml window
	 */
	private void formCloseWindow(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
	    stage.close();
	}

}
