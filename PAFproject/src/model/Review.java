package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Review {

	
	private Connection connect() 
	 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
	 } 
	
	
	
	
		
	
	//Insert items to table
	
	public String insertItem(String id, String name, String owner, String description, String price, String email, String phone, String review, String acceptance) 
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 
		 
		 // create a prepared statement
		 String query = " insert into review (`project_id`,`project_name`,`owner`,`description`,`price`,`email`,`phone`,`review`,`acceptance`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 
		 // binding values 
		 preparedStmt.setInt(1, Integer.parseInt(id)); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, owner); 
		 preparedStmt.setString(4, description); 
		 preparedStmt.setDouble(5, Double.parseDouble(price)); 
		 preparedStmt.setString(6, email);
		 preparedStmt.setInt(7, Integer.parseInt(phone));
		 preparedStmt.setString(8, review);
		 preparedStmt.setString(9, acceptance);
		 
		 
		// execute the statement3
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	
	
	
	
	
	
	
	
	
	//retrieve items from the table
	
	public String readItems() 
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Project id</th><th>Project Name</th>" +
		 "<th>Owner</th>" + 
		 "<th>Description</th>" +
		 "<th>price</th>"+
		 "<th>email</th>"+
		 "<th>phone</th>"+
		 "<th>review</th>"+
		 "<th>acceptance</th></tr>"; 
		 
		 String query = "select * from review"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String projectID = Integer.toString(rs.getInt("project_id")); 
		 String projectName = rs.getString("project_name"); 
		 String owner = rs.getString("owner"); 
		 String desc = rs.getString("description"); 
		 String price =Double.toString(rs.getDouble("price"));  
		 String email = rs.getString("email");
		 String phone = Integer.toString(rs.getInt("phone"));
		 String review = rs.getString("review");
		 String acceptance = rs.getString("acceptance");
		 
		 
		 // Add into the html table
		 output += "<tr><td>" + projectID + "</td>"; 
		 output += "<td>" + projectName + "</td>"; 
		 output += "<td>" + owner + "</td>"; 
		 output += "<td>" + desc + "</td>"; 
		 output += "<td>" + price + "</td>";
		 output += "<td>" + email + "</td>";
		 output += "<td>" + phone + "</td>";
		 output += "<td>" + review + "</td>";
		 output += "<td>" + acceptance + "</td>";
		 
		 
		 
		 } 
		 con.close(); 
		 
		 
		 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }
	
	
	
	
	
	
	
	
	//update items in the table 
	
	public String updateItem(String id, String name, String owner, String description, String price, String email, String phone, String review, String acceptance)
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 
		 
		 // create a prepared statement
		 String query = "UPDATE review SET project_name=?,owner=?,description=?,price=?,email=?,phone=?,review=?,acceptance=? WHERE project_id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, owner); 
		 preparedStmt.setString(3, description); 
		 preparedStmt.setDouble(4, Double.parseDouble(price)); 
		 preparedStmt.setString(5, email); 
		 preparedStmt.setInt(6, Integer.parseInt(phone));
		 preparedStmt.setString(7, review); 
		 preparedStmt.setString(8, acceptance); 
		 preparedStmt.setInt(9, Integer.parseInt(id));
		 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	
	
	
	
	
	
	//delete items from table
	
	public String deleteItem(String ID) 
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 
		 
		 // create a prepared statement
		 String query = "delete from review where project_id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(ID)); 
		 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
}
	
	
}
