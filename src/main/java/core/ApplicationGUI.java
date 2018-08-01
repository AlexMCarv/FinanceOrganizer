package core;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parsers.*;

import java.io.IOException;
import java.time.LocalDate;

import fxml.*;


public class ApplicationGUI extends VBox{
		
	Button btnOpenButton = new Button("Import CSV File");
	Button saveButton = new Button("Save to CSV File");
	Button addToCategory = new Button("Add to Category");
	Button btnBatchImport = new Button("Batch Data Import");
	Button catSearch = new Button("Category Search");
	Button yearSummary = new Button("Yearly Summary");
	TransactionHandler tHandler;
	BatchHandler bHandler;
	BankFileParser parseBankFile;
	
	public ApplicationGUI(Stage stage) {
	
		
		btnOpenButton.setOnAction(event -> {
			
			FileSelect fileSelector = new FileSelect(stage);
			
			try {
				/*//fileSelector.start(stage);
				tHandler = new TransactionHandler();
				tHandler.parseFile(fileSelector.getFile());
				tHandler.printTransaction();*/
				parseBankFile = new ParsePresidentChoice();
				parseBankFile.parseFile(fileSelector.getFile());
				parseBankFile.printTransactions();
				System.out.println();
				parseBankFile.printInvalidTransactions();
				parseBankFile.updateInvalidTransaction(2, LocalDate.of(2018, 10, 07), "Superstore", 82.88, 'W');
				parseBankFile.printTransactions();
				System.out.println();
				parseBankFile.printInvalidTransactions();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	
		saveButton.setOnAction(event -> {
		
			try {
				tHandler.saveTransaction();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	
		addToCategory.setOnAction(this::addCategory);
		btnBatchImport.setOnAction(this::batchImport);
		catSearch.setOnAction(this::categorySearch);
		yearSummary.setOnAction(this::yearlySummary);
		
		getChildren().addAll(btnOpenButton, saveButton, addToCategory, btnBatchImport, catSearch, yearSummary);
	}
	
	private void addCategory(ActionEvent event) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/AddCategory.fxml"));
			loader.setController(new AddCategoryController());
			Scene scene = new Scene(loader.load(),430,250);
			Stage newStage = new Stage();
			
			newStage.setScene(scene);
			//stage.sizeToScene();
			newStage.show();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void batchImport(ActionEvent event) {
		
		Button source = (Button) event.getSource();
		FileSelect fileSelector = new FileSelect(source.getScene().getWindow());
		
		try {
						
			bHandler = new BatchHandler();
			bHandler.parseFile(fileSelector.getFile());
			//bHandler.printTransaction();
			bHandler.importToDB();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	} // End of batchImport()
	
	
	private void categorySearch(ActionEvent event) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/SearchCategory.fxml"));
			loader.setController(new SearchCategoryController());
			Scene scene = new Scene(loader.load(),900,450);
			Stage newStage = new Stage();
			
			newStage.setScene(scene);
			//stage.sizeToScene();
			newStage.show();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	} // End of categorySearch()
	
	private void yearlySummary(ActionEvent event) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/YearlySummary.fxml"));
			loader.setController(new YearlySummaryController());
			Scene scene = new Scene(loader.load(),1400,760);
			Stage newStage = new Stage();
			scene.getStylesheets().add("application.css");
			
			newStage.setScene(scene);
			//stage.sizeToScene();
			newStage.show();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	} // End of categorySearch()
	
}
