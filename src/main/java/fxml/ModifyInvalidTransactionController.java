package fxml;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyInvalidTransactionController implements javafx.fxml.Initializable{

	@FXML private TextField txtRawData;
	@FXML private TextField txtDescription;
	@FXML private TextField txtValue;
	@FXML private ChoiceBox<String> txbType;
	@FXML private DatePicker txbDate;
	@FXML private Button btnModify;
	@FXML private Button btnCancel;
	private int index;
	private String rawData;
	private static ImportInvalidTransController parentController;

	public ModifyInvalidTransactionController() {}
	
	public ModifyInvalidTransactionController(String transaction) {
		rawData = transaction;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtRawData.setText(rawData);
		Scanner sc = new Scanner(rawData);
		sc.next();
		index = sc.nextInt();
		sc.close();
		String[] list = {"Withdraw","Deposit"};
		txbType.setItems(FXCollections.observableList(Arrays.asList(list)));
		
		btnModify.setOnAction(this::modifyTransaction);
		btnCancel.setOnAction(this::formCloseWindow);

	}
	
	/**
	 * Add a new code/name of category to the database
	 */
	private void modifyTransaction(ActionEvent event) {
		
		String[] transaction = new String[5];
								
		try {
			transaction[0] = index + "";
			transaction[1] = txbDate.getValue().toString();
			transaction[2] = txtDescription.getText();
			transaction[3] = txtValue.getText();
			transaction[4] = txbType.getSelectionModel().getSelectedItem();
			parentController.setReturnValue(transaction);
			formCloseWindow(event);
			parentController.updateWindow();
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	

	}
	
	/**
	 * Closes the ModifyInvalidTransactionController.fxml window
	 */
	private void formCloseWindow(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
	    stage.close();
	}
	
		 
	public static void injectBuilderController(ImportInvalidTransController parentContr){
		parentController = parentContr;
	}
}
