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


	@FXML private DatePicker txbDateFrom;
	@FXML private DatePicker txbDateTo;
	@FXML private Button btnSearch;
	@FXML private ListView<String> txtTransList;
	@FXML private PieChart graph;
	@FXML private TableView<DetailedTransaction> tblTransaction;
	@FXML private TableColumn<DetailedTransaction, LocalDate> tbcDate;
	@FXML private TableColumn<DetailedTransaction, String> tbcCategory;
	@FXML private TableColumn<DetailedTransaction, String> tbcDescription;
	@FXML private TableColumn<DetailedTransaction, Double> tbcValue;
	private int month;
	private int year;
	private String category;
	
	
	public SearchByDateController() {
		
	}
	
	public SearchByDateController(int month, int year, String category) {
		this.month = month;
		this.year = year;
		this.category = category;
	}
	
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
		
		if (month > 0 && year > 0 && category != null) {
			
			LocalDate fromDate = LocalDate.of(year, month, 1);
			LocalDate toDate = fromDate.withDayOfMonth(fromDate.lengthOfMonth());
			
			try {

				// populate the list with transactions
				tblTransaction.setItems(SQLQueries.showTransByDateRange(fromDate, toDate, category));
				txbDateFrom.setValue(fromDate);
				txbDateTo.setValue(toDate);

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Add a new code/name of category to the database
	 */
	private void populateTransactionList(ActionEvent event) {
		
		LocalDate fromDate = txbDateFrom.getValue();
		LocalDate toDate = txbDateTo.getValue();
				
		try {

			// populate the list with transactions
			tblTransaction.setItems(SQLQueries.showTransByDateRange(fromDate, toDate, ""));


		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
