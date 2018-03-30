package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelperHarmony {
	static Connection conn;
	
	 public Connection getConnection(){
	    	try{
	    		Class.forName("org.postgresql.Driver");
	    		 conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/harmony","postgres","test");
	    		 System.out.println("Successful!!");
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	return conn;
	    }

}
