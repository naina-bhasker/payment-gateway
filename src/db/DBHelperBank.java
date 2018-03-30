package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelperBank {
	static Connection conn;
	
	 public Connection getConnection(){
	    	try{
	    		Class.forName("org.postgresql.Driver");
	    		 conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bank","postgres","test");
	    		 System.out.println("Successful!!");
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	return conn;
	    }
}
