package fxml;
import java.net.URL;
import java.util.ResourceBundle;
import database.SQLQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCategoryController implements javafx.fxml.Initializable{


	@FXML
	private TextField txtCode;
	@FXML
	private TextField txtName;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnCancel;
	@FXML
	private ListView<String> txtCategoryList;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnAdd.setOnAction(this::formAddCategory);
		btnCancel.setOnAction(this::formCloseWindow);
		txtCategoryList.setItems(SQLQueries.showCategory());
	}
	
	/**
	 * Add a new code/name of category to the database
	 */
	private void formAddCategory(ActionEvent event) {
		try {
			// Input data validation
			if (txtCode.getText().length() != 3)
				throw new IllegalArgumentException(
						"Category Code is not three characters long. "
						+ "Please modify the code.");
			
			if (txtName.getText() == "")
				throw new IllegalArgumentException(
						"Category Name is Empty. Please insert a name.");
			
			// Insert in the database
			SQLQueries.addCategory(txtCode.getText(), txtName.getText());
			
			// Re-populate the list of categories
			txtCategoryList.setItems(SQLQueries.showCategory());
			
			// Clear the form
			txtCode.setText("");
			txtName.setText("");
		
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the AddCategory.fxml window
	 */
	private void formCloseWindow(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
	    stage.close();
	}
	
}
