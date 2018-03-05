package com.cerner.hackathon.avengers.clinicalweightvalidation;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

public class DataBaseConnect {
	
	// JDBC driver name and database URL
	   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   final String DB_URL = "jdbc:mysql://localhost/test";

	   //  Database credentials
	    final String USER = "root";
	    final String PASS = "kammar";
	   
	   Connection conn = null;
	   Statement stmt = null;

	private static long getDaysDifferentWithCurrentDate(Date weightEnteredDate) {
		// TODO Auto-generated method stub
		
		Date currentDateTime = new Date();
		
		long diff = currentDateTime.getTime() - weightEnteredDate.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public JSONObject addWeight(ClinicalWeightValidationObject cwv) {
		// TODO Auto-generated method stub
		JSONObject insertObject = new JSONObject();		   
		   try{
		      // Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      // Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      // Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      
		      double newWeight = cwv.getWeight();
		      String newWeightUnit = cwv.getWeightUnit();
		       
		      sql = "INSERT INTO test.patient_table values("+newWeight+","+"\""+newWeightUnit+"\""+","+ "NOW())";
		      stmt.executeUpdate(sql);
		      
		      insertObject.put("success", true);
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   return (insertObject); 
	}

	public JSONObject getWeightOfPatient() {
		// TODO Auto-generated method stub
		// We return this object with information
		   JSONObject returnObject = new JSONObject();
		   
		   try{
			 // Register JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");

		      // Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM test.patient_table ORDER BY weight_entered_date DESC LIMIT 1";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      boolean result = rs.next();
		      
		      if(result == false)
		      {
		    	  returnObject.put("No weight charted",false); 
		      }

		      // Extract data from result set
		      else{
		         //Retrieve by column name
		         double weight  = rs.getDouble("weight");
		         String wieghtUnit = rs.getString("weight unit");
		         Date weightEnteredDate=rs.getDate("weight_entered_date");
		         
		         long numberOfDay = getDaysDifferentWithCurrentDate(weightEnteredDate);
		         
		         if(numberOfDay>=5)
		         {
		        	 // not valid, show warning message to user to insert again values
		        	 
		        	 returnObject.put("Please provide new weight", false);
		         }
		         else
		         {
		        	 // Valid insert the date into table
		        	
		        	 returnObject.put("weight", weight);
		        	 returnObject.put("weight unit", wieghtUnit);
		        	 returnObject.put("weight_entered_date", weightEnteredDate);
		         }
		      }
		      // Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   
		   return (returnObject);
	}
}
