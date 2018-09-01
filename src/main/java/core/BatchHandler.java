package core;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import database.MySQLConnection;
import datatype.Transaction;
/**
 * This class is used to import data of transaction from a .csv file
 */

public class BatchHandler extends TransactionHandler {

	/**
	 * Loops through every line in rawData and creates 
	 * a transaction object and adds it to the data
	 */
	public void createTransaction () {
		// Creating transaction objects
		for (String[] line : rawData) 
		{
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		String category = line[0];
    		LocalDate date = LocalDate.parse("2018-01-01", formatter);
    		
    		
    		try {
    			date = LocalDate.parse(line[2], formatter);
    		
    		} catch (DateTimeParseException e) {
    			    			
    			System.out.println("Can't parse date: " + line[0] + line[1] + line[2]);
    			System.exit(1);
    		}
    		
    		String description = line[3];
    		double value;
    		char type;
    		
    		try {
    			value = Double.parseDouble(line[4]);
    			type = 'W';
    		
    		} catch (NumberFormatException e){
    			try {
    				value = Double.parseDouble(line[5]);
    				type = 'D';
    			
    			} catch (NumberFormatException ex){
    				value = 0;
    				type = 'W';
    			}
    		}
    				 
    		Transaction transaction = new Transaction(date, value, type, description, category);
    		data.add(transaction);
    	
		} //End of for loop
	
	} //End of createTransaction()

	/**
	 * Connects to the database and for every transaction
	 * in data, adds it to the database
	 */
	public void importToDB() {
		
		CallableStatement stmt = null;
		Connection conn = null;
		int count = 0;
		
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
			
		
		for (int i = 0; i < data.size(); i++) {
			
			try {
				
				Transaction transaction = data.get(i);
				
				// Preparing the statement
				stmt.setDate(1, Date.valueOf(transaction.getDate()));
				stmt.setString(2, transaction.getDescription());
				stmt.setDouble(3, transaction.getValue());
				stmt.setString(4, transaction.getType() + "");
				//Sets account_id to 10, which represents Common
				stmt.setInt(5, 10);
				stmt.setString(6, transaction.getCategory());
				// Sets fdescription_id to 0, which represents Undefined
				stmt.setInt(7, 0);
					
				stmt.executeQuery();
				count++;
			
			//***** TO-DO - update error codes *****
			} catch (SQLException ex) {
				if (ex.getErrorCode() == 1062)
					System.out.println("Error: Category CODE already exists.");
				if (ex.getErrorCode() == 1406)
					System.out.println("Error: Category CODE must be at 3 characters long.");
				System.out.println(ex.getMessage());

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

		System.out.println("A total of " + count + " transactions were added to the database.");

	} //End of importToDB()
	
} //End of BatchHandler
