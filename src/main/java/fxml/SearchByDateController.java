package fxml;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import database.SQLQueries;
import datatype.DetailedTransaction;
import datatype.SimpleTransaction;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchByDateController implements javafx.fxml.Initializable{


	@FXML
	private DatePicker txbDateFrom;
	@FXML
	private DatePicker txbDateTo;
	@FXML
	private Button btnSearch;
	@FXML
	private ListView<String> txtTransList;
	@FXML
	private PieChart graph;
	@FXML
	private TableView<DetailedTransaction> tblTransaction;
	@FXML
	private TableColumn<DetailedTransaction, LocalDate> tbcDate;
	@FXML
	private TableColumn<DetailedTransaction, String> tbcCategory;
	@FXML
	private TableColumn<DetailedTransaction, String> tbcDescription;
	@FXML
	private TableColumn<DetailedTransaction, Double> tbcValue;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnSearch.setOnAction(this::populateTransactionList);
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
	 * Add a new code/name of category to the database
	 */
	private void populateTransactionList(ActionEvent event) {
		
		LocalDate fromDate = txbDateFrom.getValue();
		LocalDate toDate = txbDateTo.getValue();
				
		try {

			// populate the list with transactions
			tblTransaction.setItems(SQLQueries.showTransByDateRange(fromDate, toDate));


		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
