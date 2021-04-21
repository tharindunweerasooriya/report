package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
	
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
	
	public String insertProduct(String id, String name, String owner, String description, String price, String email) 
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 
		 
		 // create a prepared statement
		 String query = " insert into products (`product_id`,`product_name`,`owner`,`description`,`price`,`email`)"+ " values (?, ?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 
		 // binding values 
		 preparedStmt.setInt(1, Integer.parseInt(id)); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, owner); 
		 preparedStmt.setString(4, description); 
		 preparedStmt.setDouble(5, Double.parseDouble(price)); 
		 preparedStmt.setString(6, email);
		 
		 
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
	
	public String readProducts() 
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Product id</th><th>Product Name</th>" +
		 "<th>Owner</th>" + 
		 "<th>Description</th>" +
		 "<th>price</th>"+
		 "<th>email</th></tr>"; 
		 
		 String query = "select * from products"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String projectID = Integer.toString(rs.getInt("product_id")); 
		 String projectName = rs.getString("product_name"); 
		 String owner = rs.getString("owner"); 
		 String desc = rs.getString("description"); 
		 String price =Double.toString(rs.getDouble("price"));  
		 String email = rs.getString("email");
		 
		 
		 // Add into the html table
		 output += "<tr><td>" + projectID + "</td>"; 
		 output += "<td>" + projectName + "</td>"; 
		 output += "<td>" + owner + "</td>"; 
		 output += "<td>" + desc + "</td>"; 
		 output += "<td>" + price + "</td>";
		 output += "<td>" + email + "</td>";
		 
		 
		 
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
	
	public String updateProduct(String id, String name, String owner, String description, String price, String email)
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 
		 
		 // create a prepared statement
		 String query = "UPDATE products SET product_name=?,owner=?,description=?,price=?,email=? WHERE product_id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, owner); 
		 preparedStmt.setString(3, description); 
		 preparedStmt.setDouble(4, Double.parseDouble(price)); 
		 preparedStmt.setString(5, email); 
		 preparedStmt.setInt(6, Integer.parseInt(id));
		 
		 
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
	
	public String deleteProduct(String ID) 
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 
		 
		 // create a prepared statement
		 String query = "delete from products where product_id=?"; 
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
