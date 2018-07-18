package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

	private static String url = "jdbc:mysql://localhost:3306/financeorganizer?autoReconnect=true&useSSL=false";
    private static String user = "root";
    private static String password = "adminroot";
    
    /**
     	Creates a new MySQLConnection. This enables the possibility of multiple connections
     	with the database.
     */
	private MySQLConnection() {	
	} // End of Constructor
	
    
	/**
 		Creates a new connection with the database
 		@return the connection with database
     */
	public static Connection createConnection() throws SQLException{
		return  DriverManager.getConnection(url, user, password);
	} // End of createConnection 
	
	
} // End of MySQLConnection Class
