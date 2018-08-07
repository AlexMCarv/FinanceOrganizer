package fxml;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import database.SQLQueries;
import datatype.DetailedTransaction;
import datatype.SimpleTransaction;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
		
		btnModify.setOnAction(this::populateTransactionList);
		btnCancel.setOnAction(this::formCloseWindow);

	}
	
	/**
	 * Add a new code/name of category to the database
	 */
	private void populateTransactionList(ActionEvent event) {
		
		
		LocalDate date = txbDate.getValue();
		String description = txtDescription.getText();
		System.out.println(index + "");
			
		try {
			Double value = Double.parseDouble(txtValue.getText());

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
	
}
