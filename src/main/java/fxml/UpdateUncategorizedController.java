package fxml;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import database.SQLQueries;
import datatype.DetailedTransaction;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdateUncategorizedController implements javafx.fxml.Initializable{

	@FXML private Button btnUpdate;
	@FXML private Button btnClose;
	@FXML private TableView<DetailedTransaction> tblTransaction;
	@FXML private TableColumn<DetailedTransaction, LocalDate> tbcDate;
	@FXML private TableColumn<DetailedTransaction, String> tbcCategory;
	@FXML private TableColumn<DetailedTransaction, String> tbcDescription;
	@FXML private TableColumn<DetailedTransaction, Double> tbcValue;
	private final String CATEGORY = "ZZZ";
	ObservableList<DetailedTransaction> transactionList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateTransactionList();
		tblTransaction.setOnMouseClicked(this::clickItem);
		btnUpdate.setOnAction(this::autoUpdate);
		btnClose.setOnAction(this::formCloseWindow);
		tbcDate.setCellValueFactory(
			    new PropertyValueFactory<DetailedTransaction, LocalDate>("date"));
		tbcCategory.setCellValueFactory(
			    new PropertyValueFactory<DetailedTransaction, String>("category"));
		tbcDescription.setCellValueFactory(
			    new PropertyValueFactory<DetailedTransaction, String>("description"));
		tbcValue.setCellValueFactory(
			    new PropertyValueFactory<DetailedTransaction, Double>("value"));
	}
	
	/**
	 * Populate the list with transactions of category type: CATEGORY
	 */
	private void populateTransactionList() {
		try {
			transactionList = SQLQueries.showAllTransByCategory(CATEGORY);
			tblTransaction.setItems(transactionList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void clickItem(MouseEvent event)
	{
	    if (event.getClickCount() == 2) //Checking double click
	    {
	        String categoryCode = tblTransaction.getSelectionModel().getSelectedItem().getCategory();
	        LocalDate date = tblTransaction.getSelectionModel().getSelectedItem().getDate();
	        String description = tblTransaction.getSelectionModel().getSelectedItem().getDescription();
	        char type = tblTransaction.getSelectionModel().getSelectedItem().getType();
	        Double value = tblTransaction.getSelectionModel().getSelectedItem().getValue();
	        	        
	        try {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/fxml/SelectCategory.fxml"));
				loader.setController(new SelectCategoryController(date, description, value, type, categoryCode));
				Scene scene = new Scene(loader.load(),900,450);
				Stage newStage = new Stage();
				
				newStage.setScene(scene);
				newStage.show();
			
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
	        
	        populateTransactionList();
	    }
	}
	
	private void autoUpdate(ActionEvent event) {
				
		for (DetailedTransaction transaction : transactionList) {
	        String categoryCode = transaction.getCategory();
	        LocalDate date = transaction.getDate();
	        String description = transaction.getDescription();
	        char type = transaction.getType();
	        Double value = transaction.getValue();
			SQLQueries.autoUpdateCategory(date, description, value, type, categoryCode);
		}
		
		populateTransactionList();
	}
	
	
	
	/**
	 * Closes the UpdateUncategorized.fxml window
	 */
	private void formCloseWindow(ActionEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
	    stage.close();
	}
	
}
