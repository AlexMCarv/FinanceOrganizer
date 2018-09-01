package fxml;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import database.SQLQueries;
import datatype.SimpleTransaction;
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
import javafx.stage.Stage;

public class SelectCategoryController implements javafx.fxml.Initializable{

	@FXML private ComboBox<String> cmbCategory;
	@FXML private Button btnConfirm;
	@FXML private Button btnCancel;
    private String categoryCode;
    private LocalDate date;
    private String description;
    private char type;
    private Double value;
	
    public SelectCategoryController(LocalDate date, String description, Double value, char type, String categoryCode) {
    	this.date = date;
    	this.description = description;
    	this.value = value;
    	this.type = type;
    	this.categoryCode = categoryCode;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnConfirm.setOnAction(this::confirmSelection);
		btnCancel.setOnAction(this::formCloseWindow);
		cmbCategory.setItems(SQLQueries.showCategory());
	}
	
	/**
	 * Add a new code/name of category to the database
	 */
	private void confirmSelection(ActionEvent event) {
		
		String newCategory = "";
		
		try {
			newCategory = cmbCategory.getValue().substring(0, 3);
		
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Category not selected or invalid");
		}
		
		if (newCategory != "") {
			SQLQueries.updateCategory(date, description, value, type, categoryCode, newCategory);
			System.out.println("updating");
		}
		formCloseWindow(event);
	}
	
	/**
	 * Closes the SelectCategory.fxml window
	 */
	private void formCloseWindow(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
	    stage.close();
	}
	
}
