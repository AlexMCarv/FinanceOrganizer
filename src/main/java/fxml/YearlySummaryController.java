package fxml;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import core.ApplicationGUI;
import database.SQLQueries;
import datatype.*;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class YearlySummaryController implements javafx.fxml.Initializable{


	@FXML
	private ComboBox<Integer> cmbChangeYear;
	@FXML
	private Label lblYear;
	@FXML
	private PieChart graph;
	@FXML
	private TreeView<Button> tblSideMenu;
	@FXML
	private TableView<CategorySummary> tblYearSummary;
	@FXML
	private TableColumn<CategorySummary, String> tbcCode;
	@FXML
	private TableColumn<CategorySummary, String> tbcCategory;
	@FXML
	private TableColumn<CategorySummary, Double> tbcEValue;
	@FXML
	private TableColumn<CategorySummary, Double> tbcJan;
	@FXML
	private TableColumn<CategorySummary, Double> tbcFeb;
	@FXML
	private TableColumn<CategorySummary, Double> tbcMar;
	@FXML
	private TableColumn<CategorySummary, Double> tbcApr;
	@FXML
	private TableColumn<CategorySummary, Double> tbcMay;
	@FXML
	private TableColumn<CategorySummary, Double> tbcJun;
	@FXML
	private TableColumn<CategorySummary, Double> tbcJul;
	@FXML
	private TableColumn<CategorySummary, Double> tbcAug;
	@FXML
	private TableColumn<CategorySummary, Double> tbcSep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcOct;
	@FXML
	private TableColumn<CategorySummary, Double> tbcNov;
	@FXML
	private TableColumn<CategorySummary, Double> tbcDec;
	@FXML
	private TableColumn<CategorySummary, Double> tbcTotal;
	@FXML
	private TableView<CategorySummary> tblYearSummaryDep;
	@FXML
	private TableColumn<CategorySummary, String> tbcCodeDep;
	@FXML
	private TableColumn<CategorySummary, String> tbcCategoryDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcEValueDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcJanDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcFebDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcMarDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcAprDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcMayDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcJunDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcJulDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcAugDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcSepDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcOctDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcNovDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcDecDep;
	@FXML
	private TableColumn<CategorySummary, Double> tbcTotalDep;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cmbChangeYear.setItems(SQLQueries.listYears());
		cmbChangeYear.setOnAction(this::updateTable);
		createSideMenu();
		updateTable();
		
		// Expenses Table
		tbcCode.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, String>("categoryCode"));
		tbcCategory.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, String>("categoryName"));
		tbcJan.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("jan"));
		
		// Personal reminders:	
		// setCellFactory argument requires an object that implements the CallBack functional interface.
		// by functional interface it means that CallBack has only one method signature:
		// returnType call(argumentType param). The required types of setCellFactory for the CallBack
		// are: TableColumn<CategorySummary, Double> as the argument, and
		// TableCell<CategorySummary, Double> as the return type.
		// That is why in the lambda expression bellow after -> we are "returning" a DoubleTableCell
		// which extends TableCell<CategorySummary, Double> but overwrites updateItem
		// The left side of the lambda expression is the argument, which is being inferred by
		// the compiler to be TableColumn<CategorySummary, Double>. Since I am not doing anything
		// with the TableColumn such as getting the CellFactory back, the variable col is not used
		// on the right side of the lambda expression.
		tbcJan.setCellFactory(col -> new DoubleTableCell());

		tbcFeb.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("feb"));
		tbcFeb.setCellFactory(col -> new DoubleTableCell());
		
		tbcMar.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("mar"));
		tbcMar.setCellFactory(col -> new DoubleTableCell());
		
		tbcApr.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("apr"));
		tbcApr.setCellFactory(col -> new DoubleTableCell());
		
		tbcMay.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("may"));
		tbcMay.setCellFactory(col -> new DoubleTableCell());
		
		tbcJun.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("jun"));
		tbcJun.setCellFactory(col -> new DoubleTableCell());
		
		tbcJul.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("jul"));
		tbcJul.setCellFactory(col -> new DoubleTableCell());
		
		tbcAug.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("aug"));
		tbcAug.setCellFactory(col -> new DoubleTableCell());
		
		tbcSep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("sep"));
		tbcSep.setCellFactory(col -> new DoubleTableCell());
		
		tbcOct.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("oct"));
		tbcOct.setCellFactory(col -> new DoubleTableCell());
		
		tbcNov.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("nov"));
		tbcNov.setCellFactory(col -> new DoubleTableCell());
		
		tbcDec.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("dec"));
		tbcDec.setCellFactory(col -> new DoubleTableCell());
		
		tbcTotal.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("total"));
		tbcTotal.setCellFactory(col -> new DoubleTableCell());
		
		// Deposits Table
		tbcCodeDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, String>("categoryCode"));
		tbcCategoryDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, String>("categoryName"));
		tbcJanDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("jan"));
		tbcJanDep.setCellFactory(col -> new DoubleTableCell());

		tbcFebDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("feb"));
		tbcFebDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcMarDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("mar"));
		tbcMarDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcAprDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("apr"));
		tbcAprDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcMayDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("may"));
		tbcMayDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcJunDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("jun"));
		tbcJunDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcJulDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("jul"));
		tbcJulDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcAugDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("aug"));
		tbcAugDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcSepDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("sep"));
		tbcSepDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcOctDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("oct"));
		tbcOctDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcNovDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("nov"));
		tbcNovDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcDecDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("dec"));
		tbcDecDep.setCellFactory(col -> new DoubleTableCell());
		
		tbcTotalDep.setCellValueFactory(
			    new PropertyValueFactory<CategorySummary, Double>("total"));
		tbcTotalDep.setCellFactory(col -> new DoubleTableCell());
	}
	
	/**
	 * Add a new code/name of category to the database
	 */
	private void updateTable(ActionEvent event) {
		
		int year = cmbChangeYear.getValue();
		lblYear.setText(String.valueOf(year));
				
		try {

			// populate the list with transactions
			tblYearSummary.setItems(SQLQueries.showYearlySummmary(year, 'W'));
			tblYearSummaryDep.setItems(SQLQueries.showYearlySummmary(year, 'D'));


		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateTable() {
		
		int year = LocalDate.now().getYear();
		lblYear.setText(String.valueOf(year));
		
		try {

			// populate the list with transactions
			tblYearSummary.setItems(SQLQueries.showYearlySummmary(year, 'W'));
			tblYearSummaryDep.setItems(SQLQueries.showYearlySummmary(year, 'D'));

		} catch (IllegalArgumentException e) {
			
			System.out.println(e.getMessage());
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	// Create
	private void createSideMenu() {

		// Root Item
		Button menu = new Button("Menu");
		TreeItem<Button> rootItem = new TreeItem<>(menu);
		rootItem.setExpanded(true);
		
		// Reports
		Button report = new Button("Reports");
		TreeItem<Button> itemReport = new TreeItem<>(report);
		Button btnReportYearly = new Button("Yearly");
		TreeItem<Button> itemYearly = new TreeItem<>(btnReportYearly);
		Button btnReportMonthly = new Button("Monthly");
		TreeItem<Button> itemMonthly = new TreeItem<>(btnReportMonthly);
		itemReport.getChildren().addAll(itemYearly, itemMonthly);
	      
		// Transactions
		Button btnTransaction = new Button("Transactions");
		TreeItem<Button> itemTransactions = new TreeItem<>(btnTransaction);
		Button btnTransactionCategory = new Button("By Category");
		btnTransactionCategory.setOnAction(e -> {
			try {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/fxml/SearchCategory.fxml"));
				loader.setController(new SearchCategoryController());
				Scene scene = new Scene(loader.load(),900,450);
				Stage newStage = new Stage();
				
				newStage.setScene(scene);
				//stage.sizeToScene();
				newStage.show();
			
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

		});
		TreeItem<Button> itemByCategory = new TreeItem<>(btnTransactionCategory);
		
		Button btnTransactionDate = new Button("By Date");
		btnTransactionDate.setOnAction(e -> {
			try {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/fxml/SearchByDate.fxml"));
				loader.setController(new SearchByDateController());
				Scene scene = new Scene(loader.load(),900,450);
				Stage newStage = new Stage();
				
				newStage.setScene(scene);
				//stage.sizeToScene();
				newStage.show();
			
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

		});
		TreeItem<Button> itemByDate = new TreeItem<>(btnTransactionDate);
		
		itemTransactions.getChildren().addAll(itemByCategory, itemByDate);
		
		// Import
		Button btnImport = new Button("Import");
		TreeItem<Button> itemImport = new TreeItem<>(btnImport);
		
		rootItem.getChildren().addAll(itemReport, itemTransactions, itemImport);
		tblSideMenu.setRoot(rootItem);
		tblSideMenu.setShowRoot(false);
		tblSideMenu.setCellFactory(e -> new SideMenuCell());
				
		
	}


}
