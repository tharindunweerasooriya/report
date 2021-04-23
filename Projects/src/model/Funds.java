package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Funds {

	//A common method to connect to the DB
		private Connection connect() 
		 { 
			 Connection con = null; 
			 try
			 { 
			 Class.forName("com.mysql.jdbc.Driver"); 
			 
			 //Provide the correct details: DBServer/DBName, username, password 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/newdb", "root", ""); 
			 } 
			 catch (Exception e) 
			 {e.printStackTrace();} 
			 return con; 
			 
		 } 
		
		public String addFund(String admin_ID, String product_ID, String funds) 
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for adding."; } 
			 // create a prepared statement
			 String query = " insert into funds (`fund_ID`,`admin_ID`,`product_ID`,`funds`)"+ " values (?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, admin_ID); 
			 preparedStmt.setString(3, product_ID);
			 preparedStmt.setString(4, funds);
			// execute the statement3
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "added successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while adding the fund."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		 } 
		
		public String readFunds() 
		{ 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Funds ID</th><th>Admin ID</th>" +
			 "<th>Product ID</th>" + 
			 "<th>Funds</th>" + 
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from funds"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String fund_ID  = Integer.toString(rs.getInt("fund_ID")); 
			 String admin_ID = rs.getString("admin_ID"); 
			 String product_ID = rs.getString("product_ID");
			 String funds = rs.getString("funds"); 
			  
			 // Add into the html table
			 output += "<tr><td>" + fund_ID + "</td>"; 
			 output += "<td>" + admin_ID + "</td>"; 
			 output += "<td>" + product_ID + "</td>"; 
			 output += "<td>" + funds + "</td>";
			 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='projects.jsp'>"
			+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='projectID' type='hidden' value='" + fund_ID 
			 + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while reading the funds."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		 }
		
		public String updateFunds(String ID, String admin_ID, String product_ID, String funds)
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 // create a prepared statement
			 String query = "UPDATE funds SET admin_ID=?,product_ID=?,funds=? WHERE fund_ID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, admin_ID); 
			 preparedStmt.setString(2, product_ID);
			 preparedStmt.setString(3, funds);
			 
			  
			 preparedStmt.setInt(4, Integer.parseInt(ID));
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Fund Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while updating the Funds."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		 } 
		
		public String deleteFunds(String fund_ID) 
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
			 // create a prepared statement
			 String query = "delete from funds where fund_ID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(fund_ID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Fund Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while deleting the fund."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
	 } 
		
	
}
