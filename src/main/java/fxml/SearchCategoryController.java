package fxml;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import database.SQLQueries;
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

public class SearchCategoryController implements javafx.fxml.Initializable{

	@FXML
	private DatePicker txbDateFrom;
	@FXML
	private DatePicker txbDateTo;
	@FXML
	private ComboBox<String> cmbCategory;
	@FXML
	private Button btnSearch;
	@FXML
	private ListView<String> txtTransList;
	@FXML
	private PieChart graph;
	@FXML
	private TableView<SimpleTransaction> tblTransaction;
	@FXML
	private TableColumn<SimpleTransaction, String> tbcDescription;
	@FXML
	private TableColumn<SimpleTransaction, Double> tbcValue;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		btnSearch.setOnAction(this::populateTransactionList);
		cmbCategory.setItems(SQLQueries.showCategory());
		tbcDescription.setCellValueFactory(
			    new PropertyValueFactory<SimpleTransaction, String>("description"));
		tbcValue.setCellValueFactory(
			    new PropertyValueFactory<SimpleTransaction, Double>("value"));
	}
	
	/**
	 * Add a new code/name of category to the database
	 */
	private void populateTransactionList(ActionEvent event) {
		
		LocalDate fromDate = txbDateFrom.getValue();
		LocalDate toDate = txbDateTo.getValue();
		String category = "";
		
		try {
			category = cmbCategory.getValue().substring(0, 3);
		
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Category not selected or invalid");
		}
		
		
		try {

			// populate the list with transactions
			tblTransaction.setItems(SQLQueries.showTransByCatAndDate(fromDate, toDate, category));


		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
