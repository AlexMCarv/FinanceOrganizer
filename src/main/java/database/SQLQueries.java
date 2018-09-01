package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import datatype.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SQLQueries {

	
	public static void addCategory(String code, String name){
				
		CallableStatement stmt = null;
		Connection conn = null;
		
		try {
			
			conn = MySQLConnection.createConnection();
			String query = "{call addToCategory(?,?)}";
			
			stmt = conn.prepareCall(query); // intensive
			stmt.setString(1, code);
			stmt.setString(2, name);
			
			stmt.executeQuery();
			
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1062)
				System.out.println("Error: Category CODE already exists.");
			if (ex.getErrorCode() == 1406)
				System.out.println("Error: Category CODE must be at 3 characters long");
			System.out.println(ex.getMessage());
	    
		} finally {
			try {
				if (stmt!= null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
	
	} // End of addCategory()  

	
	public static ObservableList<String> showCategory(){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rset = null;
		List<String> list = new ArrayList<String>(); 
		
		try {
			conn = MySQLConnection.createConnection();
			String query = "{call showCategory()}";
			stmt = conn.prepareCall(query); // intensive
			rset = stmt.executeQuery();
            
			while (rset.next()) {
				list.add(rset.getString(1) + " - " + rset.getString(2));
            }
            			
			
		} catch (Exception ex) {
			 System.out.println(ex.getMessage());
	    
		} finally {
			try {
				if (stmt!= null)
					stmt.close();
				if (rset!= null)
					rset.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
		
		return FXCollections.observableList(list);
		
	} // End of showCategory() 
	
	
	/**
	 * This method queries the DB to retrieve all the accounts in the database
	 * @return List with all accounts stored in the database 
	 */
	public static List<Account> retrieveAccountFromDB(){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rset = null;
		List<Account> list = new ArrayList<Account>(); 
		
		try {
			conn = MySQLConnection.createConnection();
			String query = "{call showAccounts()}";
			stmt = conn.prepareCall(query); // intensive
			rset = stmt.executeQuery();
            
			while (rset.next()) {
				
				String owner = rset.getString(1) + " " + rset.getString(2);
				String bank = rset.getString(3);
				int id = rset.getInt(4);
				list.add(new Account(id, owner, bank));
            }
            			
			
		} catch (Exception ex) {
			 System.out.println(ex.getMessage());
	    
		} finally {
			try {
				if (stmt!= null)
					stmt.close();
				if (rset!= null)
					rset.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
		
		return list;
		
	} // End of retrieveAccountFromDB() 

	public static ObservableList<Integer> listYears(){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rset = null;
		List<Integer> list = new ArrayList<Integer>(); 
		
		try {
			conn = MySQLConnection.createConnection();
			String query = "{call listYears()}";
			stmt = conn.prepareCall(query); // intensive
			rset = stmt.executeQuery();
            
			while (rset.next()) {
				list.add(rset.getInt(1));
            }
            			
			
		} catch (Exception ex) {
			 System.out.println(ex.getMessage());
	    
		} finally {
			try {
				if (stmt!= null)
					stmt.close();
				if (rset!= null)
					rset.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
		
		return FXCollections.observableList(list);
		
	} // End of listYears() 
	
	public static ObservableList<SimpleTransaction> showTransaction(int month, int year, String category){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rset = null;
		List<SimpleTransaction> list = new ArrayList<SimpleTransaction>(); 
		
		try {
			conn = MySQLConnection.createConnection();
			String query = "{call queryByCategory(?, ?, ?, ?, ?)}";
			stmt = conn.prepareCall(query); // intensive
			stmt.setInt(1, month);
			stmt.setInt(2, year);
			stmt.setString(3, category);
			stmt.setString(4, "Z");
			stmt.setInt(5, -1);

			rset = stmt.executeQuery();
            
			while (rset.next()) {
				list.add(new SimpleTransaction(category, rset.getString(1), rset.getDouble(2)));
            }
            			
			
		} catch (Exception ex) {
			 System.out.println(ex.getMessage());
	    
		} finally {
			try {
				if (stmt!= null)
					stmt.close();
				if (rset!= null)
					rset.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
		
		return FXCollections.observableList(list);
		
	} // End of showCategory() 
	
	public static ObservableList<CategorySummary> showYearlySummmary(int year, char type){
		Connection conn = null;
		CallableStatement stmt = null;
		CallableStatement stmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		List<CategorySummary> list = new ArrayList<CategorySummary>(); 
		List<Category> categoryList = new ArrayList<Category>();
		
		try {
			conn = MySQLConnection.createConnection();
			
			// Populating the category list
			String query = "{call showCategory()}";
			stmt = conn.prepareCall(query); // intensive
			rset = stmt.executeQuery();
            
			while (rset.next()) {
				categoryList.add(new Category(rset.getString(1), rset.getString(2)));
            }

			
			try {
				if (stmt!= null)
					stmt.close();
				if (rset!= null)
					rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
			
			// Populating the CategorySummary list
			query = "{call queryByCategory(?, ?, ?, ?, ?)}";
			stmt2 = conn.prepareCall(query); // intensive
			
			stmt2.setInt(2, year);
			stmt2.setString(4, String.valueOf(type));
			stmt2.setInt(5, 0);
			
			for(Category category : categoryList) {
				stmt2.setString(3, category.getCategoryCode());
				double[] valueArray = new double[12];
				
				for(int month = 1; month <= 12; month++){
					stmt2.setInt(1, month);
					rset2 = stmt2.executeQuery();
					rset2.next();
					valueArray[month - 1] = rset2.getDouble(1);
				}
				
				CategorySummary currentCategory = new CategorySummary(category, year, valueArray);
				if (currentCategory.getTotal() > 0.0001) {
					list.add(currentCategory);
				} 
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
	    
		} finally {
			try {
				if (stmt2!= null)
					stmt2.close();
				if (rset2!= null)
					rset2.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
		
		return FXCollections.observableList(list);
		
	} // End of showYearlySummmary()
	
	public static ObservableList<SimpleTransaction> showTransByCatAndDate(LocalDate fromDate, LocalDate toDate, String category){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rset = null;
		List<SimpleTransaction> list = new ArrayList<SimpleTransaction>();
				
		try {
			conn = MySQLConnection.createConnection();
			String query = "{call queryByDateRange(?, ?, ?, ?)}";
			stmt = conn.prepareCall(query); // intensive
			stmt.setDate(1, Date.valueOf(fromDate));
			stmt.setDate(2, Date.valueOf(toDate));
			stmt.setString(3, category);
			stmt.setInt(4, 1);
			
			rset = stmt.executeQuery();
            
			while (rset.next()) {
				list.add(new SimpleTransaction(category, rset.getString(1), rset.getDouble(2)));
            }
            			
			
		} catch (Exception ex) {
			 System.out.println(ex.getMessage());
	    
		} finally {
			try {
				if (stmt!= null)
					stmt.close();
				if (rset!= null)
					rset.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
		
		return FXCollections.observableList(list);
		
	} // End of showTransByCatAndDate()
	
	public static ObservableList<DetailedTransaction> showAllTransByCategory(String category){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rset = null;
		List<DetailedTransaction> list = new ArrayList<DetailedTransaction>();
				
		try {
			conn = MySQLConnection.createConnection();
			String query = "{call showAllTransByCategory(?)}";
			stmt = conn.prepareCall(query); // intensive
			stmt.setString(1, category);
			rset = stmt.executeQuery();
			
			while (rset.next()) {
				list.add(new DetailedTransaction(rset.getDate(1).toLocalDate(), rset.getString(2), 
						rset.getString(3), rset.getDouble(4), rset.getString(5).charAt(0)));
            }
            			
			
		} catch (Exception ex) {
			 System.out.println(ex.getMessage());
	    
		} finally {
			try {
				if (stmt!= null)
					stmt.close();
				if (rset!= null)
					rset.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
		
		return FXCollections.observableList(list);
		
	} // End of showAllTransByCategory()
	
	public static ObservableList<DetailedTransaction> showTransByDateRange(LocalDate fromDate, LocalDate toDate, String category){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rset = null;
		List<DetailedTransaction> list = new ArrayList<DetailedTransaction>();
				
		try {
			conn = MySQLConnection.createConnection();
			String query = "{call queryByDateRange(?, ?, ?, ?)}";
			stmt = conn.prepareCall(query); // intensive
			stmt.setDate(1, Date.valueOf(fromDate));
			stmt.setDate(2, Date.valueOf(toDate));
			//The category here is irrelevant, as the call will return all categories
			stmt.setString(3, category);
			
			if (category == "") {
				stmt.setInt(4, 0);
			} else {
				stmt.setInt(4, -1);
			}
						
			
			rset = stmt.executeQuery();
			
			while (rset.next()) {
				list.add(new DetailedTransaction(rset.getDate(1).toLocalDate(), rset.getString(2), 
						rset.getString(3), rset.getDouble(4), rset.getString(5).charAt(0)));
            }
            			
			
		} catch (Exception ex) {
			 System.out.println(ex.getMessage());
	    
		} finally {
			try {
				if (stmt!= null)
					stmt.close();
				if (rset!= null)
					rset.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
		
		return FXCollections.observableList(list);
		
	} // End of showTransByDateRange()

	public static int[] importToDB(List<Transaction> list, int accountID){
		
		CallableStatement stmt = null;
		Connection conn = null;
		int successful = 0;
		int unsuccessful = 0;
		
		try {
			conn = MySQLConnection.createConnection();
		
			// Table fields:
			// bank_transaction (trans_date, trans_description, 
			//                   trans_value, trans_type, account_id, 
			//                   category_id, fdescription_id)
			String query = "{call addToTransaction(?,?,?,?,?,?,?)}";
			
			stmt = conn.prepareCall(query); // intensive
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		
			try {
				if (stmt!= null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		
		for (int i = 0; i < list.size(); i++) {
			
			try {
				
				Transaction transaction = list.get(i);
				
				// Preparing the statement
				stmt.setDate(1, Date.valueOf(transaction.getDate()));
				stmt.setString(2, transaction.getDescription());
				stmt.setDouble(3, transaction.getValue());
				stmt.setString(4, transaction.getType() + "");
				stmt.setInt(5, accountID);
				stmt.setString(6, transaction.getCategory());
				// Sets fdescription_id to 0, which represents Undefined
				stmt.setInt(7, 0);
					
				stmt.executeQuery();
				successful++;
			
			//***** TO-DO - update error codes *****
			} catch (SQLException ex) {
				if (ex.getErrorCode() == 1062)
					System.out.println("Error: Category CODE already exists.");
				if (ex.getErrorCode() == 1406)
					System.out.println("Error: Category CODE must be at 3 characters long.");
				System.out.println(ex.getMessage());
				unsuccessful++;

			} //End of Try/Catch Block
		
		} // End of for loop for data
		
		try {
			if (stmt!= null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} //End of Try/Catch Block
		
		return new int[] {successful, unsuccessful} ;

	} //End of importToDB()
	
	public static void updateCategory(LocalDate date, String description, Double value, char type, String categoryCode, String newCategory){
		
		CallableStatement stmt = null;
		Connection conn = null;
		
		try {
			
			conn = MySQLConnection.createConnection();
			String query = "{call updateCategory(?,?,?,?,?,?)}";
			
			stmt = conn.prepareCall(query); // intensive
			stmt.setDate(1, Date.valueOf(date));
			stmt.setString(2, description);
			stmt.setDouble(3, value);
			stmt.setString(4, String.valueOf(type));
			stmt.setString(5, categoryCode);
			stmt.setString(6, newCategory);
			
			stmt.executeQuery();
			
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1062)
				System.out.println("Error: Category CODE already exists.");
			if (ex.getErrorCode() == 1406)
				System.out.println("Error: Category CODE must be at 3 characters long");
			System.out.println(ex.getMessage());
	    
		} finally {
			try {
				if (stmt!= null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
	
	} // End of updateCategory() 
	
	public static void autoUpdateCategory(LocalDate date, String description, Double value, char type, String categoryCode){
		
		CallableStatement stmt = null;
		Connection conn = null;
		ResultSet rset = null;
		String mostCommonCategory = "ZZZ";
		
		try {
			
			conn = MySQLConnection.createConnection();
			String query = "{call getMostCommonCategory(?,?)}";
			
			stmt = conn.prepareCall(query); // intensive
			stmt.setString(1, description);
			stmt.setString(2, String.valueOf(type));
			rset = stmt.executeQuery();
						
			if (rset != null) {				
				rset.next();
				mostCommonCategory = rset.getString(1);
			}
			           
			
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1062)
				System.out.println("Error: Category CODE already exists.");
			if (ex.getErrorCode() == 1406)
				System.out.println("Error: Category CODE must be at 3 characters long");
			System.out.println(ex.getMessage());
	    
		} finally {
			try {
				if (stmt!= null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		} //End of Try/Catch/Finally Block
	
		updateCategory(date, description, value, type, categoryCode, mostCommonCategory);
		
	} // End of updateCategory() 
	
} // End of SQLQueries Class
